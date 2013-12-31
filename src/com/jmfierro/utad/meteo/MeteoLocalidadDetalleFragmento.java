package com.jmfierro.utad.meteo;

import com.jmfierro.utad.meteo.R;
import com.jmfierro.utad.meteo.weather.DatosMeteo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MeteoLocalidadDetalleFragmento extends Fragment {
	
	public static final String EXTRA= "extra";
	private DatosMeteo w;
	private String mTextTemp;
	private DatosMeteo mDatosMeteo;
	
	
	/**==============================================
	 * Constructor vacio para que pueda instanciarse
	 *===============================================*/
	public MeteoLocalidadDetalleFragmento() {
	}

	/**=========================
	 * Recoge parámetros Bundle
	 *==========================*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		
		if (getArguments().containsKey(EXTRA)){
			//mTextTemp = getArguments().getString(EXTRA);
			mDatosMeteo = getArguments().getParcelable(EXTRA);
		}
//		setContentView(R.layout.ciudad_detalle);
//		
//		//  Home del ActionBar
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true); 
		
	}
	
	/**=============================================
	 * Se crea la vista y se asocia con el layout.
	 * [en lugar de usar setContentView()]
	 *==============================================*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View viewFrag = inflater.inflate(R.layout.localidad_detalle_fragmento, container, false);

		if (mDatosMeteo != null) {

		// Datos meteorología de una localidad
		TextView textLocalidadNomb = (TextView) viewFrag.findViewById(R.id.text_LocalidadDetalle_Nomb);
		textLocalidadNomb.setText(mDatosMeteo.getNombre());
		TextView  textPaisNomb  = (TextView) viewFrag.findViewById(R.id.text_LocalidadDetalle_PaisNomb);

		// Temperaturas
		TextView textTemp = (TextView) viewFrag.findViewById(R.id.text_LocalidadDetalle_Temp);
		textTemp.setText((String)this.mDatosMeteo.getTemp() + "º");
//		TextView textTempUd = (TextView) viewFrag.findViewById(R.id.textTempUd);
//		textTempUd.setText("C");
		TextView textTempMaxMin = (TextView) viewFrag.findViewById(R.id.text_LocalidadDetalle_TempMaxMin);
		textTempMaxMin.setText((String)this.mDatosMeteo.getTemp_max() + "ºC - " 
							+ (String)this.mDatosMeteo.getTemp_min() + "ºC");
		
		// Varios
		TextView textDescr = (TextView) viewFrag.findViewById(R.id.textDescripcion);
		textDescr.setText((String)this.mDatosMeteo.getDescripcion());
		TextView textPresion = (TextView) viewFrag.findViewById(R.id.TextPresion);
		textPresion.setText((String)this.mDatosMeteo.getPresion() + " mb");
		TextView textHumedad = (TextView) viewFrag.findViewById(R.id.textHumedad);
		textHumedad.setText((String)this.mDatosMeteo.getHumedad() + " %");
		TextView textVelocidad = (TextView) viewFrag.findViewById(R.id.textVelocidad);
		textVelocidad.setText((String)this.mDatosMeteo.getVelocidad() + " Km/h");
		TextView textGrado = (TextView) viewFrag.findViewById(R.id.textGrado);
		textGrado.setText((String)this.mDatosMeteo.getGrado() + "->");
		
		
		ImageView  imgPronos  = (ImageView) viewFrag.findViewById(R.id.imgCiudadDetallePronos);
//		imgPronos.setImageDrawable(this.getResources().getDrawable(R.raw.meteo_rain));
	    //image.setImageResource(imageresource);
	    
	    		
		TextView textCiudadPaisNomb = (TextView) viewFrag.findViewById(R.id.text_LocalidadDetalle_PaisNomb);
//		if (mTextTemp != null)
//			textTemp.setText((String)this.mTextTemp+"º");
		}
		
		return viewFrag;
//		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
    
    public void updateMyLayout(DatosMeteo w) {
    	//This function paints de layout when
    	Log.d("app","Rellena vista");
    	
//    	((TextView) findViewById(R.id.name)).setText(w.getWName());
//    	((TextView) findViewById(R.id.description)).setText(w.getDescription());
//    	((TextView) findViewById(R.id.main)).setText(w.getMain());
//    	
//    	String iconame = w.getIcon();
//    	
//    	log(iconame);
//    	Resources res = getResources();
//    	int resourceId = res.getIdentifier("w"+iconame, "drawable", getPackageName() );
//    	
//    	
//    	Drawable drawable = res.getDrawable(resourceId );
//    	
//    	ImageView icon = (ImageView) findViewById(R.id.icono);
//    	icon.setImageDrawable(drawable);
//    	
//    	
//    	
//    	((TextView) findViewById(R.id.temp)).setText(w.getTemp());
//    	((TextView) findViewById(R.id.temp_min)).setText(w.getTempMin());
//    	((TextView) findViewById(R.id.temp_max)).setText(w.getTempMax());
//    	
//    	((TextView) findViewById(R.id.pressure)).setText(w.getPressure());
//    	((TextView) findViewById(R.id.humidity)).setText(w.getHumidity());
//    	((TextView) findViewById(R.id.deg)).setText(w.getDeg());
//    	((TextView) findViewById(R.id.speed)).setText(w.getSpeed());
//    	
//    	LinearLayout mainLayout = (LinearLayout)findViewById(R.id.mainlayout);
//        mainLayout.setVisibility(View.VISIBLE);
    }
	
}





