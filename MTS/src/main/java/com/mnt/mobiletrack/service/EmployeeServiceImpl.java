/**
 * 
 */
package com.mnt.mobiletrack.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.mnt.mobiletrack.bean.Employee;
import com.mnt.mobiletrack.bean.EmployeeIMIE;
import com.mnt.mobiletrack.bean.MapSearch;
import com.mnt.mobiletrack.dao.EmployeeDao;

/**
 * @author venkateshp
 *
 */
public class EmployeeServiceImpl implements EmployeeService {
	private Logger logger=Logger.getLogger(EmployeeServiceImpl.class);
	private boolean flag;
private EmployeeDao dao;


	public EmployeeDao getDao() {
	return dao;
}


public void setDao(EmployeeDao dao) {
	this.dao = dao;
}



	public boolean saveEmployeeDetails(Object object) {
		// TODO Auto-generated method stub
		try
		{
			flag=dao.saveEmployeeDetails(object);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
		return flag;
	}
	public List<MapSearch> getMapData(String mobileNumber) {
		// TODO Auto-generated method stub
		List<MapSearch> listofMap=null;
		try
		{
			listofMap=dao.getMapData(mobileNumber);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return listofMap;
	}
	@Override
	public List<MapSearch> getAllMapValue() {
		// TODO Auto-generated method stub
		List<MapSearch> list=null;
		try
		{
		 list=dao.getAllMapValue();
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}
	@Override
	public List<Employee> searchEmployeeDetails() {
		// TODO Auto-generated method stub
		List<Employee> employees=null;
		try
		{
			employees=dao.searchEmployeeDetails();
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return employees;
	}
	@Override
	public List<Employee> searchEmployeeDetailswithName(String ename) {
		// TODO Auto-generated method stub
		List<Employee> employees=null;
		try
		{
			employees=dao.searchEmployeeDetailswithName(ename);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return employees;
	}


	@Override
	public List<Employee> editEmployeeValues(int empId) {
		// TODO Auto-generated method stub
		List<Employee> employees=null;
		try
		{
			employees=dao.editEmployeeValues(empId);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return employees;
	}


	@Override
	public boolean deleteChildDetails(List<EmployeeIMIE> employeeIMIEs) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try
		{
			flag=dao.deleteChildDetails(employeeIMIEs);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return flag;
	}


	@Override
	public boolean deletEmployee(int empId) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try
		{
			flag=dao.deletEmployee(empId);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return flag;
	}


	@Override
	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		boolean  flag=true;
		try
		{
		flag=dao.updateEmployee(employee);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return flag;
	}


	@Override
	public String getCountryCode(String countryId) {
		// TODO Auto-generated method stub
		String countryCode=null;
		try
		{
			countryCode=dao.getCountryCode(countryId);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return countryCode;
	}

	
}
