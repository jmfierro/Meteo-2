package com.jmfierro.utad.meteo;

import com.jmfierro.utad.meteo.R;
import com.jmfierro.utad.meteo.datos.DatosMeteo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MeteoLocalidadDetalle extends MeteoMenuActionBarActivity {
	
	public static final String EXTRA = "DatosMeteo"; 
	private DatosMeteo mDatosMeteo = new DatosMeteo();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.localidad_detalle);
		
		
		// Obtiene los datos meteorologicos.
		Intent intent= this.getIntent();
		mDatosMeteo = intent.getParcelableExtra(EXTRA);
		if (mDatosMeteo == null){ 
			finish();
			return;
		}
	
		//  Home del ActionBar
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 

		/*------------------------------------------------------------
		 * Compueba que previamente no hay entrado en esta actividad 
		 * (al rotar el dispositivo,...). 
		 * -> añade el fragmento al contenedor. 
		 *------------------------------------------------------------*/
		if (savedInstanceState == null) {

			
//			PruebaFragment fragmento = new PruebaFragment();
//			getSupportFragmentManager().beginTransaction()
//			.add(R.id.contenedorFrag_LocalidadDetalle, fragmento).commit();
			
			
			// Argumentos a enviar al fragmento
			Bundle arg = new Bundle();
			arg.putParcelable(MeteoLocalidadDetalleFragmento.EXTRA, mDatosMeteo);
			
			MeteoLocalidadDetalleFragmento fragmento = new MeteoLocalidadDetalleFragmento();
			fragmento.setArguments(arg);
			getSupportFragmentManager().beginTransaction()
			.add(R.id.contenedorFrag_LocalidadDetalle, fragmento).commit();
		}
	}

}
