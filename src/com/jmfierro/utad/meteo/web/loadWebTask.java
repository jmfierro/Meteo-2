package com.jmfierro.utad.meteo.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jmfierro.utad.meteo.MeteoMainActivity;
import com.jmfierro.utad.meteo.R;
import com.jmfierro.utad.meteo.datos.DatosMeteo;
import com.jmfierro.utad.meteo.ut.Utils;
import com.jmfierro.utad.meteo.web.WebServicio.OnWebServicioListener;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**==================================================================
 * Tarea en segundo plano para realizar la petición al servicio web
 * ==================================================================*/
public abstract class loadWebTask extends AsyncTask<Double, Void, Object> {

	// Métodos a implementar por el cliente
	abstract public InputStream loadRaw();
	abstract public Object parseJSON(String stringJSON);
	abstract public void onSetDatos(Object object);
	abstract public  void updateMyView(Object object); 


	protected Object doInBackground(Double... params) {

		// Web Service
		Log.d(loadWebTask.class.getSimpleName(),"doInBackground");
		
		Double lat = params[0]; 
		Double log = params[1];  
		
		//String stringJSON = downloadUrl(lat, log,"sp","metric");
		String stringJSON = downloadUrl();
		Object object = parseJSON(stringJSON);
		
		return object;
		


		//return downloadWeb(dLatitud,dLongitud,"sp","metric");
	}
	

	/**===========================
	 * Finalización del hilo. 
	 * Aviso callbacks al cliente
	 *===========================*/
		public void onPostExecute(Object object) {
		Log.d(WebServicio.class.getSimpleName(),"onPostExecute()");
		//super.onPostExecute(result);
		
		/*
		 * >> Aviso al cliente cuuando la base de datos esta acutalizada.
		 */
		onSetDatos(object);
		updateMyView(object);
	}
	
//	/**===============================
//	 * Descarga datos de la web
//	 *================================*/
//	private Object downloadWeb(double aLat, double aLong, String aIdioma, String aUnidades) {
//		//ArrayList<DatosMeteo> arrayListDatosMeteo = null;
//		
//		Double dLatitud = aLat;
//		Double dLongitud = aLong;
//		String stringJSON = null;
//		InputStream streamJSON = null;
//		
//		HttpURLConnection con = null;
//		try {
//			/*-----------------------------------
//			 *  Si hay conexión a internet.
//			 *----------------------------------*/
//			if(false) { //Utils.isNet()) {
//
//				/*
//				 *  Crear una nueva lista de pares valor/clave para utilizar como parámetros en la solicitud
//				 */
//				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//				
//				/*
//				 * Añade el nombre y valor.
//				 */
//				pairs.add(new BasicNameValuePair("APPID",   "AIzaSyDlwLnt1rTsG1f5GAIv1y0bdN04oS-3i4M"));
//				pairs.add(new BasicNameValuePair("lat",  String.valueOf(dLatitud) ));
//				pairs.add(new BasicNameValuePair("lon",   String.valueOf(dLongitud)));
//				pairs.add(new BasicNameValuePair("lang",  "sp"));
//				pairs.add(new BasicNameValuePair("units",  "metric"));
//
//				// Create a nueva URL
//				Log.d(loadWebTask.class.getSimpleName(),"http://api.openweathermap.org/data/2.5/weather" +"?"
//						+ URLEncodedUtils.format(pairs,"utf-8"));
//				URL url = new URL("http://api.openweathermap.org/data/2.5/weather" +"?"+ URLEncodedUtils.format(pairs,"utf-8"));
//				//URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Madrid");
//
//				// Obtiene nueva conexión.
//				con = (HttpURLConnection) url.openConnection();
//				con.setRequestMethod("GET");
//				
//				// Set petición para JSON.
//				con.setRequestProperty("Accept", "application/json"); 
//
//				// Precesa la respuesta.
//				//reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//				streamJSON = con.getInputStream();
//			}
//
//			/*-----------------------------------
//			 *  Si no hay conexión a internet.
//			 *----------------------------------*/
//			else {
//				//Toast.makeText(getContext().this, "No hay conexión a internet !!!", Toast.LENGTH_LONG).show();
////				mJSONStream = getContext().getResources()
////			              .openRawResource(R.raw.googleapis_leon);
//				streamJSON = loadRaw();
//			}
//
//			/*-------------------------
//			 *  De Stream a String
//			 *-------------------------*/
//			BufferedReader buffReader = null;
//			buffReader = new BufferedReader(new InputStreamReader(streamJSON,Charset.forName("UTF-8")));
//
//			StringBuffer buffer = new StringBuffer();
//			String s = null;
//			while((s = buffReader.readLine()) != null) {
//				buffer.append(s);
//			}
//
//			stringJSON = buffer.toString();
//			Log.d("app",stringJSON);
//			
//			
//		}
//		catch (UnsupportedEncodingException uee) { 
//			Log.d("DEBUG", "UnsupportedEncodingException while processing the PUT friend's name");
//		}
//		catch (IOException ioe) {
//			Log.d("DEBUG", "IOException while processing the PUT friend's email");
//		} 
//		finally {
//			// Release connection
//			if (con != null)
//				con.disconnect();
//		}
//	
//		/*------------------------------
//		 * Parse objeto JSON
//		 *------------------------------*/
//				
//		return parseJSON(stringJSON);
//		
//	} // ** Fin downloadWeb() **


