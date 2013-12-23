package com.utad.android.meteojmfierro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MeteoAdapter extends BaseAdapter {
	
	 private Context mMeteoContext = null;
	 
	 /*--------------------------------------------------------------
	  * 	Constructor: guarda el referencia a la actividad principal
	  * como contexto 
	  *-------------------------------------------------------------*/
	 public MeteoAdapter(Context aContext) {
		 
		 this.mMeteoContext = aContext;
	
	 }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		/*-------------------------------------
		 *  Si no existe se crea la vista
		 *-------------------------------------*/
		if(convertView == null) {
			
			LayoutInflater inflater = (LayoutInflater) mMeteoContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.lista_ciudades_item, null);
		}
		
		ImageView iconoPronostico = (ImageView) convertView.findViewById(R.id.iconoPronostico);
		iconoPronostico.setImageDrawable(mMeteoContext.getResources().getDrawable(R.raw.meteo_rain));
		
		TextView textCiudad = (TextView) convertView.findViewById(R.id.textCiudadNomb);
		textCiudad.setText("Ciudad");
		TextView textCiudadTemp = (TextView) convertView.findViewById(R.id.textCiudadTemp); 
				
		return convertView;
	}

}
