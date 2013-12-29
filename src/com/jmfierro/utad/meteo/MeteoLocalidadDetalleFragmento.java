package com.jmfierro.utad.meteo;

import com.jmfierro.utad.meteo.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MeteoLocalidadDetalleFragmento extends Fragment {
	
	private static final String EXTRA_IMAGEN = "img";
	
	
	/**==============================================
	 * Constructor vacio para que pueda instanciarse
	 *===============================================*/
	public MeteoLocalidadDetalleFragmento() {
	}

	/**=========================
	 * Recoge parámetros Bundle
	 *==========================*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.ciudad_detalle);
//		
//		//  Home del ActionBar
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
		
	}
	
	/**=============================================
	 * Se crea la vista y se asocia con el layout.
	 * [en lugar de usar setContentView()]
	 *==============================================*/
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		
//		View viewFrag = inflater.inflate(R.layout.localidad_detalle_fragmento, container, false);
//		
//		// Datos del pronóstico de una ciudad
//		ImageView  imgPronos  = (ImageView) viewFrag.findViewById(R.id.imgCiudadDetallePronos);
////		imgPronos.setImageDrawable(this.getResources().getDrawable(R.raw.meteo_rain));
//		imgPronos.setImageDrawable(this.getResources().getDrawable(R.drawable.meteo_rain));
//		
//		TextView textTemp = (TextView) viewFrag.findViewById(R.id.textCiudadTemp);
//		TextView textCiudadNomb = (TextView) viewFrag.findViewById(R.id.textCiudadNomb);
//		TextView textCiudadPaisNomb = (TextView) viewFrag.findViewById(R.id.textCiudadDetallePaisNomb);
//		
//		return viewFrag;
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View viewFrag = inflater.inflate(R.layout.localidad_detalle_fragmento, container, false);
		
		// Datos del pronóstico de una ciudad
		ImageView  imgPronos  = (ImageView) viewFrag.findViewById(R.id.imgCiudadDetallePronos);
//		imgPronos.setImageDrawable(this.getResources().getDrawable(R.raw.meteo_rain));
	    //image.setImageResource(imageresource);
	    
	    		
		TextView textTemp = (TextView) viewFrag.findViewById(R.id.textCiudadTemp);
		TextView textCiudadNomb = (TextView) viewFrag.findViewById(R.id.textCiudadNomb);
		TextView textCiudadPaisNomb = (TextView) viewFrag.findViewById(R.id.textCiudadDetallePaisNomb);
		
		return viewFrag;
//		return super.onCreateView(inflater, container, savedInstanceState);
	}
}





