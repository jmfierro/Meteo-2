package com.jmfierro.utad.meteo.ut;

import java.util.ArrayList;

import com.jmfierro.utad.meteo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class ListAdapterGenerico extends BaseAdapter {

	//private ArrayList<?> entradas; 
	private int mR_layout_IdView; 
	private Context mMeteoContext;

	//private Context mMeteoContext = null;

	/**=======================================================================================================
	 *  Devuelve las vistas de cada item.
	 *  
	 * @param aItem El item que será la asociada a la view. La entrada es del tipo del paquete/handler
	 * @param view View particular que contendrá los datos del paquete/handler
	 *=========================================================================================================*/
	//public abstract void onSetItemForListAdapterGenerico (Object aItem, View view);
	public abstract void onSetItemForListAdapterGenerico (View view);

	/**==============================================================
	 * Constructor: guarda la referencia a la actividad principal
	 * 				como contexto 
	 *===============================================================*/
	//public ListAdapterGenerico(Context contexto, int R_layout_IdView, ArrayList<?> entradas) {
	public ListAdapterGenerico(Context contexto, int R_layout_IdView) {
		super();
		this.mMeteoContext = contexto;
		//this.entradas = entradas; 
		this.mR_layout_IdView = R_layout_IdView; 
	}



	@Override
	public int getCount() {

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
			//convertView = inflater.inflate(R.layout.ciudades_lista_item, null);
			convertView = inflater.inflate(mR_layout_IdView, null);
		}

		/*------------------------------------------------------------------
		 * Rellena la vista del item en el método que utiliza el adaptador.
		 *------------------------------------------------------------------*/
		//onSetItemForListAdapterGenerico (entradas.get(position), convertView);
		onSetItemForListAdapterGenerico (convertView);

		//		ImageView iconoPronostico = (ImageView) convertView.findViewById(R.id.iconoPronostico);
		//		iconoPronostico.setImageDrawable(mMeteoContext.getResources().getDrawable(R.raw.meteo_rain));
		//		
		//		TextView textCiudad = (TextView) convertView.findViewById(R.id.textCiudadNomb);
		//		textCiudad.setText("Ciudad");
		//		TextView textCiudadTemp = (TextView) convertView.findViewById(R.id.textCiudadTemp); 

		return convertView;
	}

}
