package com.utad.android.meteojmfierro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class MeteoCiudadDetalle extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meteo_ciudad_detalle);
		
		// Datos del pronóstico de una ciudad
		ImageView  imgPronos  = (ImageView) findViewById(R.id.imgCiudadDetallePronos);
//		imgPronos.setImageDrawable(this.getResources().getDrawable(R.raw.meteo_rain));
		imgPronos.setImageDrawable(this.getResources().getDrawable(R.drawable.meteo_rain));
		
		TextView textTemp = (TextView) findViewById(R.id.textCiudadTemp);
		TextView textCiudadNomb = (TextView) findViewById(R.id.textCiudadNomb);
		TextView textCiudadPaisNomb = (TextView) findViewById(R.id.textCiudadDetallePaisNomb);
		
	}

}
