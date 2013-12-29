package com.jmfierro.utad.meteo;

import com.jmfierro.utad.meteo.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MeteoLocalidadDetalle extends MeteoMenuActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loacalidad_detalle);
		
		//  Home del ActionBar
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 

		
		// Datos del pronóstico de una localidad
		ImageView  imgPronos  = (ImageView) findViewById(R.id.img_LocalidadDetalle_Pronos);
//		imgPronos.setImageDrawable(this.getResources().getDrawable(R.raw.meteo_rain));
		imgPronos.setImageDrawable(this.getResources().getDrawable(R.drawable.meteo_rain));
		
		TextView textTemp = (TextView) findViewById(R.id.text_LocalidadDetalle_Temp);
		TextView textLocalidadNomb = (TextView) findViewById(R.id.text_LocalidadDetalle_Nomb);
		TextView textLocalidadPaisNomb = (TextView) findViewById(R.id.text_LocalidadDetalle_PaisNomb);
		
	}

}
