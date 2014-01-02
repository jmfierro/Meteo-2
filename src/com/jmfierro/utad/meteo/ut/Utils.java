package com.jmfierro.utad.meteo.ut;

import com.jmfierro.utad.meteo.Config;
import com.jmfierro.utad.meteo.MeteoMainActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class Utils {
	
	
	/**=======================================
	 * Comprueba de hay conexión de internet.	
	 * ====================================== */
	static public boolean isNet() {
        // Gets the URL from the UI's text field.
        //String stringUrl = urlText.getText().toString();
//        ConnectivityManager connMgr = 
//        		(ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager connMgr = 
        		(ConnectivityManager) Config.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //new DownloadWebpageTask().execute(stringUrl);
        	return true;
        } else {
        	return false;
            //textView.setText("No network connection available.");
        }
  
//	return false;
	}


}
