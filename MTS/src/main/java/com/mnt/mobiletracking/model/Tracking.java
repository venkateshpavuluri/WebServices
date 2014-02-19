package com.mnt.mobiletracking.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tracking {
	
private String mobileDeviceId;

	private Locations locationslist;

	private Calls callslist;
	
	private Messages messageslist;
	/**
	 * @return the locations
	 */
	@XmlElement(name="locations")
	public Locations getLocationslist() {
		return locationslist;
	}
	/**
	 * @param locations the locations to set
	 */
	public void setLocationslist(Locations locationslist) {
		this.locationslist = locationslist;
	}
	/**
	 * @return the calls
	 */
	@XmlElement(name="calls")
	public Calls getCallslist() {
		return callslist;
	}
	/**
	 * @param calls the calls to set
	 */
	public void setCallslist(Calls callslist) {
		this.callslist = callslist;
	}
	/**
	 * @return the messages
	 */
	@XmlElement(name="messages")
	public Messages getMessageslist() {
		return messageslist;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessageslist(Messages messageslist) {
		this.messageslist = messageslist;
	}
	/**
	 * @return the mobileDeviceId
	 */
	@XmlAttribute(name="deviceid")
	public String getMobileDeviceId() {
		return mobileDeviceId;
	}
	/**
	 * @param mobileDeviceId the mobileDeviceId to set
	 */
	public void setMobileDeviceId(String mobileDeviceId) {
		this.mobileDeviceId = mobileDeviceId;
	}
	
	
	

}
