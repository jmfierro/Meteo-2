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


public class MeteoCiudadDetalle extends MeteoMenuActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meteo_ciudad_detalle);
		
		//  Home del ActionBar
		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 

		
		// Datos del pronóstico de una ciudad
		ImageView  imgPronos  = (ImageView) findViewById(R.id.imgCiudadDetallePronos);
//		imgPronos.setImageDrawable(this.getResources().getDrawable(R.raw.meteo_rain));
		imgPronos.setImageDrawable(this.getResources().getDrawable(R.drawable.meteo_rain));
		
		TextView textTemp = (TextView) findViewById(R.id.textCiudadTemp);
		TextView textCiudadNomb = (TextView) findViewById(R.id.textCiudadNomb);
		TextView textCiudadPaisNomb = (TextView) findViewById(R.id.textCiudadDetallePaisNomb);
		
	}

	@Override
	protected void onResume() {
		/*----------------------------------------------------------
		 * Para saber si la avtividad es visible 
		 * (gestion de menus desde MeteoMenuActionBarActivity)
		 *---------------------------------------------------------*/
		activityResumed(MeteoCiudadDetalle.class);
		super.onResume(); 
	}

	@Override
	protected void onPause() {
		/*----------------------------------------------------------
		 * Para saber si la avtividad es visible 
		 * (gestion de menus desde MeteoMenuActionBarActivity)
		 *---------------------------------------------------------*/
		activityPaused();
		super.onPause();
	}

	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			Intent upIntent = NavUtils.getParentActivityIntent(this);
//			
//			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
//	            // This activity is NOT part of this app's task, so create a new task
//	            // when navigating up, with a synthesized back stack.
//				TaskStackBuilder.create(this)
//	                    // Add all of this activity's parents to the back stack
//						.addNextIntent(upIntent)
//	                    // Navigate up to the closest parent
//						.startActivities();
//			}
//			else {
//				NavUtils.navigateUpTo(this, upIntent);
//			}
//			
//			return true;
//
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//	}

}
