package com.jmfierro.utad.meteo.ut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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

	
	/**====================
	 *  De Stream a String
	 *=====================*/
	static public String stringJSONfromStream(InputStream streamJSON) {
		
		BufferedReader buffReader = null;
		buffReader = new BufferedReader(new InputStreamReader(streamJSON,Charset.forName("UTF-8")));

		StringBuffer buffer = new StringBuffer();
		String s = null;
		try {
			while((s = buffReader.readLine()) != null) {
				buffer.append(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String stringJSON = buffer.toString();
		return stringJSON;
	}


}
