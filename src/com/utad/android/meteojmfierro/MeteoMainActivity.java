package com.utad.android.meteojmfierro;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MeteoMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_ciudades);
		
		final ListView lv = (ListView) findViewById(R.id.listaCiudades);
		lv.setAdapter(new MeteoAdapter(this));
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> aParent, View aView, int aPosition,
					long aId) {
				Toast.makeText(MeteoMainActivity.this, "Pulsado item #" + aPosition, Toast.LENGTH_SHORT).show();
				lv.setItemChecked(aPosition, true);
				
				Intent intent = new Intent(MeteoMainActivity.this,MeteoCiudadDetalle.class);
				startActivity(intent);
				
			}
			});	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.meteo_main, menu);
		return true;
	}

}
