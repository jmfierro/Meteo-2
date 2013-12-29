package com.jmfierro.utad.meteo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MeteoListaCiudadesFragmento extends ListFragment {
	
	/**==================================================================================
	 * Callback que notifique a la Activity de que elemento del listado se ha pulsado.
	 *===================================================================================*/
	private Callbacks mCallbacks = CallbacksVacios;

	public interface Callbacks {
		public void onItemSeleccionado(String id);
	}
	
	private static Callbacks CallbacksVacios = new Callbacks() {

		@Override
		public void onItemSeleccionado(String id) {
			
		}
	};

	
	/**==============================
	 * Necesario constructor vacio. 
	 *===============================*/
	public MeteoListaCiudadesFragmento() {
		// TODO Auto-generated constructor stub
	}
	
	/**================================
	 * Rellena cada item del listado.
	 *=================================*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ListAdapterGenerico(getActivity(),R.layout.item_lista_ciudades){

			@Override
			public void onEntrada(View view) {
						ImageView iconoPronostico = (ImageView) view.findViewById(R.id.imgPronostico);
						iconoPronostico.setImageDrawable(getActivity().getResources().getDrawable(R.raw.meteo_rain));
						
						TextView textCiudad = (TextView) view.findViewById(R.id.textCiudadNomb);
						textCiudad.setText("Ciudad");
						TextView textCiudadTemp = (TextView) view.findViewById(R.id.textCiudadTemp); 
				
			}
			
		});
	}
	
	/**==============================================================================
	 * Asegura que este implementado el Callback en la clase que use a este Fragment.
	 *===============================================================================*/
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException("Error: La actividad debe implementar el callback del fragmento");
		}

		mCallbacks = (Callbacks) activity;
	}
	
	/**=================
	 * Limpia Callbacks.
	 *==================*/
	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = CallbacksVacios;
	}

	/**===============================================================
	 * Escucha la pulsación sobre un elemento un item de la lista. 
	 * Se usa el Callback para notificar a la Actividad del id pulsado.
	 *================================================================*/
	@Override
	public void onListItemClick(ListView listView, View view, int posicion, long id) {
		super.onListItemClick(listView, view, posicion, id);		
		//mCallbacks.onEntradaSelecionada(Lista_contenido.ENTRADAS_LISTA.get(posicion).id);
		mCallbacks.onItemSeleccionado(Integer.toString(posicion));
	}
	
	
}
