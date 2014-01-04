package com.jmfierro.utad.meteo.datos;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jmfierro.utad.meteo.ut.Utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;



public class DatosMeteoList implements Parcelable{
	
	private ArrayList<DatosMeteo> mDatosMeteoList;
	private TreeMap<Long, DatosMeteo> byId; // Para buscar en el array
	
	public static final Parcelable.Creator<DatosMeteoList> CREATOR = new Creator<DatosMeteoList>() {

		@Override
		public DatosMeteoList createFromParcel(Parcel source) {
			DatosMeteoList datosMeteoList = new DatosMeteoList();
			Parcelable[] datosMeteoArray = source
					.readParcelableArray( DatosMeteoList.class.getClassLoader() );
			if (datosMeteoList != null) {
				for (Parcelable datosMeteo : datosMeteoArray) {
					datosMeteoList.add((DatosMeteo) datosMeteo);
				}
			}
			
			return datosMeteoList;
		}

		@Override
		public DatosMeteoList[] newArray(int size) {
			return new DatosMeteoList[size];
		}
		
	};
	
	//Constructor
	public DatosMeteoList() {
		mDatosMeteoList= new ArrayList<DatosMeteo>();
		byId = new TreeMap<Long, DatosMeteo>();
	}
	
	
	//public DatosMeteoList parseJSONBusqueda(String stringJSON) {
	public DatosMeteoList (String stringJSONBusqueda) {
		
//		ArrayList<DatosMeteo> arraylistDatosMeteo = new ArrayList<DatosMeteo>();
		DatosMeteoList datosMeteoList = new DatosMeteoList();
		try {

			JSONObject jsonObject = new JSONObject(stringJSONBusqueda);

			if (jsonObject.getString("status").equals("OK")) {
				JSONArray localidades = jsonObject.getJSONArray("results"); 

				for (int i=0; i < localidades.length(); i++) {
					JSONObject objMain = localidades.getJSONObject(i);


					String nombre = objMain.getString("formatted_address");
//					TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
//					textLocalidad.setText(nombre);
					DatosMeteo datosMeteo = new DatosMeteo();
					datosMeteo.setNombre(nombre);
					datosMeteo.setId(i);
					this.add(datosMeteo);

					JSONObject objGeo = objMain.getJSONObject("geometry");
					JSONObject objLoc = objGeo.getJSONObject("location");
					Double lat = objLoc.getDouble("lat");
					Double log = objLoc.getDouble("lng");
				}

			}
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		
	}

	
	/**================================
	 * De JSON Object a DatosMeteoList
	 *=================================*/
	static public DatosMeteoList parseJSONBusqueda(InputStream streamJSON) {
		
		String stringJSON = Utils.stringJSONfromStream(streamJSON);
		//DatosMeteoList datosMeteoList = parseJSONBusqueda(stringJSON);
		//DatosMeteoList datosMeteoList = new DatosMeteoList(stringJSON);
		DatosMeteoList datosMeteoList = new DatosMeteoList();
		datosMeteoList = DatosMeteoList.parseJSONBusqueda(stringJSON);
		
		return datosMeteoList;
	}	
	
	static public DatosMeteoList parseJSONBusqueda(String stringJSON) {
		
//		ArrayList<DatosMeteo> arraylistDatosMeteo = new ArrayList<DatosMeteo>();
		DatosMeteoList datosMeteoList = new DatosMeteoList();
		try {

			JSONObject jsonObject = new JSONObject(stringJSON);

			if (jsonObject.getString("status").equals("OK")) {
				JSONArray localidades = jsonObject.getJSONArray("results"); 

				for (int i=0; i < localidades.length(); i++) {
					JSONObject objMain = localidades.getJSONObject(i);


					String nombre = objMain.getString("formatted_address");
//					TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
//					textLocalidad.setText(nombre);
					DatosMeteo datosMeteo = new DatosMeteo();
					datosMeteo.setNombre(nombre);

					JSONObject objGeo = objMain.getJSONObject("geometry");
					JSONObject objLoc = objGeo.getJSONObject("location");
					Double lat = objLoc.getDouble("lat");
					datosMeteo.setLat(lat);
					Double log = objLoc.getDouble("lng");
					datosMeteo.setLog(log);

					datosMeteoList.add(datosMeteo); 
				}

			}
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		return datosMeteoList;
	}


	public void add(DatosMeteo datosMeteo) {
		add(new DatosMeteo[]{ datosMeteo });
	}

	private void add(DatosMeteo... datosMeteoArray) { //recibe array de datosMeteo
		// Convierte un array en una lista
		this.mDatosMeteoList.addAll(Arrays.asList(datosMeteoArray));
		
		for (DatosMeteo datosMeteo : datosMeteoArray) {
			this.byId.put(datosMeteo.getId(), datosMeteo);
		}
	}
	

	public DatosMeteo get(long id) {  // Devuelve el datosMeteo
		return byId.get(id);
	}
	
	
	public int getPosition(long id) {
		DatosMeteo datosMeteo = get( id );
		return mDatosMeteoList.indexOf( datosMeteo );
		
	}


	
	public ArrayList<DatosMeteo> getdatosMeteoList() {
		return mDatosMeteoList;
	}
	

//	private static DatosMeteoList singleInstance = null;
//	// para evitar que se construya cada vez que se le llame. 
//	// Devuelve un sólo un datosMeteo
//	public synchronized static DatosMeteoList getInstance() {
//		//DatosMeteoList ret = new DatosMeteoList();
//		
//		if (singleInstance == null){ 		   // La primera vez utiliza el constructor,
//			singleInstance = new DatosMeteoList();   // (Cada threard crearía uno.)
//		
//			singleInstance.add(
//					new DatosMeteo(101, new Date(), "http://www.u-tad.com", "Primer datosMeteo", null, "Este es el extracto del blog.",
//							"<p><b>Lorem ipsum</b> dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
//							+ "<p><b>Lorem ipsum</b> dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
//							+ "<p><b>Lorem ipsum</b> dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"),
//					new DatosMeteo(102, new Date(), "http://www.u-tad.com", "Segundo datosMeteo", null, "Este es el extracto del blog.", "Este es el <b>contenido</b> del blog."),
//					new DatosMeteo(103, new Date(), "http://www.u-tad.com", "Tercer datosMeteo", null, "Este es el extracto del blog.", "Este es el <b>contenido</b> del blog."),
//					new DatosMeteo(104, new Date(), "http://www.u-tad.com", "Cuarto datosMeteo", null, "Este es el extracto del blog.", "Este es el <b>contenido</b> del blog."),
//					new DatosMeteo(105, new Date(), "http://www.u-tad.com", "Quinto datosMeteo", null, "Este es el extracto del blog.", "Este es el <b>contenido</b> del blog."),
//					new DatosMeteo(106, new Date(), "http://www.u-tad.com", "Sexto datosMeteo", null, "Este es el extracto del blog.", "Este es el <b>contenido</b> del blog.")
//			
//			);
//		}
//			
//		return singleInstance;
//	}
	
	@Override
	public int describeContents() {
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		DatosMeteo[] datosMeteoArray = this.mDatosMeteoList.toArray( new DatosMeteo[0] );
		dest.writeParcelableArray(datosMeteoArray, 0);
	}

	public int getSize () {
		return mDatosMeteoList.size();
	}
	
} // Fin clase DatosMeteoList
