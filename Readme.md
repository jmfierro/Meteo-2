## Ejercicio de App de Meteorologia ##

***Jose Manuel Fierro Conchouso***

*U-Tad., Clase Android, Profesor Samuel Moreno, 2013-2014*

# Comentarios sobre el desarrollo #
### APIS ###

Comienzo por la búsqueda de proveedores API de meteorología(servicios,datos,...).Me decido inicialmente por:

- **"Openweathermap"** que proporciona acceso a datos meteorológicos por idioma, utilizando tanto coordenadas como nombre de la ciudad. Tambien se puede obtener un listado de ciudades.

- **"Metwit"** del que obtengo los iconos (más atractivos que el resto de proveedores)

### Descripción app ###
La aplicación en un principio constara de tres pantallas:

- Pantalla 1: Búsqueda y adicción de ciudades.
- Pantalla 2: Lista de ciudades favoritas.
- Pantalla 3: Detalles de los datos meteorológicos de una ciudad elegida.

### Primeras actividades ###
Inicio el desarrollo con un **listView** que simula una lista de ciudades.

La seleccion de una ciudad abre una **segunda actividad** que muestras los detalles meteorológicos de una ciudad.

###Servicio para la obtención de datos meteorológicos ###

Para la lectura de datos de internet, o fichero en raw, inicio un servicio para que se ejecute en **segundo plano**, implementado la clase **WebServicio()**. Esto permite que la conexión a internet se haga en segundo plano y de forma independiente a la actividad que lo llama, es decir, que si se destruye la actividad cliente, el servicio seguirá ejecutándose, lo que permitirá que la base de datos, que implementare más adelante se actualicé. 

Me decido por un **bindService()** porque quiero que haya comunicación entre la actividad y el servicio. El servicio avisará a la actividad cliente cuando los datos hayan sido ya bajados de internet.

Inicio el servicio desde **onCreate()** de la actividad principal porque por ahora sólo quiero que se ejecute una vez al iniciar la aplicación. Otra opción podría ser ejecutarlo desde **onStart()**, y con un if() condicional para que sólo ejecutará el servicio una vez por día, evitando que lo hiciera cada vez que la actividad se volviera a hacer visible despues de haber quedado tapada por otra.

Implemento un sitema de Callback mediante la **interface OnWebServicioListener** para hacer *la carga, parseo y gestión y visualización de datos* . Permite a las clase cliente que ejecuten su propio código para el parseo, gestión y visualización de datos.

Ejecuto el código de la conexión en un hilo separado, utilizando un **AsyncTask**. Con ello la conexión a internet tendrá su propio proceso y no interferirá en la interfaz del usuario.

Hago uso de la clase **loadWebTask.java** para hacer *la carga, parseo y gestión de datos y visualización *mediante un procedimiento de clases abstractas. Permite a las clase cliente que ejecuten su propio código para el parseo de datos, gestión y visualización.

### Problema: eclipse no reconoce la tablet Nexus 7###

Tengo un portátil HP Pavilion dm3 con procesador Dual-Core (1,6 GHz) yes, el mayor tiene una velocidad de 1,6 GHz y 4096 MB DDR2. No puede con el emulador virtual. Dependo de la tablet que nos dejaron en U-Tad. 

Pues eclipse dejo de reconocerla, o mejor dicho, windows 7 la ignoraba por completo. ¡ Había estado funcionando bien desde el primer día hasta el ayer !. Buscando en internet y probando esto y aquello finalmente se soluciono. HABIA QUE VOLVER A INSTALAR LOS DRIVES ORIGINALES DE NEXUS 7, ¡ Windows 7 por su cuenta y riesgo los reinstaló y puso los que le pareció bien !. 

### Menus ###
La clase **MeteoMenuActionBarActivity()** para gestionar la visualización del menús por herencia. Empece a utilizar un método, **isActivityVisible()**, para saber que actividad es visible, guardando el hasdCode al pasar por **onResumen()**, de la actividad que se hacia visible.  En las clases que hereden deben de gestionar en **onResumen() y onPause()** los metodos activityResumed() y activityPaused().

