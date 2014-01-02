package com.jmfierro.utad.meteo;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jmfierro.utad.meteo.datos.DatosMeteo;
import com.jmfierro.utad.meteo.ut.ListAdapterGenerico;
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
	public ArrayList<DatosMeteo> mArrayListDatosMeteo = new ArrayList<DatosMeteo>();
	
	public interface Callbacks {
		public void onMyItemViewSeleccionado(String id);
	}
	 
	private static Callbacks CallbacksVacios = new Callbacks() {

		@Override
		public void onMyItemViewSeleccionado(String id) {
			
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
		
		//ArrayList<DatosMeteo> arrayListDatosMeteo = new ArrayList<DatosMeteo>();
//		BuscarLocalidadTask bl = new BuscarLocalidadTask();
		loadWebTask bl = new loadWebTask() {

//			@Override
//			public void updateMyView(ArrayList<DatosMeteo> arrayListDatosMeteo, String stringJSON) {
//				//Toast.makeText(getApplicationContext(), "updateMyView()", Toast.LENGTH_SHORT).show();
//				try {
//					//Generate the jsonObject form respobnse
//					JSONObject jsonObject = new JSONObject(stringJSON);
//					//					ArrayList<DatosMeteo> arrayListDatosMeteo = null;
//
//					if (jsonObject.getString("status").equals("OK")) {
//						JSONArray localidades = jsonObject.getJSONArray("results"); 
//
//						for (int i=0; i < localidades.length(); i++) {
//							JSONObject objMain = localidades.getJSONObject(i);
//
//
//							//String nombre = String.format(j.getString("formatted_address"),"UTF-8");
//							String nombre = objMain.getString("formatted_address");
////							TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
////							textLocalidad.setText(nombre);
//							DatosMeteo datosMeteo1 = new DatosMeteo();
//							datosMeteo1.setNombre(nombre);
//							arrayListDatosMeteo.add(datosMeteo1);
//
//							JSONObject objGeo = objMain.getJSONObject("geometry");
//							JSONObject objLoc = objGeo.getJSONObject("location");
//							Double lat = objLoc.getDouble("lat");
//							Double log = objLoc.getDouble("lng");
//						}
//
//					}
//				} catch (JSONException e) {
//					Log.e("JSON Parser", "Error parsing data " + e.toString());
//				}
//
//			}

			@Override
			public InputStream loadRaw() {
//				mJSONStream = mActividadContexto.getResources()
//		 		.openRawResource(R.raw.googleapis_lista_localidades);
				return getResources()
				 		.openRawResource(R.raw.googleapis_lista_localidades);
			}
			@Override
			public Object parseJSON(String stringJSON) {
				//Toast.makeText(getApplicationContext(), "updateMyView()", Toast.LENGTH_SHORT).show();
				
				ArrayList<DatosMeteo> arraylistDatosMeteo = new ArrayList<DatosMeteo>();
				try {
					//Generate the jsonObject form respobnse
					JSONObject jsonObject = new JSONObject(stringJSON);
					//					ArrayList<DatosMeteo> arrayListDatosMeteo = null;

					if (jsonObject.getString("status").equals("OK")) {
						JSONArray localidades = jsonObject.getJSONArray("results"); 

						for (int i=0; i < localidades.length(); i++) {
							JSONObject objMain = localidades.getJSONObject(i);


							//String nombre = String.format(j.getString("formatted_address"),"UTF-8");
							String nombre = objMain.getString("formatted_address");
//							TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
//							textLocalidad.setText(nombre);
							DatosMeteo datosMeteo1 = new DatosMeteo();
							datosMeteo1.setNombre(nombre);
							((ArrayList<DatosMeteo>) arraylistDatosMeteo)
											.add(datosMeteo1);

							JSONObject objGeo = objMain.getJSONObject("geometry");
							JSONObject objLoc = objGeo.getJSONObject("location");
							Double lat = objLoc.getDouble("lat");
							Double log = objLoc.getDouble("lng");
						}

					}
				} catch (JSONException e) {
					Log.e("JSON Parser", "Error parsing data " + e.toString());
				}
				return arraylistDatosMeteo;
			}

			@Override
			public void onSetDatos(Object object) {
				mArrayListDatosMeteo = (ArrayList<DatosMeteo>) object;
				int i=0;
				i=6;
			}
			@Override
			public void updateMyView(Object object) {
				ArrayList<DatosMeteo> kk = new ArrayList<DatosMeteo>();
				kk = (ArrayList<DatosMeteo>) object;
				mArrayListDatosMeteo = (ArrayList<DatosMeteo>) object;
				mArrayListDatosMeteo = kk;

				/*------------------------------
				 * Llama la adptador generico.
				 *------------------------------*/
				setListAdapter(new ListAdapterGenerico(getActivity(),R.layout.item_lista_localidades, kk){
//				setListAdapter(new ListAdapterGenerico(getActivity(),R.layout.item_lista_localidades, mArrayListDatosMeteo){
					
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

//		bl.setOnWebListener(new OnWebListener() { 
//			
//			@Override
//			public InputStream loadRaw() {
////				mJSONStream = mActividadContexto.getResources()
////		 		.openRawResource(R.raw.googleapis_lista_localidades);
//				return getResources()
//				 		.openRawResource(R.raw.googleapis_lista_localidades);
//			}
//			@Override
//			public DatosMeteo parseJSON(Object arraylistDatosMeteo, String stringJSON) {
//				//Toast.makeText(getApplicationContext(), "updateMyView()", Toast.LENGTH_SHORT).show();
//				try {
//					//Generate the jsonObject form respobnse
//					JSONObject jsonObject = new JSONObject(stringJSON);
//					//					ArrayList<DatosMeteo> arrayListDatosMeteo = null;
//
//					if (jsonObject.getString("status").equals("OK")) {
//						JSONArray localidades = jsonObject.getJSONArray("results"); 
//
//						for (int i=0; i < localidades.length(); i++) {
//							JSONObject objMain = localidades.getJSONObject(i);
//
//
//							//String nombre = String.format(j.getString("formatted_address"),"UTF-8");
//							String nombre = objMain.getString("formatted_address");
////							TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
////							textLocalidad.setText(nombre);
//							DatosMeteo datosMeteo1 = new DatosMeteo();
//							datosMeteo1.setNombre(nombre);
//							((ArrayList<DatosMeteo>) arraylistDatosMeteo)
//											.add(datosMeteo1);
//
//							JSONObject objGeo = objMain.getJSONObject("geometry");
//							JSONObject objLoc = objGeo.getJSONObject("location");
//							Double lat = objLoc.getDouble("lat");
//							Double log = objLoc.getDouble("lng");
//						}
//
//					}
//				} catch (JSONException e) {
//					Log.e("JSON Parser", "Error parsing data " + e.toString());
//				}
//				return null;
//			}
//			
//			@Override
//			public void onSetDatos(Object object) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		
//		bl.setContext(getActivity());
//		bl.setView(view);
//		bl.setmArrayListDatosMeteo(mArrayListDatosMeteo);
//		bl.setmActivity(getActivity());
		bl.execute(40.6,-3.6);


		
	}

	
	public void updateMyLayout(ArrayList<DatosMeteo> arrayDatosMeteo) {
		
			mArrayListDatosMeteo = arrayDatosMeteo;
			/*------------------------------
			 * Llama la adptador generico.
			 *------------------------------*/
			setListAdapter(new ListAdapterGenerico(getActivity(),R.layout.item_lista_localidades, mArrayListDatosMeteo){
				
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
	
		mCallbacks.onMyItemViewSeleccionado(Integer.toString(posicion));
	}
	
	
}
