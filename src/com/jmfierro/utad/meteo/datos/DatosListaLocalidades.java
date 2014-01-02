package com.jmfierro.utad.meteo.datos;

public class DatosListaLocalidades {


	String mNameLoc;
	double mLat;
	double mLog;
	
	public DatosListaLocalidades(String mNameLoc, double mLat, double mLog) {
		super();
		this.mNameLoc = mNameLoc;
		this.mLat = mLat;
		this.mLog = mLog;
	}

	public String getmNameLoc() {
		return mNameLoc;
	}

	public void setmNameLoc(String mNameLoc) {
		this.mNameLoc = mNameLoc;
	}

	public double getmLat() {
		return mLat;
	}

	public void setmLat(double mLat) {
		this.mLat = mLat;
	}

	public double getmLog() {
		return mLog;
	}

	public void setmLog(double mLog) {
		this.mLog = mLog;
	}
	
	
}
