package com.jmfierro.utad.meteo;
// package="com.utad.android.meteojmfierro"


import com.jmfierro.utad.meteo.weather.DatosMeteo;
import com.jmfierro.utad.meteo.weather.WebServicio;
import com.jmfierro.utad.meteo.weather.WebServicio.OnWebListener;
import com.jmfierro.utad.meteo.weather.WebServicio.WebBinder;
import com.jmfierro.utad.meteo.R;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MeteoMainActivity extends MeteoMenuActionBarActivity implements MeteoListaLocalidadesFragmento.Callbacks {


	private WebBinder mWebBinder;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meteo_main); 

		Log.d(MeteoMainActivity.class.getSimpleName(),"----------");
		Log.d(MeteoMainActivity.class.getSimpleName(),"onCreate()");
		Log.d(MeteoMainActivity.class.getSimpleName(),"-----------");

		/*----------------------------------
		 * Descarga de datos meteorológicos
		 *----------------------------------*/
		conexionWebServicio();


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


		/**===========================
		 * >> Inicio del Servicio web.
		 *============================*/
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
				public void onSetDatosMeteo(DatosMeteo aDatosMeteo) {
					Log.d(MeteoMainActivity.class.getSimpleName(),"Aviso de WebServicio: datos actualizados!!!");
					desconexionWebService();
					Toast.makeText(MeteoMainActivity.this, aDatosMeteo.getTemp()  
							+" " 
							+ aDatosMeteo.getTemp(), 
							Toast.LENGTH_LONG).show();
//					TextView textTemp = (TextView) findViewById(R.id.textCiudadTemp);
//					textTemp.setText(aWeather.getTemp());
					
//					MeteoLocalidadDetalleFragmento fragmento = new MeteoLocalidadDetalleFragmento();
//					getSupportFragmentManager().beginTransaction()
//					.replace(R.id.contenedorFrag_LocalidadDetalle,fragmento)
//					.commit();

					// Argumentos a enviar al fragmento
					Bundle arg = new Bundle();
					//arg.putString(MeteoLocalidadDetalleFragmento.EXTRA, (String)aWeather.getTemp());
					arg.putParcelable(MeteoLocalidadDetalleFragmento.EXTRA, aDatosMeteo);
					
					// Creación de Fragmento					
					MeteoLocalidadDetalleFragmento fragmento = new MeteoLocalidadDetalleFragmento();
					fragmento.setArguments(arg);
					getSupportFragmentManager().beginTransaction()
					.replace(R.id.contenedorFrag_LocalidadDetalle,fragmento)
					.commit();
					

					
				}
			});

			/*---------------------------------------------------
			 * Actualizar datos desde el servicio.
			 *--------------------------------------------------*/
			mWebBinder.getService().loadWebDatosMeteo();
		}
	};



	/**========================================================================================
	 * CallBack que recibe el item seleccionado desde MeteoListaLocalidadesFragmento.Callbacks
	 *=========================================================================================*/
	@Override
	public void onItemSeleccionado(String id) {

		Toast.makeText(this, "Pulsado #" + id, Toast.LENGTH_SHORT).show();
		
		/*---------------------------------
		 * Pantallas a partir de 7 pulgadas
		 *---------------------------------*/
		if (getResources().getBoolean(R.bool.tablet)) {

//			MeteoLocalidadDetalleFragmento fragmento = new MeteoLocalidadDetalleFragmento();
//			getSupportFragmentManager().beginTransaction()
//			.replace(R.id.contenedorFrag_LocalidadDetalle,fragmento)
//			.commit();

			// Argumentos a enviar al fragmento
			Bundle arg = new Bundle();
			arg.putString(MeteoLocalidadDetalleFragmento.EXTRA, (String)"-");
			
			// Creación de Fragmento					
			MeteoLocalidadDetalleFragmento fragmento = new MeteoLocalidadDetalleFragmento();
			fragmento.setArguments(arg);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.contenedorFrag_LocalidadDetalle,fragmento)
			.commit();
		}

		else {
			Intent intent = new Intent(MeteoMainActivity.this,MeteoLocalidadDetalle.class);
			startActivity(intent);
		}
	}

    
    public void updateMyLayout(DatosMeteo w) {
    	//This function paints de layout when
    	Log.d(getClass().getSimpleName(),"Rellena vista");
    	
//    	((TextView) findViewById(R.id.name)).setText(w.getWName());
//    	((TextView) findViewById(R.id.description)).setText(w.getDescription());
//    	((TextView) findViewById(R.id.main)).setText(w.getMain());
//    	
//    	String iconame = w.getIcon();
//    	
//    	log(iconame);
//    	Resources res = getResources();
//    	int resourceId = res.getIdentifier("w"+iconame, "drawable", getPackageName() );
//    	
//    	
//    	Drawable drawable = res.getDrawable(resourceId );
//    	
//    	ImageView icon = (ImageView) findViewById(R.id.icono);
//    	icon.setImageDrawable(drawable);
//    	
//    	
//    	
//    	((TextView) findViewById(R.id.temp)).setText(w.getTemp());
//    	((TextView) findViewById(R.id.temp_min)).setText(w.getTempMin());
//    	((TextView) findViewById(R.id.temp_max)).setText(w.getTempMax());
//    	
//    	((TextView) findViewById(R.id.pressure)).setText(w.getPressure());
//    	((TextView) findViewById(R.id.humidity)).setText(w.getHumidity());
//    	((TextView) findViewById(R.id.deg)).setText(w.getDeg());
//    	((TextView) findViewById(R.id.speed)).setText(w.getSpeed());
//    	
//    	LinearLayout mainLayout = (LinearLayout)findViewById(R.id.mainlayout);
//        mainLayout.setVisibility(View.VISIBLE);
    }

}
