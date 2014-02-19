
/**
 * @Copyright MNTSOFT
 * 
 */

package com.mnt.mobiletrack.bean;

/**
 * @author pvenkateswarlu
 * @version 1.0 17-09-2013
 */
public class CountrysList {
	private int countryId;
	private String countryName;
	private String countryCode;
	private String countryPhoneCode;

	/**
	 * @return the countryId
	 */
	
	public int getCountryId() {
		return countryId;
	}

	public String getCountryPhoneCode() {
		return countryPhoneCode;
	}

	public void setCountryPhoneCode(String countryPhoneCode) {
		this.countryPhoneCode = countryPhoneCode;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
