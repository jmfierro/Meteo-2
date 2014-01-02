package com.jmfierro.utad.meteo.datos;



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
	private int id;
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
				null,null,null);
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
					  int id,
					  String main,
					  String descripcion,
					  String img)
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
			int id = source.readInt();
			String main = source.readString();
			String descripcion  = source.readString();
			String img = source.readString();
			
			
			String nombre = source.readString();
			
			return new DatosMeteo(log,lat,temp,presion,humedad,
					temp_min,temp_max,velocidad,grado,id,main,descripcion,img);
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
		dest.writeInt(this.id);
		dest.writeString(this.main);
		dest.writeString(this.descripcion);
		dest.writeString(this.img);
		
		dest.writeString(this.nombre);
	}
	
}
