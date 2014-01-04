package com.jmfierro.utad.meteo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jmfierro.utad.meteo.datos.DatosMeteo;
import com.jmfierro.utad.meteo.datos.DatosMeteoList;
import com.jmfierro.utad.meteo.ut.ListAdapterGenerico;
import com.jmfierro.utad.meteo.ut.Utils;
import com.jmfierro.utad.meteo.web.loadWebTask;
//import com.jmfierro.utad.meteo.weather.loadWebTask.OnWebListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MeteoListaLocalidadesFragmento extends ListFragment {
	
	/**==================================================================================
	 * Callback que notifique a la Activity de que elemento del listado se ha pulsado.
	 *===================================================================================*/
	private Callbacks mCallbacks = CallbacksVacios;
//	public ArrayList<DatosMeteo> mArrayListDatosMeteo = new ArrayList<DatosMeteo>();
	private DatosMeteoList mDatosMeteoList = new DatosMeteoList();
	
	public interface Callbacks {
		//public void onMyItemViewSeleccionado(String id);
		public void onMyItemViewSeleccionado(DatosMeteo datosMeteo);
	}
	 
	private static Callbacks CallbacksVacios = new Callbacks() {

		@Override
		public void onMyItemViewSeleccionado(DatosMeteo datosMeteo) {
			
		}
	};

	
	/**==============================
	 * Necesario constructor vacio. 
	 *===============================*/
	public MeteoListaLocalidadesFragmento() {
		// TODO Auto-generated constructor stub
	}
	
	/*-------------------------------
	 * Rellena cada item del listado.
	 *-------------------------------*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		readSQLdatosMeteo();
		
		loadWebTask bl = new loadWebTask() {

			@Override
			public InputStream loadRaw() {
				return getResources()
				 		.openRawResource(R.raw.googleapis_lista_localidades);
			}
			@Override
			public Object parseJSON(String stringJSON) {
				
				DatosMeteoList datosMeteoList = new DatosMeteoList();
				datosMeteoList = DatosMeteoList.parseJSONBusqueda(stringJSON);
				return datosMeteoList;
				
			}

			@Override
			public void onSetDatos(Object object) {
				mDatosMeteoList = (DatosMeteoList) object;
			}

			
			/*
			 * Instancia el ListView cuando ha terminado de 
			 * leer datos de inernet.
			 */
			@Override
			public void updateMyView(Object object) {
//				mArrayListDatosMeteo = (ArrayList<DatosMeteo>) object;

				/*------------------------------
				 * Llama la adptador generico. 
				 *------------------------------*/
				setListAdapter(new ListAdapterGenerico(getActivity(),R.layout.item_lista_localidades, ((DatosMeteoList)object).getdatosMeteoList()) {
					
					/*--------------------------------------------------------------------------
					 * Rellena un item para que lo use el adaptador ListAdapterGenerico.
					 *--------------------------------------------------------------------------*/
					@Override 
					public void onMyItemView(Object entrada, View view) {
								ImageView iconoPronostico = (ImageView) view.findViewById(R.id.img_Item_ListaLocalidades_Pronos);
								iconoPronostico.setImageDrawable(getActivity().getResources().getDrawable(R.raw.meteo_rain));
								
								TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
								textLocalidad.setText(((DatosMeteo)entrada).getNombre());
								TextView textLocalidadTemp = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Temp);
					}
					
				});
				
			}

		};

		bl.execute(40.6,-3.6);


		
	}

	
	public void updateMyLayout(DatosMeteoList datosMeteoList) {
//		public void updateMyLayout(ArrayList<DatosMeteo> arrayDatosMeteo) {
		
			mDatosMeteoList = datosMeteoList;
//			ArrayList<DatosMeteo> arr = new ArrayList<DatosMeteo>();
			ArrayList<DatosMeteo> arrayDatosMeteoList = new ArrayList<DatosMeteo>();
			arrayDatosMeteoList = datosMeteoList.getdatosMeteoList();
//			for (int i; i < datosMeteoList.getSize(); i++)
//				arrayDatosMeteoList.add(datosMeteoList.getdatosMeteoList().get(i));
			/*------------------------------
			 * Llama la adptador generico.
			 *------------------------------*/
			setListAdapter(new ListAdapterGenerico(getActivity(),R.layout.item_lista_localidades, datosMeteoList.getdatosMeteoList()){
				
				/*--------------------------------------------------------------------------
				 * Rellena un item para que lo use el adaptador ListAdapterGenerico.
				 *--------------------------------------------------------------------------*/
				@Override 
				public void onMyItemView(Object entrada, View view) {
							ImageView iconoPronostico = (ImageView) view.findViewById(R.id.img_Item_ListaLocalidades_Pronos);
							iconoPronostico.setImageDrawable(getActivity().getResources().getDrawable(R.raw.meteo_rain));
							
							TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
							textLocalidad.setText(((DatosMeteo)entrada).getNombre());
							TextView textLocalidadTemp = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Temp);
				}
				
			});

	}
	
	@Override
	public void onStart() { 
		super.onStart();
		if (getActivity().getApplication().getResources().getBoolean(R.bool.tablet))
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}
	
	/**==============================================================================
	 * Asegura que este implementado el Callback en la clase que use a este Fragment.
	 *===============================================================================*/
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException("Error: La actividad debe implementar el callback del fragmento");
		}

		mCallbacks = (Callbacks) activity;
	}
	
	/**=================
	 * Limpia Callbacks.
	 *==================*/
	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = CallbacksVacios; 
	}

	/**===============================================================
	 * Escucha la pulsación sobre un item de la lista. 
	 * Se usa el Callback para notificar a la Actividad el id pulsado.
	 *================================================================*/
	@Override
	public void onListItemClick(ListView listView, View view, int posicion, long id) {
		super.onListItemClick(listView, view, posicion, id);		
		//mCallbacks.onEntradaSelecionada(Lista_contenido.ENTRADAS_LISTA.get(posicion).id); 
		listView.setItemChecked(posicion, true);
	
		//mCallbacks.onMyItemViewSeleccionado(Integer.toString(posicion));
		mCallbacks.onMyItemViewSeleccionado(mDatosMeteoList.getdatosMeteoList().get(posicion));
	}

