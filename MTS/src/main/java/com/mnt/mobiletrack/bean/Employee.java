/**
 * 
 */
package com.mnt.mobiletrack.bean;

import java.util.List;

/**
 * @author venkateshp
 * 
 */
public class Employee {

	private int employeeId;
	private String employeeGroupId;
	private String fName;
	private String lName;
	private String mName;
	private String gender;
	private String dob;
	private String doj;
	private String pAdd;
	private String pCity;
	private String pState;
	private String pCountry;
	private String tAdd;
	private String tCity;
	private String tState;
	private String tCountry;
	private String eMail;
	private String[] imieNumber;
	private String[] mobileNo;
	private String status;
	private int aid;
	private String phoneNo;
	private int deliveryhide;
	private String imeiNumber;
	private String mobileNumber;
	private String employeeImeiId;
	private String imeiNumberEdit;
	private String mobileNumberEdit;
	private String countryCode;
	private String signature;
	
	

	
	/*Edit Proiperties*/			
	private String employeeGroupIdEdit;
	private String fNameEdit;
	private String lNameEdit;
	private String mNameEdit;
	private String genderEdit;
	private String dobEdit;
	private String dojEdit;
	private String pAddEdit;
	private String pCityEdit;
	private String pStateEdit;
	private String pCountryEdit;
	private String tAddEdit;
	private String tCityEdit;
	private String tStateEdit;
	private String tCountryEdit;
	private String eMailEdit;
	private String mobileNoEdit;
	private String statusEdit;
	private String imieNumberEdit;
	private int employeeImienoEdit;
	private String countryCodeEdit;
	/*Relationship properties*/
	private List<EmployeeIMIE> employeeImieDetails;
	
	
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getCountryCodeEdit() {
		return countryCodeEdit;
	}
	public void setCountryCodeEdit(String countryCodeEdit) {
		this.countryCodeEdit = countryCodeEdit;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getImieNumberEdit() {
		return imieNumberEdit;
	}

	public void setImieNumberEdit(String imieNumberEdit) {
		this.imieNumberEdit = imieNumberEdit;
	}

	public String getImeiNumberEdit() {
		return imeiNumberEdit;
	}

	public void setImeiNumberEdit(String imeiNumberEdit) {
		this.imeiNumberEdit = imeiNumberEdit;
	}

	public String getMobileNumberEdit() {
		return mobileNumberEdit;
	}

	public void setMobileNumberEdit(String mobileNumberEdit) {
		this.mobileNumberEdit = mobileNumberEdit;
	}



	public String getEmployeeImeiId() {
		return employeeImeiId;
	}

	public void setEmployeeImeiId(String employeeImeiId) {
		this.employeeImeiId = employeeImeiId;
	}

	public String getImeiNumber() {
		return imeiNumber;
	}

	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getDeliveryhide() {
		return deliveryhide;
	}

	public void setDeliveryhide(int deliveryhide) {
		this.deliveryhide = deliveryhide;
	}

	public int getEmployeeImienoEdit() {
		return employeeImienoEdit;
	}

	public void setEmployeeImienoEdit(int employeeImienoEdit) {
		this.employeeImienoEdit = employeeImienoEdit;
	}


	
	
	
	

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	

	/**
	 * @return the employeeImieDetails
	 */
	public List<EmployeeIMIE> getEmployeeImieDetails() {
		return employeeImieDetails;
	}

	/**
	 * @param employeeImieDetails the employeeImieDetails to set
	 */
	public void setEmployeeImieDetails(List<EmployeeIMIE> employeeImieDetails) {
		this.employeeImieDetails = employeeImieDetails;
	}

	

	public String getfNameEdit() {
		return fNameEdit;
	}

	public void setfNameEdit(String fNameEdit) {
		this.fNameEdit = fNameEdit;
	}

	public String getlNameEdit() {
		return lNameEdit;
	}

	public void setlNameEdit(String lNameEdit) {
		this.lNameEdit = lNameEdit;
	}

	public String getmNameEdit() {
		return mNameEdit;
	}

	public void setmNameEdit(String mNameEdit) {
		this.mNameEdit = mNameEdit;
	}

	public String getGenderEdit() {
		return genderEdit;
	}

	public void setGenderEdit(String genderEdit) {
		this.genderEdit = genderEdit;
	}

	public String getDobEdit() {
		return dobEdit;
	}

	public void setDobEdit(String dobEdit) {
		this.dobEdit = dobEdit;
	}

	public String getDojEdit() {
		return dojEdit;
	}

	public void setDojEdit(String dojEdit) {
		this.dojEdit = dojEdit;
	}

	public String getpAddEdit() {
		return pAddEdit;
	}

	public void setpAddEdit(String pAddEdit) {
		this.pAddEdit = pAddEdit;
	}

	public String getpCityEdit() {
		return pCityEdit;
	}

	public void setpCityEdit(String pCityEdit) {
		this.pCityEdit = pCityEdit;
	}

	public String getpStateEdit() {
		return pStateEdit;
	}

	public void setpStateEdit(String pStateEdit) {
		this.pStateEdit = pStateEdit;
	}

	public String getpCountryEdit() {
		return pCountryEdit;
	}

	public void setpCountryEdit(String pCountryEdit) {
		this.pCountryEdit = pCountryEdit;
	}

	public String gettAddEdit() {
		return tAddEdit;
	}

	public void settAddEdit(String tAddEdit) {
		this.tAddEdit = tAddEdit;
	}

	public String gettCityEdit() {
		return tCityEdit;
	}

	public void settCityEdit(String tCityEdit) {
		this.tCityEdit = tCityEdit;
	}

	public String gettStateEdit() {
		return tStateEdit;
	}

	public void settStateEdit(String tStateEdit) {
		this.tStateEdit = tStateEdit;
	}

	public String gettCountryEdit() {
		return tCountryEdit;
	}

	public void settCountryEdit(String tCountryEdit) {
		this.tCountryEdit = tCountryEdit;
	}

	public String geteMailEdit() {
		return eMailEdit;
	}

	public void seteMailEdit(String eMailEdit) {
		this.eMailEdit = eMailEdit;
	}

	

	public String getMobileNoEdit() {
		return mobileNoEdit;
	}

	public void setMobileNoEdit(String mobileNoEdit) {
		this.mobileNoEdit = mobileNoEdit;
	}

	public String getStatusEdit() {
		return statusEdit;
	}

	public void setStatusEdit(String statusEdit) {
		this.statusEdit = statusEdit;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	
	

	public String getEmployeeGroupId() {
		return employeeGroupId;
	}
	public void setEmployeeGroupId(String employeeGroupId) {
		this.employeeGroupId = employeeGroupId;
	}
	public String getEmployeeGroupIdEdit() {
		return employeeGroupIdEdit;
	}
	public void setEmployeeGroupIdEdit(String employeeGroupIdEdit) {
		this.employeeGroupIdEdit = employeeGroupIdEdit;
	}
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getpAdd() {
		return pAdd;
	}

	public void setpAdd(String pAdd) {
		this.pAdd = pAdd;
	}

	public String getpCity() {
		return pCity;
	}

	public void setpCity(String pCity) {
		this.pCity = pCity;
	}

	public String getpState() {
		return pState;
	}

	public void setpState(String pState) {
		this.pState = pState;
	}

	public String getpCountry() {
		return pCountry;
	}

	public void setpCountry(String pCountry) {
		this.pCountry = pCountry;
	}

	public String gettAdd() {
		return tAdd;
	}

	public void settAdd(String tAdd) {
		this.tAdd = tAdd;
	}

	public String gettCity() {
		return tCity;
	}

	public void settCity(String tCity) {
		this.tCity = tCity;
	}

	public String gettState() {
		return tState;
	}

	public void settState(String tState) {
		this.tState = tState;
	}

	public String gettCountry() {
		return tCountry;
	}

	public void settCountry(String tCountry) {
		this.tCountry = tCountry;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	


	public String[] getImieNumber() {
		return imieNumber;
	}

	public void setImieNumber(String[] imieNumber) {
		this.imieNumber = imieNumber;
	}

	public String[] getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String[] mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
