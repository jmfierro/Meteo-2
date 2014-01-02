package com.jmfierro.utad.meteo.web;

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

import com.jmfierro.utad.meteo.MeteoListaLocalidadesFragmento;
import com.jmfierro.utad.meteo.MeteoMainActivity;
import com.jmfierro.utad.meteo.R;
import com.jmfierro.utad.meteo.datos.DatosMeteo;
import com.jmfierro.utad.meteo.datos.DatosMeteoList;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class WebServicio extends Service { 

	private OnWebServicioListener mWebListener; 
	private DatosMeteoList mDatosMeteoList = new DatosMeteoList();

	/*---------------------------------------------------------
	 * Métodos para callbacks entre Actividade cliente y Servicio:
	 *---------------------------------------------------------*/


	/*
	 * >> Método callback para la Actividad cliente.
	 */
	public interface OnWebServicioListener {
		public void onSetDatosMeteo(DatosMeteoList datosMeteoList);
		public DatosMeteo parseJSON(DatosMeteo datosMeteo,String stringJSON);
		public DatosMeteo parseJSON(String stringJSON);

	}

	public void setOnWebServicioListener (OnWebServicioListener aWebListener) {
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
	public void loadWebDatosMeteo() {
		Log.d(WebServicio.class.getSimpleName(),"Descarga y actualización de datos");

		/*
		 * Acceso a la web en un hilo separado 
		 */
//		new LoadWedTask().execute("http://api.openweathermap.org/data/2.5/weather?q=Madrid");
//		new LoadWedTask().execute(40.6,-3.6);
		loadWebTask bl = new loadWebTask() {

			@Override
			public InputStream loadRaw() {
				return getApplication().getResources()
				         .openRawResource(R.raw.weather_test);
			}

			@Override
			public Object parseJSON(String stringJSON) {
				/*------------------------------
				 * Parse objeto JSON
				 *------------------------------*/
//				if (mWebListener != null)
//					mDatosMeteo = WebServicio.this.mWebListener.parseJSON(mDatosMeteo, stringJSON);
				return WebServicio.this.mWebListener.parseJSON(stringJSON);
			}

			@Override
			public void onSetDatos(Object object) {
				mDatosMeteoList.add((DatosMeteo) object);
			}

			@Override
			public void updateMyView(Object object) {
//				DatosMeteoList datosMeteoList = new DatosMeteoList();
//				datosMeteoList.add((DatosMeteo) object);
				if (WebServicio.this.mWebListener != null)
					WebServicio.this.mWebListener.onSetDatosMeteo(mDatosMeteoList);
				
			}
			
		};

		/*----------------
		 * Lanza hilo
		 *----------------*/
//		DatosMeteoList d  = new DatosMeteoList(); 
//		d = MeteoListaLocalidadesFragmento.getArrayListDatosMeteo();
		bl.execute(40.6,-3.6);
		
//		if (WebServicio.this.mWebListener != null)
//			WebServicio.this.mWebListener.onSetDatosMeteo(mDatosMeteoList);
		
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


}
