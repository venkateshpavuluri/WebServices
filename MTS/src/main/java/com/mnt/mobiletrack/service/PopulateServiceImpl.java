/**
 * Copyright MNTSOFT 
 */
package com.mnt.mobiletrack.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



/**
 * @author pvenkateswarlu
 * @version 1.0 09-10-2013
 */
public class PopulateServiceImpl implements PopulateService {
	private Logger logger=Logger.getLogger(PopulateServiceImpl.class);

	public com.mnt.mobiletrack.dao.PoPulateDao getDao() {
		return dao;
	}

	public void setDao(com.mnt.mobiletrack.dao.PoPulateDao dao) {
		this.dao = dao;
	}

	com.mnt.mobiletrack.dao.PoPulateDao dao;

	
	public List<Object[]> poPulate(String sql) {
		List<Object[]> list = null;
		try {
			list = dao.poPulate(sql);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public Map<String, String> populatePopUp(String sql) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		List<Object[]> list = null;

		try {
			list = dao.poPulate(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((String) objects[0], (String) objects[1]);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return map;

	}

	public Map<Integer, String> populateSelectBox(String sql) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<Object[]> list = null;

		try {
			list = dao.poPulate(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objects = (Object[]) iterator.next();
				map.put((Integer) objects[0], (String) objects[1]);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return map;

	}

}
