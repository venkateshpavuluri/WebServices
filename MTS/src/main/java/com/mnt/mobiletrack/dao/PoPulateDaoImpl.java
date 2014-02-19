/**
 * Copyright MNTSOFT 
 */
package com.mnt.mobiletrack.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author pvenkateswarlu
 *@version 1.0 09-10-2013
 */
public class PoPulateDaoImpl extends HibernateDaoSupport implements PoPulateDao {
private 	Logger logger=Logger.getLogger(PoPulateDaoImpl.class);

	public List<Object[]> poPulate(String sql) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		try
		{
			list=getHibernateTemplate().find(sql);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}

}