//	public ArrayList<DatosMeteo> getArrayListDatosMeteo() {
//		return mArrayListDatosMeteo;
//	}
//
//	public void setArrayListDatosMeteo(ArrayList<DatosMeteo> mArrayListDatosMeteo) {
//		this.mArrayListDatosMeteo = mArrayListDatosMeteo;
//	}
	
//	/**================================
//	 * De JSON Object a DatosMeteoList
//	 *=================================*/
//	public DatosMeteoList parseJSONBusqueda(InputStream streamJSON) {
//		
//		String stringJSON = Utils.stringJSONfromStream(streamJSON);
//		//DatosMeteoList datosMeteoList = parseJSONBusqueda(stringJSON);
//		DatosMeteoList datosMeteoList = new DatosMeteoList(stringJSON);
//		
//		return datosMeteoList;
//	}	
//	public DatosMeteoList parseJSONBusqueda(String stringJSON) {
//		
////		ArrayList<DatosMeteo> arraylistDatosMeteo = new ArrayList<DatosMeteo>();
//		DatosMeteoList datosMeteoList = new DatosMeteoList();
//		try {
//
//			JSONObject jsonObject = new JSONObject(stringJSON);
//
//			if (jsonObject.getString("status").equals("OK")) {
//				JSONArray localidades = jsonObject.getJSONArray("results"); 
//
//				for (int i=0; i < localidades.length(); i++) {
//					JSONObject objMain = localidades.getJSONObject(i);
//
//
//					String nombre = objMain.getString("formatted_address");
////					TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
////					textLocalidad.setText(nombre);
//					DatosMeteo datosMeteo1 = new DatosMeteo();
//					datosMeteo1.setNombre(nombre);
//					datosMeteoList.add(datosMeteo1);
//
//					JSONObject objGeo = objMain.getJSONObject("geometry");
//					JSONObject objLoc = objGeo.getJSONObject("location");
//					Double lat = objLoc.getDouble("lat");
//					Double log = objLoc.getDouble("lng");
//				}
//
//			}
//		} catch (JSONException e) {
//			Log.e("JSON Parser", "Error parsing data " + e.toString());
//		}
//		return datosMeteoList;
//	}

	
	