Aunque funcionaba no me convencía. Busque en internet para averiguar como se sabia si una actividad era no visible desde otra clase. No encontré nada claro. Empece a probar por mi cuenta con hasdCode():

		int hasdMain = MeteoMainActivity.class.hashCode();
		int hasdDetalle = MeteoCiudadDetalle.class.hashCode();
		int hasdActual = getApplication().getClass().hashCode();
		int hasdClass = getClass().hashCode();
		int hasdthis = hashCode();
Al final con **gesClass().hashCode()** me da el "hasdCode" de la Actividad que esta visible:

		if (MeteoMainActivity.class.hashCode() == getClass().hashCode())
			getMenuInflater().inflate(R.menu.ciudades_lista, menu);
		if (MeteoCiudadDetalle.class.hashCode() == getClass().hashCode())
			getMenuInflater().inflate(R.menu.ciudad_detalle, menu);
 


### Fragmentos###

Creo un **fragmento estático** para que contenga el "listView" para las localidades. Llama a la clase **MeteoListaLocalidadesFragmento()** que herada de **ListFragment**.

Utilizo **"adaptador universal"**, una clase llamada "ListAdapterGenerico" que pueda reutilizarse en otro código sin tener que modificarlo. Hereda de **BaseAdapter** y tiene un método abstracto **onSetItemForListAdapterGenerico()** que debe implementar quien invoque al adaptador.  onSetItemForListAdapterGenerico() será el encargado de rellenar la vista "particular" del item en el lisView.

Yo lo invoco desde **MeteoListaLocalidadesFragmento()**, que es llamado directamente desde la vista **lista_localidades.xml**. Establezco llamadas **callbacks** con la clase **MeteoMainActivity** a través del método **onItemSeleccionado()**. En MeteoListaLocalidadesFragmento() mediante **onAttach()** me aseguro que se implementa el callback onItemSeleccionado() en la actividad asociada, y guardo una referencia de él.

Los fragmentos los hago compatibles con versiones anteriores a Android 3.0 ó nivel 11, utilizando **android.support.v4...**

Para la actividad del detalle de la localidad uso un **fragmento dinámico**.

### Distinción del tamaño de la pantalla de dispositivos ###

Creo **dimens.xml** en la carpeta **values-sw600dp** de "res" para detectar dispositivos con un tamaño de pantalla a partir de 7 pulgadas. La  variable  **dimensionPantalla** toma el valor true si es asi y falso para pantallas  menores. Igualmente la variable **main\_latout**  toma el valor de "meteo\_main\_unpanel" o "meteo\_main\_dospaneles" según el tamaño detectado.


### Estilos y temas###

Establezco un **tema** para todas las actividades: **Theme.AppCompat.Light.DarkActionBar** compatible con versiones antiguas de android. Para implementarlo modifico los **dimmens.xml** en los diferentes directorios **values**. Hago que todos los textos tengan el color #A0A0A0 (gris) poniéndolo en el tema **AppTheme**, para que afecte a todas las actividades por defecto.

Para la actividad **MeteoMainActivity** cambio el tema por **LocalidadDetalleStyle** en el **AndroidManifiest.xml**. Pone el fondo negro. Hereda de AppTheme.

Pongo el **estilo** (ListViewItemStyle) en el **RelativeLayout** de **item\_lista\_localidades.xml**. De fondo para el item de la lista aplica un drawable  (background.9.png) y para el item presionado otro (list_item_selected.9.png). También fija margenes y padding.   


### Search View en ActionBar y compatibilidad con API 7 ###
Para poder usar la introducción de texto desde *ActionBar* he utilizado **TextWatcher** y simulado un Search View. Tuve realizar numerosas pruebas y 
errores para una correcta simulación.

	        /*
			* Ocultar teclado
			* /
	        //et.setVisibility(View.INVISIBLE);
	        //et.setVisibility(View.GONE);
	        //et.requestFocus();
	        getWindow().setSoftInputMode(
	    	      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	        //imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
	        isSearchView = false;
	        //int heigh = et.getHeight();
	        //et.setHeight(10);
			...
 
El resultado es mejorable pero tan sólo quería mostrar que se puede hacer. 


### Wifi###

Sin wifi en casa, simulo la lectura de datos desde un **fichero JSON que sitúo en res/raw**. Más adelante lo adapto a una conexión con internet.
