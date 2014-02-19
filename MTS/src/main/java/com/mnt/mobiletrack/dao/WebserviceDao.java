/**
 * 
 */
package com.mnt.mobiletrack.dao;

import java.util.List;

import com.mnt.mobiletrack.bean.Calls;
import com.mnt.mobiletrack.bean.Messages;
import com.mnt.mobiletrack.bean.MobileGEOCode;

/**
 * @author venkateshp
 *
 */
public interface WebserviceDao {
	public boolean saveCallsDetails(List<Calls> list);
	public boolean saveMessagesDetails(List<Messages> list);
	public boolean saveMobileGeoCodeDetails(List<MobileGEOCode> list);

}
