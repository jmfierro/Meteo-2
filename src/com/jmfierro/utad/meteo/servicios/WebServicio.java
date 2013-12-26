package com.jmfierro.utad.meteo.servicios;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class WebServicio extends Service {
	
	private OnWebListener mWebListener;
	
	/*---------------------------------------------------------
	 * Métodos para callbacks entre Actividades clientes y Servicio:
 	 *---------------------------------------------------------*/
	
	/*
	 * >> Método callback para la Actividad cliente.
	 */
	public interface OnWebListener {
		public void onLoadDatosMeteo();
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
	 * (El método onBind() debe devolver un referencia a este metodo para permitir la conexión al servicio.)
	 */
	public class WebBinder extends Binder {
		public WebServicio getService(){ 
			return WebServicio.this;
		}
	}
	
	/*----------------------------------------------------
	 * Método al que acceden los Clientes desde su proceso
	 *----------------------------------------------------*/
	public boolean loadDatosMeteo(){
		Log.d(WebServicio.class.getSimpleName(),"Descarga y actualización de datos");
		/*
		 * >> Aviso al cliente.
		 */
		if (this.mWebListener != null)
			this.mWebListener.onLoadDatosMeteo();
		
		return false;
		
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