	/**===============================
	 * Descarga datos de la web
	 *================================*/
	protected String downloadUrl(double aLat, double aLong, String aIdioma, String aUnidades) {
		//ArrayList<DatosMeteo> arrayListDatosMeteo = null;
		
		Double dLatitud = aLat;
		Double dLongitud = aLong;
		String stringJSON = null;
		InputStream streamJSON = null;
		
		HttpURLConnection con = null;
		try {
			/*-----------------------------------
			 *  Si hay conexión a internet.
			 *----------------------------------*/
			if(Utils.isNet()) {

				/*
				 *  Crear una nueva lista de pares valor/clave para utilizar como parámetros en la solicitud
				 */
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				
				/*
				 * Añade el nombre y valor.
				 */
				pairs.add(new BasicNameValuePair("APPID",   "AIzaSyDlwLnt1rTsG1f5GAIv1y0bdN04oS-3i4M"));
				pairs.add(new BasicNameValuePair("lat",  String.valueOf(dLatitud) ));
				pairs.add(new BasicNameValuePair("lon",   String.valueOf(dLongitud)));
				pairs.add(new BasicNameValuePair("lang",  "sp"));
				pairs.add(new BasicNameValuePair("units",  "metric"));

				// Create a nueva URL
				Log.d(loadWebTask.class.getSimpleName(),"http://api.openweathermap.org/data/2.5/weather" +"?"
						+ URLEncodedUtils.format(pairs,"utf-8"));
				URL url = new URL("http://api.openweathermap.org/data/2.5/weather" +"?"+ URLEncodedUtils.format(pairs,"utf-8"));
				//URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Madrid");

				// Obtiene nueva conexión.
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				
				// Set petición para JSON.
				con.setRequestProperty("Accept", "application/json"); 

				// Precesa la respuesta.
				//reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				streamJSON = con.getInputStream();
			}


			/*-------------------------
			 *  De Stream a String
			 *-------------------------*/
			BufferedReader buffReader = null;
			buffReader = new BufferedReader(new InputStreamReader(streamJSON,Charset.forName("UTF-8")));

			StringBuffer buffer = new StringBuffer();
			String s = null;
			while((s = buffReader.readLine()) != null) {
				buffer.append(s);
			}

			stringJSON = buffer.toString();
			Log.d("app",stringJSON);
			
			
		}
		catch (UnsupportedEncodingException uee) { 
			Log.d("DEBUG", "UnsupportedEncodingException while processing the PUT friend's name");
		}
		catch (IOException ioe) {
			Log.d("DEBUG", "IOException while processing the PUT friend's email");
		} 
		finally {
			// Release connection
			if (con != null)
				con.disconnect();
		}
	
		return stringJSON;
		
	} // ** Fin downloadUrl(lat,log,idioma,ud) **



	/**===============================
	 * Descarga datos de la web
	 *================================*/
	protected String downloadUrl() {

		String stringJSON = null;
		InputStream streamJSON = null;
		
		try {
			/*-----------------------------------
			 *  Si hay conexión a internet.
			 *----------------------------------*/
				//Toast.makeText(getContext().this, "No hay conexión a internet !!!", Toast.LENGTH_LONG).show();
//				mJSONStream = getContext().getResources()
//			              .openRawResource(R.raw.googleapis_leon);
				streamJSON = loadRaw();

			/*-------------------------
			 *  De Stream a String
			 *-------------------------*/
			BufferedReader buffReader = null;
			buffReader = new BufferedReader(new InputStreamReader(streamJSON,Charset.forName("UTF-8")));

			StringBuffer buffer = new StringBuffer();
			String s = null;
			while((s = buffReader.readLine()) != null) {
				buffer.append(s);
			}

			stringJSON = buffer.toString();
			Log.d("app",stringJSON);
			
			
		}
		catch (UnsupportedEncodingException uee) { 
			Log.d("DEBUG", "UnsupportedEncodingException while processing the PUT friend's name");
		}
		catch (IOException ioe) {
			Log.d("DEBUG", "IOException while processing the PUT friend's email");
		} 
		finally {
		}
	
		return stringJSON;
	} // ** Fin downloadUrl(void) **

	
} // ** FIN TasK **
