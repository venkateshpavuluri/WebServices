/**
 * 
 */
package com.mnt.mobiletrack.bean;

/**
 * @author venkateshp
 *
 */
public class MobileGEOCode {
	
	private int mobileGEOCodeId;
	private String  iMIENumber;
	
	private String  latitude;
	private String  longitude;
	private String  dT;
	public int getMobileGEOCodeId() {
		return mobileGEOCodeId;
	}
	public void setMobileGEOCodeId(int mobileGEOCodeId) {
		this.mobileGEOCodeId = mobileGEOCodeId;
	}
	public String getiMIENumber() {
		return iMIENumber;
	}
	public void setiMIENumber(String iMIENumber) {
		this.iMIENumber = iMIENumber;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getdT() {
		return dT;
	}
	public void setdT(String dT) {
		this.dT = dT;
	}
	
	
	
}
