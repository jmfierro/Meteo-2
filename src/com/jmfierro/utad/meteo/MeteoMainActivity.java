package com.jmfierro.utad.meteo;
// package="com.utad.android.meteojmfierro"

import com.jmfierro.utad.meteo.servicios.WebServicio;
import com.jmfierro.utad.meteo.servicios.WebServicio.OnWebListener;
import com.jmfierro.utad.meteo.servicios.WebServicio.WebBinder;
import com.jmfierro.utad.meteo.R;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MeteoMainActivity extends MeteoMenuActionBarActivity implements MeteoListaLocalidadesFragmento.Callbacks {
	
	
	private WebBinder mWebBinder;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_localidades); 

		Log.d(MeteoMainActivity.class.getSimpleName(),"----------");
		Log.d(MeteoMainActivity.class.getSimpleName(),"onCreate()");
		Log.d(MeteoMainActivity.class.getSimpleName(),"-----------");
		
		/*----------------------------------
		 * Descarga de datos meteorológicos
		 *----------------------------------*/
		conexionWebServicio();
		
		/*--------------------
		 * Vista del listview
		 *--------------------*/
//		mListView = (ListView) findViewById(R.id.listaCiudades);
//		mListView.setAdapter(new ListAdapterGenerico(this));
//		setOnClickItemListView();
		
	}
	
	
	
	private void desconexionWebService(){ 
		if (mWebServiceConn != null) {
			Log.d(MeteoMainActivity.class.getSimpleName(),"unbindService(mWebServiceConn)");  
			unbindService(mWebServiceConn);
		}
	}

	private void conexionWebServicio(){
		Log.d(MeteoMainActivity.class.getSimpleName(),"bindService(service, mWebServiceConn, Service.BIND_AUTO_CREATE)");  
		Intent service = new Intent(MeteoMainActivity.this, WebServicio.class);
		bindService(service, mWebServiceConn, Service.BIND_AUTO_CREATE); 
	}
	
	private ServiceConnection mWebServiceConn = new ServiceConnection() {
		
		/**=====================================================
		 * >> Finalización del Servicio por causas desconocidas.
		 *======================================================*/
		@Override
		public void onServiceDisconnected(ComponentName name) { 
			Log.d(MeteoMainActivity.class.getSimpleName(),"Conexión a WedService");
			mWebBinder = null;
		}

		
		/**========================
		 * >> Inicio del Servicio.
		 *=========================*/
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(MeteoMainActivity.class.getSimpleName(),"Conexión a WedService");
			
			/*------------------------------------------------------------------
			 * Obtengo del Binder para acceder al servicio desde esta actividad
			 *------------------------------------------------------------------*/
			mWebBinder = (WebBinder) service;
			
			/*------------------------------------------------
			 * Escucha de mensajes procedentes del Servicio
			 *------------------------------------------------*/
			mWebBinder.getService().setOnWebListener(new OnWebListener() { 
				
				/*
				 * >> Aviso del Servicio de que los datos ya estan actualizados.
				 *    (callback)*/	   
				@Override
				public void onSetDatosMeteo() {
					Log.d(MeteoMainActivity.class.getSimpleName(),"Aviso de WebServicio: datos actualizados!!!");
					desconexionWebService();
				}
			});
			
			/*---------------------------------------------------
			 * Actualizar datos desde el servicio.
			 *--------------------------------------------------*/
			mWebBinder.getService().loadWebDatosMeteo();
		}
	};
	
	
//	private void setOnClickItemListView() {	
//		mListView.setOnItemClickListener(new OnItemClickListener() { 
//
//			@Override
//			public void onItemClick(AdapterView<?> aParent, View aView, int aPosition,
//					long aId) {
//				Toast.makeText(MeteoMainActivity.this, "Pulsado item #" + aPosition, Toast.LENGTH_SHORT).show();
//				mListView.setItemChecked(aPosition, true);
//				
//				Intent intent = new Intent(MeteoMainActivity.this,MeteoCiudadDetalle.class);
//				startActivity(intent);
//				
//			}
//			});
//	}

	/**========================================================================================
	 * CallBack que recibe el item seleccionado desde MeteoListaLocalidadesFragmento.Callbacks
	 *=========================================================================================*/
	@Override
	public void onItemSeleccionado(String id) {
		
		Toast.makeText(this, "Pulsado #" + id, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MeteoMainActivity.this,MeteoLocalidadDetalle.class);
		startActivity(intent);
	}


}
