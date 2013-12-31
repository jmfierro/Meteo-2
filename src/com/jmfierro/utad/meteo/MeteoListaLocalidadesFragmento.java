package com.jmfierro.utad.meteo;

import com.jmfierro.utad.meteo.ut.ListAdapterGenerico;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MeteoListaLocalidadesFragmento extends ListFragment {
	
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
	public MeteoListaLocalidadesFragmento() {
		// TODO Auto-generated constructor stub
	}
	
	/**================================
	 * Rellena cada item del listado.
	 *=================================*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*------------------------------
		 * Llama la adptador generico.
		 *------------------------------*/
		setListAdapter(new ListAdapterGenerico(getActivity(),R.layout.item_lista_localidades){
			
			/*--------------------------------------------------------------------------
			 * Rellena un item para que lo use el adaptador ListAdapterGenerico.
			 *--------------------------------------------------------------------------*/
			@Override 
			public void onSetItemForListAdapterGenerico(View view) {
						ImageView iconoPronostico = (ImageView) view.findViewById(R.id.img_Item_ListaLocalidades_Pronos);
						iconoPronostico.setImageDrawable(getActivity().getResources().getDrawable(R.raw.meteo_rain));
						
						TextView textLocalidad = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Nomb);
						textLocalidad.setText("Localidad");
						TextView textLocalidadTemp = (TextView) view.findViewById(R.id.text_Item_ListaLocalidades_Temp); 
				
			}
			
		});

	}

	@Override
	public void onStart() { 
		super.onStart();
		if (getActivity().getApplication().getResources().getBoolean(R.bool.tablet))
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
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
	 * Escucha la pulsación sobre un item de la lista. 
	 * Se usa el Callback para notificar a la Actividad el id pulsado.
	 *================================================================*/
	@Override
	public void onListItemClick(ListView listView, View view, int posicion, long id) {
		super.onListItemClick(listView, view, posicion, id);		
		//mCallbacks.onEntradaSelecionada(Lista_contenido.ENTRADAS_LISTA.get(posicion).id); 
		listView.setItemChecked(posicion, true);
	
		mCallbacks.onItemSeleccionado(Integer.toString(posicion));
	}
	
	
}