//	public DatosMeteo parseJSONweatherworl(String stringJSON) {
//		
//		DatosMeteo datosMeteo = new DatosMeteo();
//		/*------------------------------
//		 * Parse objeto JSON
//		 *------------------------------*/
//		try {
//
//			//Generate the jsonObject form response
//			JSONObject jsonObject = new JSONObject(stringJSON);
//
//			//datosMeteo = new DatosMeteo();
//
//			datosMeteo.setNombre(jsonObject.getString("name"));
//
//			JSONArray array = jsonObject.getJSONArray("weather");
//
//			JSONObject object = array.getJSONObject(0);
//
//			datosMeteo.setMain(object.getString("main"));
//			datosMeteo.setDescripcion(object.getString("description"));
//			datosMeteo.setImg(object.getString("icon"));
//
//			JSONObject object2 = jsonObject.getJSONObject("main");
//			datosMeteo.setTemp(object2.getString("temp"));
//			datosMeteo.setTemp_min(object2.getString("temp_min"));
//			datosMeteo.setTemp_max(object2.getString("temp_max"));
//
//			datosMeteo.setPresion(object2.getString("pressure"));
//			datosMeteo.setHumedad(object2.getString("humidity"));
//
//			JSONObject object3 = jsonObject.getJSONObject("wind");
//			datosMeteo.setVelocidad(object3.getString("speed"));
//			datosMeteo.setGrado(object3.getString("deg"));
//
//		} catch (JSONException e) {
//			Log.e("JSON Parser", "Error parsing data " + e.toString());
//		}
//		
//		return datosMeteo;
//
//	}
	
	public Object parseJSONgoogleapisList(String stringJSON) {
		
		//DatosMeteoList datosMeteoList = parseJSONBusqueda(stringJSON);
		DatosMeteoList datosMeteoList = new DatosMeteoList(stringJSON);
		DatosMeteoList datosMeteoListNew = new DatosMeteoList();
		for (int i=0; i< datosMeteoList.getdatosMeteoList().size(); i++) {

			Double lat = datosMeteoList.getdatosMeteoList().get(i).getLat(); 
			Double log = datosMeteoList.getdatosMeteoList().get(i).getLog();  
			
			//stringJSON = downloadUrl(lat, log,"sp","metric"); 
			InputStream streamJSON = getResources().openRawResource(R.raw.weather_test);

			/*-------------------------
			 *  De Stream a String
			 *-------------------------*/
			BufferedReader buffReader = null;
			buffReader = new BufferedReader(new InputStreamReader(streamJSON,Charset.forName("UTF-8")));

			StringBuffer buffer = new StringBuffer();
			String s = null;
			try {
				while((s = buffReader.readLine()) != null) {
					buffer.append(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			stringJSON = buffer.toString();
			
			//DatosMeteo datosMeteo = parseJSONweatherworl(stringJSON);
			DatosMeteo datosMeteo = new DatosMeteo(stringJSON);

			
			datosMeteoListNew.add(datosMeteo);
			
			
			//mDatosMeteoList.add(datosMeteo);
		}
		
		datosMeteoList = datosMeteoListNew;
		
		return datosMeteoList;
	}


}
