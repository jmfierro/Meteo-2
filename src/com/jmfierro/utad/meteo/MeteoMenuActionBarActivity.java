package com.jmfierro.utad.meteo;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/*-------------------------------------------------------------
 * >> 	Gestiona el menu segun la actividad que esta visible.
 *-------------------------------------------------------------*/
public class MeteoMenuActionBarActivity extends ActionBarActivity {
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (MeteoMainActivity.class.hashCode() == getClass().hashCode())
			getMenuInflater().inflate(R.menu.lista_localidades, menu);
		if (MeteoLocalidadDetalle.class.hashCode() == getClass().hashCode())
			getMenuInflater().inflate(R.menu.localidad_detalle, menu);
			
			return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {

		case android.R.id.home:
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
	            // This activity is NOT part of this app's task, so create a new task
	            // when navigating up, with a synthesized back stack.
				TaskStackBuilder.create(this)
	                    // Add all of this activity's parents to the back stack
						.addNextIntent(upIntent)
	                    // Navigate up to the closest parent
						.startActivities();
			}
			else {
				NavUtils.navigateUpTo(this, upIntent);
			}
			
			return true;

		case R.id.action_search:
			Toast.makeText(getApplicationContext(), "Se ha pulsado: Search", Toast.LENGTH_LONG).show();
			
			return true;

		case R.id.action_add_location:
			Toast.makeText(getApplicationContext(), "Se ha pulsado: Add_location", Toast.LENGTH_LONG).show();
			
			return true;
			
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "Se ha pulsado: Settings", Toast.LENGTH_LONG).show();
			return true;

		// Intent implicito, el sistema abre las aplicaiones a elegir
		case R.id.action_share:
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, "mMeteoDatos");
			startActivity(intent);
			
			return true;

		default:
			
			return super.onOptionsItemSelected(item);
		}
	}
}
