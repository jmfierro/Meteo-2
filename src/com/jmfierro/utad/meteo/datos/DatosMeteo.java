package com.jmfierro.utad.meteo.datos;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.os.Parcel;
import android.os.Parcelable;

public class DatosMeteo implements Parcelable{
	
	private double log;
	private double lat;
	
	// Varios
	private String temp = null;
	private String presion = null; 
	private String humedad = null; 
	private String temp_min = null; 
	private String temp_max = null;
	
	//Viento
	private String velocidad = null; 
	private String grado = null;
	
	//General
	private long id;
	private String main = null;
	private String descripcion  = null;
	private String img = null;
	
	
	private String nombre = null;
	
	/**==================
	 * Constructor vacio
	 *===================*/
	public DatosMeteo() {
		
		new DatosMeteo(0,0,
				null,null,null,null,null,null,null,
				0,
				null,null,null,null);
	}
	

	/**==================
	 * Constructor set
	 *===================*/
	public DatosMeteo(double log, 
					  double lat,
					  String temp,
					  String presion,
					  String humedad,
					  String temp_min,
					  String temp_max,
					  String velocidad,
					  String grado,
					  long id,
					  String main,
					  String descripcion,
					  String img,
					  String nombre)
	{
		
				  this.log = log; 
				  this.lat = lat;
				  this.temp = temp;
				  this.presion = presion;
				  this.humedad = humedad;
				  this.temp_min = temp_min;
				  this.temp_max = temp_max;
				  this.velocidad = velocidad;
				  this.grado = grado;
				  this.id = id;
				  this.main = main;
				  this.descripcion = descripcion;
				  this.img  =img;
				  this.nombre=nombre;
	}
	

	/**=====================================================
	 * Constructores para JSON (String, Stream y JSONObject)
	 *======================================================*/
	public DatosMeteo(String stringJSON) {
		
		try {
			new DatosMeteo(new JSONObject(stringJSON));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DatosMeteo(InputStream streamJSON) {
			
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

		new DatosMeteo(buffer.toString());

	}
	
	public DatosMeteo(JSONObject jsonObject) {
		try {
//		String id = datosMeteo.getJSONObject("id").getString("$t");
//		int _i = id.lastIndexOf('-');
//		long idLong = Long.parseLong( id.substring(_i + 1) );
		
//		String date = datosMeteo.getJSONObject("published").getString("$t");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		Date dateDate = sdf.parse(date);
		
		//JSONObject jsonObject = new JSONObject(stringJSON);

		//datosMeteo = new DatosMeteo();

		//datosMeteo.setNombre(jsonObject.getString("name"));
		this.nombre = jsonObject.getString("name");

		JSONArray array = jsonObject.getJSONArray("weather");
		JSONObject object = array.getJSONObject(0);

//		datosMeteo.setMain(object.getString("main"));
//		datosMeteo.setDescripcion(object.getString("description"));
//		datosMeteo.setImg(object.getString("icon"));
		this.main = object.getString("main");
		this.descripcion  = object.getString("description");
		this.img = object.getString("icon");

		JSONObject object2 = jsonObject.getJSONObject("main");
//		datosMeteo.setTemp(object2.getString("temp"));
//		datosMeteo.setTemp_min(object2.getString("temp_min"));
//		datosMeteo.setTemp_max(object2.getString("temp_max"));
		this.temp = object2.getString("temp");
		this.temp_min = object2.getString("temp_min");
		this.temp_max = object2.getString("temp_max");

//		datosMeteo.setPresion(object2.getString("pressure"));
//		datosMeteo.setHumedad(object2.getString("humidity"));
		this.presion = object2.getString("pressure");
		this.humedad = object2.getString("humidity");

		JSONObject object3 = jsonObject.getJSONObject("wind");
//		datosMeteo.setVelocidad(object3.getString("speed"));
//		datosMeteo.setGrado(object3.getString("deg"));
		this.velocidad = object3.getString("speed");
		this.grado = object3.getString("deg");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	
	
	/*-------------------
	 * Getters & Setters
	 *-------------------*/
	
	public double getLog() {
		return log;
	}

	public void setLog(double log) {
		this.log = log;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getPresion() {
		return presion;
	}

	public void setPresion(String presion) {
		this.presion = presion;
	}

	public String getHumedad() {
		return humedad;
	}

	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}

	public String getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}

	public String getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre; 
	}

	
	
	/*----------------------------------------------
	 * Metodos Parcelable
	 *----------------------------------------------*/
	public static final Parcelable.Creator<DatosMeteo> CREATOR = new Creator<DatosMeteo>() {

		@Override
		public DatosMeteo createFromParcel(Parcel source) {
			double log = source.readDouble();
			double lat = source.readDouble();
			
			// Varios
			String temp = source.readString();
			String presion = source.readString(); 
			String humedad = source.readString(); 
			String temp_min = source.readString(); 
			String temp_max = source.readString();
			
			//Viento
			String velocidad = source.readString(); 
			String grado = source.readString();
			
			//General
			long id = source.readLong();
			String main = source.readString();
			String descripcion  = source.readString();
			String img = source.readString();
			
			
			String nombre = source.readString();
			
			return new DatosMeteo(log,lat,temp,presion,humedad,
					temp_min,temp_max,velocidad,grado,id,main,descripcion,img,nombre);
		}

		@Override
		public DatosMeteo[] newArray(int size) {
			return new DatosMeteo[size];
		}
		
	};

	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(this.log);
		dest.writeDouble(this.lat);
		
		// Varios
		dest.writeString(this.temp);
		dest.writeString(this.presion);
		dest.writeString(this.humedad);
		dest.writeString(this.temp_min);
		dest.writeString(this.temp_max);
		
		//Viento
		dest.writeString(this.velocidad);
		dest.writeString(this.grado);
		
		//General
		dest.writeLong(this.id);
		dest.writeString(this.main);
		dest.writeString(this.descripcion);
		dest.writeString(this.img);
		
		dest.writeString(this.nombre);
	}
	
}
