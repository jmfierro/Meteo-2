## Ejercicio de App de Meteorologia ##

**Jose Manuel Fierro Conchouso**

Clase Android, profesor Samuel Moreno, U-Tad.

### Comentarios sobre el desarrollo ###
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

###Descarga de datos de internet ###
Sin wifi en casa, simulo la lectura de datos desde un **fichero JSON que sitúo en res/raw**. Más adelante lo adapto a una conexión con internet.

Para la lectura de datos de internet, o fichero en raw, inicio un servicio para que se ejecute en segundo plano, implementado la clase **WebServicio()**. Esto permite que la conexión a internet se haga en segundo plano y de forma independiente a la actividad que lo llama, es decir, que si se destruye la actividad cliente, el servicio seguirá ejecutándose, lo que permitirá que la base de datos, que implementare más adelante se actualicé. 

Además ejecuto el código de la conexión en un hilo separado, utilizando un **AsyncTask**. Con ello la conexión a internet tendrá su propio proceso y sin interferir en el de la aplicación.

Me decido por un **bindService()** porque quiero que haya comunicación entre la actividad y el servicio. El servicio avisará a la actividad cliente cuando los datos hayan sido ya bajados de internet.

para que inicie un servicio y pueda hacer una lectura de los datos (del fichero JSON en raw ó de Internet), de forma independiente y liberar el uso de la interface del usuario.

La clase hereda de Service compatible con versiones antiguas.

Enjoy first-class Markdown support with easy access to  Markdown syntax and convenient keyboard shortcuts.

Give them a try:

- **Bold** (`Ctrl+B`) and *Italic* (`Ctrl+I`)
- Quotes (`Ctrl+Q`)
- Code blocks (`Ctrl+K`)
- Headings 1, 2, 3 (`Ctrl+1`, `Ctrl+2`, `Ctrl+3`)
- Lists (`Ctrl+U` and `Ctrl+Shift+O`)

### See your changes instantly with LivePreview ###

Don't guess if your [hyperlink syntax](http://markdownpad.com) is correct; LivePreview will show you exactly what your document looks like every time you press a key.

### Make it your own ###

Fonts, color schemes, layouts and stylesheets are all 100% customizable so you can turn MarkdownPad into your perfect editor.

### A robust editor for advanced Markdown users ###

MarkdownPad supports multiple Markdown processing engines, including standard Markdown, Markdown Extra (with Table support) and GitHub Flavored Markdown.

With a tabbed document interface, PDF export, a built-in image uploader, session management, spell check, auto-save, syntax highlighting and a built-in CSS management interface, there's no limit to what you can do with MarkdownPad.
