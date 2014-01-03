package com.jmfierro.utad.meteo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*-------------------------------------------------------------
 * >> 	Gestiona el menu segun la actividad que esta visible.
 *-------------------------------------------------------------*/
//@SuppressLint("NewApi")
public class MeteoMenuActionBarActivity extends ActionBarActivity implements TextWatcher {
	
	private EditText et;
	private String title;
	private boolean isSearchView;
	private InputMethodManager imm;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		if (MeteoLocalidadDetalle.class.hashCode() == getClass().hashCode()) {
			getMenuInflater().inflate(R.menu.localidad_detalle, menu);
		}
		else if (MeteoMainActivity.class.hashCode() == getClass().hashCode()) {

			getMenuInflater().inflate(R.menu.lista_localidades, menu);


			// Titulo de la ActionBar 
			title = (String) getTitle();

			// Teclado
			imm = (InputMethodManager)getSystemService(
					getApplication().INPUT_METHOD_SERVICE);
			//Context.INPUT_METHOD_SERVICE);

			// EditText para la busqueda
			et=(EditText)findViewById(R.id.edittext1);
			et.addTextChangedListener(this);

			/*
			 * Ocultar teclado
			 */
			//et.setVisibility(View.INVISIBLE);
			//et.setVisibility(View.GONE);
			//et.requestFocus();
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			//imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
			isSearchView = false;
			//	       int heigh = et.getHeight();
			//	       et.setHeight(10);
		}


	 		
		//**********************************
//        MenuItem menuItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Type something...");
//        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
//        View searchPlate = searchView.findViewById(searchPlateId);
//        if (searchPlate!=null) {
//            searchPlate.setBackgroundColor(Color.DKGRAY);
//            int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
//            TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
//            if (searchText!=null) {
//	            searchText.setTextColor(Color.WHITE);
//	            searchText.setHintTextColor(Color.WHITE);
//            }
//        }
// 		
		//**********************************
		
		
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
			Toast.makeText(getApplicationContext(), "Se ha pulsado: Search", Toast.LENGTH_SHORT).show();
//			et=(EditText)findViewById(R.id.edittext1);
//            et.addTextChangedListener(this);
//	        et.setVisibility(View.VISIBLE);
			
			// Inicializa EditText para la busqueda
			et.setText("");
			isSearchView = true;
			
			// Muestra teclado
        	imm.showSoftInput(et, 0);


			return true;

		case R.id.action_add_location:
			Toast.makeText(getApplicationContext(), "Se ha pulsado: Add_location", Toast.LENGTH_SHORT).show();
			
			return true;
			
		case R.id.action_settings:
			Toast.makeText(getApplicationContext(), "Se ha pulsado: Settings", Toast.LENGTH_SHORT).show();
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


	@Override
	public void afterTextChanged(Editable s) {

		if (isSearchView) {
			String findpalindrome=et.getText().toString();
			//setTitle(findpalindrome);
			String textIntro="";
			int length=findpalindrome.length();

			for (int i = 0; i<length; i++) {
				if (findpalindrome.charAt(i) == '\n') {

	            	/*
	            	 * Oculta teclado
	            	 */
	            	imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
					
					//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

					/*
					 * Restaura tile
					 */
					setTitle(title);
					

					/*
					 * Oculta EditText
					 */
					//et.setVisibility(View.INVISIBLE);
					isSearchView = false;

				}

				else {
					textIntro = textIntro + findpalindrome.charAt(i);
					setTitle(textIntro);
				}


			}

		}
         
//        if(reverse.equalsIgnoreCase(findpalindrome) && length>0)
//            setTitle("String is a palindrome");
//        else
//            setTitle("String is not a palindrome");
 		
	}


	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		
	}


	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		
	}
}
