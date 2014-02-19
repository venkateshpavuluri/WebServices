package com.mnt.mobiletrack.bean;

import java.io.Serializable;

public class Mobile implements Serializable{
	private String latitude;
	private String source;
	private String destination;
	
	public String getLatitude() {
		return latitude;
	}
	public String getSource() {
		return source;
	}
	public String getDestination() {
		return destination;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

}
