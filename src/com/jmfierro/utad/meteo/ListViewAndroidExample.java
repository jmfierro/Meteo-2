package com.jmfierro.utad.meteo;


import com.jmfierro.utad.meteo.datos.DatosMeteo;
import com.jmfierro.utad.meteo.datos.DatosMeteoList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewAndroidExample extends Activity implements OnClickListener{
	
	public static final String EXTRA= "DatosMeteo";
	public static final String RETURN_RESULT = "return_result";
	public static final String QUESTION_TEXT_EXTRA = "questionText";
	public static final int ACCEPTED = 1;
	public static final int CANCELLED = 0;

    ListView listView ;
	private DatosMeteoList mDatosMeteoList = new DatosMeteoList();

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_localidad_listview);
        
        listView = (ListView) findViewById(R.id.list);
        
        
        Intent intent= this.getIntent();
		mDatosMeteoList = intent.getParcelableExtra(EXTRA);
		if (mDatosMeteoList == null){ 
			finish();
			return;
		}


		String [] arraystringLocalidades  = new String[mDatosMeteoList.getSize()];
		for (int i=0; i<mDatosMeteoList.getSize(); i++) {
			arraystringLocalidades[i] = mDatosMeteoList.getdatosMeteoList().get(i)
											.getNombre();
		}

		
//        // Defined Array values to show in ListView
//        String[] values = new String[] { "Android List View", 
//                                         "Adapter implementation",
//                                         "Simple List View In Android",
//                                         "Create List View Android", 
//                                         "Android Example", 
//                                         "List View Source Code", 
//                                         "List View Array Adapter", 
//                                         "Android Example List View" 
//                                        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, arraystringLocalidades);

        findViewById(R.id.background).setOnClickListener(this);

        listView.setAdapter(adapter); 
        listView.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> parent, View view,
	                 int position, long id) {
                
	               // ListView Clicked item index
	               int itemPosition     = position;
	               
	               // ListView Clicked item value
	               String  itemValue    = (String) listView.getItemAtPosition(position);
	                  
//	                // Show Alert 
//	                Toast.makeText(getApplicationContext(),
//	                  "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
//	                  .show();
	     
	                Intent returnIntent = new Intent();
	                //returnIntent.putExtra(RETURN_RESULT,Integer.toString(itemPosition));
	                DatosMeteo datosMeteo = new DatosMeteo();
	                datosMeteo = mDatosMeteoList.getdatosMeteoList().get(itemPosition);
	                returnIntent.putExtra(RETURN_RESULT,datosMeteo);
	                setResult(RESULT_OK,returnIntent);     
	                finish();
	              }

         }); 
    }


	@Override
	public void onClick(View arg0) {
		setResult(CANCELLED);
		finish();
		
	}

}