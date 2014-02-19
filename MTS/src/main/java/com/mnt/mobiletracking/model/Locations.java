/**
 * 
 */
package com.mnt.mobiletracking.model;




/**
 * @author venkateshp
 *
 */
public class Locations {

	private java.util.List<Location> locations;

	
	   @javax.xml.bind.annotation.XmlElement(name="location")
	public java.util.List<Location> getLocations() {
		return locations;
	}

	public void setLocations(java.util.List<Location> locations) {
		this.locations = locations;
	}
	
}
