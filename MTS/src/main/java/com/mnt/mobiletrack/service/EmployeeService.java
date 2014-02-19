/**
 * 
 */
package com.mnt.mobiletrack.service;

import java.util.List;

import com.mnt.mobiletrack.bean.Employee;
import com.mnt.mobiletrack.bean.EmployeeIMIE;
import com.mnt.mobiletrack.bean.MapSearch;

/**
 * @author venkateshp
 *
 */
public interface EmployeeService {
	public boolean saveEmployeeDetails(Object object);
	public List<MapSearch> getMapData(String mobileNumber);
	public List<MapSearch> getAllMapValue();
	public List<Employee> searchEmployeeDetails();
	public List<Employee> searchEmployeeDetailswithName(String ename);
	public List<Employee> editEmployeeValues(int empId);
	public boolean deleteChildDetails(List<EmployeeIMIE> employeeIMIEs);
	public boolean deletEmployee(int empId);
	public boolean updateEmployee(Employee employee);
	public String getCountryCode(String countryId);

}
