/**
 * 
 */
package com.mnt.mobiletrack.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mnt.mobiletrack.bean.Employee;
import com.mnt.mobiletrack.bean.EmployeeIMIE;
import com.mnt.mobiletrack.bean.MapSearch;

/**
 * @author venkateshp
 *
 */
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao{
	private Logger logger=Logger.getLogger(EmployeeDaoImpl.class);

	String sql;
	boolean flag=true;
	
	public boolean saveEmployeeDetails(Object object) {
		// TODO Auto-generated method stub
		
		try
		{
			
			Serializable id=getHibernateTemplate().save(object);
			if(id!=null)
			{
				flag=true;
			}
		}
		catch(Exception e)
		{
			flag=false;
			logger.error(e.getMessage());
		}
		
		return flag;
	}

	public List<MapSearch> getMapData(String mobileNumber) {
		// TODO Auto-generated method stub
		List<Object[]> listofob=null;
		List<MapSearch> listofMap=null;
		MapSearch mapSearch=null;
		try
		{
			listofMap=new ArrayList<MapSearch>();
			listofob=getHibernateTemplate().findByNamedQueryAndNamedParam("getdataf",new String[]{"mobile"},new Object[]{mobileNumber});
		//System.out.println("list of proc=="+listofob);
		Iterator<Object[]> iterator=listofob.iterator();
		while(iterator.hasNext())
		{
			Object[] objects=(Object[])iterator.next();
			mapSearch=new MapSearch();
			mapSearch.setEmployeeId((Integer)objects[0]);
			mapSearch.setEmpName((String)objects[1]);
			mapSearch.setImieNo((String)objects[2]);
			mapSearch.setDt((String)objects[3]);
			mapSearch.setMobileNumer((String)objects[4]);
			listofMap.add(mapSearch);
		}
		
		
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
		List<Object[]> listofob=null;
		List<MapSearch> listofMap=null;
		MapSearch mapSearch=null;
		try
		{
			
			
				listofMap=new ArrayList<MapSearch>();
				listofob=getHibernateTemplate().findByNamedQuery("getAllMaps");
			//System.out.println("list of proc=="+listofob);
			Iterator<Object[]> iterator=listofob.iterator();
			while(iterator.hasNext())
			{
				Object[] objects=(Object[])iterator.next();
				mapSearch=new MapSearch();
				mapSearch.setEmployeeId((Integer)objects[0]);
				mapSearch.setEmpName((String)objects[1]);
				mapSearch.setImieNo((String)objects[2]);
				mapSearch.setDt((String)objects[3]);
				mapSearch.setMobileNumer((String)objects[4]);
				listofMap.add(mapSearch);
			}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
			
		return listofMap;
	}

	@Override
	public String getMaxDate() {
		// TODO Auto-generated method stub
		String date=null;
		try
		
		{
			
		String hql="select max() ";
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
		return "";
	}

	@Override
	public List<Employee> searchEmployeeDetails() {
		// TODO Auto-generated method stub
		List<Employee> list=null;
		try
		{
			 sql="from  com.mnt.mobiletrack.bean.Employee";
			list=getHibernateTemplate().find(sql);
			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Employee> searchEmployeeDetailswithName(String ename) {
		// TODO Auto-generated method stub
		List<Employee> list=null;
		try
		{
			 sql="from  com.mnt.mobiletrack.bean.Employee e where e.fName='"+ename+"'";
			 list=getHibernateTemplate().find(sql);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public List<Employee> editEmployeeValues(int empId) {
		// TODO Auto-generated method stub
		List<Employee> list=null;
		System.out.println("emp id=="+empId);
		try
		{
			sql="from com.mnt.mobiletrack.bean.Employee e where e.employeeId="+empId+"";
			list=getHibernateTemplate().find(sql);
					}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return list;
	}

	@Override
	public boolean deleteChildDetails(List<EmployeeIMIE> employeeIMIEs) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try
		{
			getHibernateTemplate().deleteAll(employeeIMIEs);
			flag=true;
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			flag=false;
			
		}
		return flag;
	}

	@Override
	public boolean deletEmployee(int empId) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try
		{
			Employee employee=new Employee();
			employee.setEmployeeId(empId);
			Employee employee2=(Employee)getHibernateTemplate().get(Employee.class,empId);
			List<EmployeeIMIE> employeeIMIEs=employee2.getEmployeeImieDetails();
			getHibernateTemplate().deleteAll(employeeIMIEs);
			getHibernateTemplate().delete(employee);
			flag=true;
			
			
		}
		catch(Exception e)
		{
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		flag=true;
		try
		{
			getHibernateTemplate().update(employee);
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
		String ccode=null;
		try
		{
			sql="select c.countryPhoneCode from com.mnt.mobiletrack.bean.CountrysList c where c.countryId="+countryId+"";
			List<String> countryCode=getHibernateTemplate().find(sql);
			 ccode=countryCode.get(0);
			 logger.info("countryCode=="+ccode);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return ccode;
	}

}
