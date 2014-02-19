/**
 * 
 */
package com.mnt.mobiletrack.dao;

import java.util.List;

import com.mnt.mobiletrack.bean.Employee;
import com.mnt.mobiletrack.bean.EmployeeIMIE;
import com.mnt.mobiletrack.bean.MapSearch;

/**
 * @author venkateshp
 *
 */
public interface EmployeeDao {
	public boolean saveEmployeeDetails(Object object);
	public List<MapSearch> getMapData(String mobileNumber);
	public List<MapSearch> getAllMapValue();
	public String getMaxDate();
	public List<Employee> searchEmployeeDetails();
	List<Employee> searchEmployeeDetailswithName(String ename);
	public List<Employee> editEmployeeValues(int empId);
	public boolean deleteChildDetails(List<EmployeeIMIE> employeeIMIEs);
	public boolean deletEmployee(int empId);
	public boolean updateEmployee(Employee employee);
	public String getCountryCode(String countryId);

}
