/**
 * 
 */
package com.mnt.mobiletrack.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mnt.mobiletrack.bean.Calls;
import com.mnt.mobiletrack.bean.Messages;
import com.mnt.mobiletrack.bean.MobileGEOCode;

/**
 * @author venkateshp
 *
 */
public class WebServiceDaoImpl extends HibernateDaoSupport implements WebserviceDao {
	private Logger logger=Logger.getLogger(WebServiceDaoImpl.class);
private boolean flag;
	@Override
	public boolean saveCallsDetails(List<Calls> list) {
		// TODO Auto-generated method stub
		try
		{
		
			System.out.println("list iss=="+list);
			getHibernateTemplate().saveOrUpdateAll(list);
			
			flag=true;
		}
		catch(Exception e)
		{
			flag=false;
			logger.error(e.getMessage());
		}
		return flag;
	}
	@Override
	public boolean saveMessagesDetails(List<Messages> list) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().saveOrUpdateAll(list);
			
			flag=true;
		}
		catch(Exception e)
		{
			flag=false;
			logger.error(e.getMessage());
		}
		return flag;
	}
	@Override
	public boolean saveMobileGeoCodeDetails(List<MobileGEOCode> list) {
		// TODO Auto-generated method stub
		try
		{
			getHibernateTemplate().saveOrUpdateAll(list);
			
			flag=true;
		}
		catch(Exception e)
		{
			flag=false;
			logger.error(e.getMessage());
		}
		return flag;
	} 

}
