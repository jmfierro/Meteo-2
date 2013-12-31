package com.jmfierro.utad.meteo;

import com.jmfierro.utad.meteo.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
		setContentView(R.layout.localidad_detalle);
		
		//  Home del ActionBar
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 

		/*------------------------------------------------------------
		 * Compueba que previamente no hay entrado en esta actividad 
		 * (al rotar el dispositivo,...). 
		 * -> añade el fragmento al contenedor. 
		 *------------------------------------------------------------*/
		if (savedInstanceState == null) {
			
			// Crea el fragmento del detalle y lo añade el fragmento al contenedor.
//			Bundle arg = new Bundle();
//			Drawable d = this.getResources().getDrawable(R.drawable.meteo_rain);
//			Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
//			arg.puteputExtra("Bitmap", d);
			MeteoLocalidadDetalleFragmento fragmento = new MeteoLocalidadDetalleFragmento();
			getSupportFragmentManager().beginTransaction()
			.add(R.id.contenedorFrag_LocalidadDetalle, fragmento).commit();
		}
	}

}
