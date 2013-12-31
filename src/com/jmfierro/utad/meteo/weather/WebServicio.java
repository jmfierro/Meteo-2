package com.jmfierro.utad.meteo.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jmfierro.utad.meteo.R;
import com.jmfierro.utad.meteo.ut.Utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class WebServicio extends Service { 
	
	private OnWebListener mWebListener; 
	
	/*---------------------------------------------------------
	 * Métodos para callbacks entre Actividade cliente y Servicio:
 	 *---------------------------------------------------------*/
	
	/*
	 * >> Método callback para la Actividad cliente.
	 */
	public interface OnWebListener {
		public void onSetDatosMeteo(DatosMeteo DatosMeteo);
	}

	public void setOnWebListener (OnWebListener aWebListener) {
		this.mWebListener = aWebListener;
	}
	
	/* 
	 * >> Binder para recibir llamadas de las Actividades cliente.
	 *
	 *
	 *  >> Clase usada por la Actividad cliente para comunicarse con el servicio (Actividad -> Servicio),
	 * devuelve el objeto servicio.
	 *  
	 * (El método onBind() debe devolver una referencia a este metodo para permitir la conexión al servicio.)
	 */
	public class WebBinder extends Binder {
		public WebServicio getService(){ 
			return WebServicio.this;
		}
	}
	
	/*----------------------------------------------------
	 * Método al que acceden los Clientes desde su proceso
	 *----------------------------------------------------*/
	public void loadWebDatosMeteo(){
		Log.d(WebServicio.class.getSimpleName(),"Descarga y actualización de datos");
		
		/*
		 * Acceso a la web en un hilo separado 
		 */
		//new LoadWedTask().execute("http://api.openweathermap.org/data/2.5/weather?q=Madrid");
		new LoadWedTask().execute(40.6,-3.6);
	}
	
	
	/*--------------------------------------------------------------
	 * Sobrescritura de métodos de la clase Service para binder:
	 *--------------------------------------------------------------*/

	/*
	 * >> Recibe la peticiones de conexión al servicio por parte de clientes.
	 * 	  Debe devolver un IBinder para que se establezca la conexión.
	 */
	@Override
	public IBinder onBind(Intent arg0) { 
		return new WebBinder();
	}
	
	
	/*
	 * >> Finalización del Servicio.
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		this.mWebListener = null;
		return super.onUnbind(intent);
	}
	
	/**
	 * Nuevo hilo para conexión a internet y guardar en base de datos.
	 */
	public class LoadWedTask extends AsyncTask<Double, Void, DatosMeteo>{
	
		String response = null;
		
		double dLatitud;
		double dLongitud;
		
		DatosMeteo mDatosMeteo = null;
		InputStream mJSONStream;
		
		/*-----------------------------------------------------------------
		 * Mantener una referencia de la actividad padre para ser capaz de 
		 * cambiar su interfaz
		 *-----------------------------------------------------------------*/
		private Context mDatosMeteoContexto = null;
		
		/**================================================
		 * Nuevo hilo: conexión a internet y 
		 * guarda de base de datos.
		 *=================================================*/
		@Override
		protected DatosMeteo doInBackground(Double... params) {
			Log.d(WebServicio.class.getSimpleName(),"doInBackground: " + params[0]);
			
			dLatitud = params[0];
			dLongitud = params[1];
			DatosMeteo datosMeteo = downloadWeb(dLatitud,dLongitud,"sp","metric");
			
			return datosMeteo;
		}


		/**===========================
		 * Finalización del hilo.
		 * Aviso callbacks al cliente
		 *===========================*/
		@Override
		protected void onPostExecute(DatosMeteo datosMeteo) {
			Log.d(WebServicio.class.getSimpleName(),"onPostExecute()");
			//super.onPostExecute(result);
			/*
			 * >> Aviso al cliente cuuando la base de datos esta acutalizada.
			 */
			if (WebServicio.this.mWebListener != null)
				WebServicio.this.mWebListener.onSetDatosMeteo(datosMeteo);
			
		}
		
		public Context getContext() {
			return mDatosMeteoContexto;
		}

		public void setContext(Context context) {
			this.mDatosMeteoContexto = context;
		}

		/**===============================
		 * Descarga datos de la web
		 * 
		 * @param aLat
		 * @param aLong
		 * @param aIdioma
		 * @param aUnidades
		 * @return
		 *================================*/
		private DatosMeteo downloadWeb(double aLat, double aLong, String aIdioma, String aUnidades) {

			HttpURLConnection con = null;
			try {
//				/*-----------------------------------
//				 *  Si hay conexión a internet.
//				 *----------------------------------*/
//				if(Utils.isNet()) {
//					// Create a new list of name-pair values to use as parameters in the request
//					List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//					// Add the name and friend's name
//
//					pairs.add(new BasicNameValuePair("APPID",   "AIzaSyDlwLnt1rTsG1f5GAIv1y0bdN04oS-3i4M"));
//					pairs.add(new BasicNameValuePair("lat",  String.valueOf(aLat) ));
//					pairs.add(new BasicNameValuePair("lon",   String.valueOf(aLong)));
//					pairs.add(new BasicNameValuePair("lang",  "sp")); //aIdioma
//					pairs.add(new BasicNameValuePair("units",  "metric")); //aUnidades
//
//					// Create a new URL
//					//			if(isNet()){
//					Log.d(LoadWedTask.class.getSimpleName(),"http://api.openweathermap.org/data/2.5/weather" +"?"
//							+ URLEncodedUtils.format(pairs,"utf-8"));
//					URL url = new URL("http://api.openweathermap.org/data/2.5/weather" +"?"+ URLEncodedUtils.format(pairs,"utf-8"));
//					//URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Madrid");
//					// Get a new connection to the required resource
//					con = (HttpURLConnection) url.openConnection();
//					con.setRequestMethod("GET");
//					// Set the header to ask for JSON replies
//					con.setRequestProperty("Accept", "application/json"); 
//
//					// Process the response using a BufferedReader
//
//					//reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//					mJSONStream = con.getInputStream();
//				}
//
//				/*-----------------------------------
//				 *  Si no hay conexión a internet.
//				 *----------------------------------*/
//				else {
//					//Toast.makeText(getContext().this, "No hay conexión a internet !!!", Toast.LENGTH_LONG).show();
////					mJSONStream = mDatosMeteoContexto.getResources()
////							.openRawResource(R.raw.weather_test);
//					mJSONStream = getApplication().getResources()
//							.openRawResource(R.raw.weather_test);
//					//				 reader = new BufferedReader(
//					//						new InputStreamReader(
//					//								mWeatherContexto.getResources().openRawResource(R.raw.weather_test)));
//				}


				mJSONStream = getApplication().getResources()
							.openRawResource(R.raw.weather_test);

				/*-------------------------
				 *  De Stream a String
				 *-------------------------*/
				BufferedReader buffReader = null;
				buffReader = new BufferedReader(new InputStreamReader(mJSONStream));
				StringBuffer buffer = new StringBuffer();
				String s = null;
				while((s = buffReader.readLine()) != null) {
					buffer.append(s);
				}

				response = buffer.toString();
				Log.d("app",response);
				
				
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


			/*------------------------------
			 * Parse objeto JSON
			 *------------------------------*/
			try {
				//Generate the jsonObject form respobnse
				JSONObject jsonObject = new JSONObject(response);

				mDatosMeteo = new DatosMeteo();
	        	
	        	mDatosMeteo.setNombre(jsonObject.getString("name"));
	        	
	        	JSONArray array = jsonObject.getJSONArray("weather");
	        
	        	JSONObject object = array.getJSONObject(0);

	        	mDatosMeteo.setMain(object.getString("main"));
	        	mDatosMeteo.setDescripcion(object.getString("description"));
	        	mDatosMeteo.setImg(object.getString("icon"));
	        	
	        	JSONObject object2 = jsonObject.getJSONObject("main");
	        	mDatosMeteo.setTemp(object2.getString("temp"));
	        	mDatosMeteo.setTemp_min(object2.getString("temp_min"));
	        	mDatosMeteo.setTemp_max(object2.getString("temp_max"));
	        	
	        	mDatosMeteo.setPresion(object2.getString("pressure"));
	        	mDatosMeteo.setHumedad(object2.getString("humidity"));
	        	
	        	JSONObject object3 = jsonObject.getJSONObject("wind");
	        	mDatosMeteo.setVelocidad(object3.getString("speed"));
	        	mDatosMeteo.setGrado(object3.getString("deg"));
	        	
	        } catch (JSONException e) {
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }
	 
			
			return mDatosMeteo;
			
		}


	} // FIN Task ************************************************


}
