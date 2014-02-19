/**
 * 
 */
package com.mnt.mobiletrack.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import antlr.collections.List;

import com.mnt.erp.daojar.ERPDao;

import com.mnt.mobiletrack.bean.Employee;
import com.mnt.mobiletrack.bean.EmployeeIMIE;
import com.mnt.mobiletrack.bean.MapSearch;
import com.mnt.mobiletrack.service.EmployeeService;
import com.mnt.mobiletrack.service.EmployeeServiceImpl;

/**
 * @author venkateshp
 * 
 */
@Controller
public class EmployeeController {
	private static Logger logger=Logger.getLogger(EmployeeController.class);
	@Autowired
	EmployeeServiceImpl employeeService;
	@Autowired
	com.mnt.mobiletrack.service.PopulateService populateService;
	@Autowired
	ERPDao erpDao;

	@ModelAttribute("employeeGroup")
	public Map<Integer, String> populateemployeeGroup() {
		java.util.List<Object[]> listvalues = null;
		Map<Integer, String> map = null;
		Iterator<Object[]> iterator = null;
		try {
			
			
			map = populateService
					.populateSelectBox("select m.employeeGroupId,m.employeeGroup from com.mnt.mobiletrack.bean.EmployeeGroup m");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return map;
	}
	 
	@ModelAttribute("country")
	public Map<Integer, String> populatecurrency() {
		java.util.List<Object[]> listvalues = null;
		Map<Integer, String> map = null;
		Iterator<Object[]> iterator = null;
		try {
			map = populateService
					.populateSelectBox("select m.countryId,m.countryName from CountrysList m");
		}

		catch (Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String addhome() {
		return "login";
		// return new ModelAndView("login", "command", new Login());
	}

	@RequestMapping(value = "/mobiletrackingHome", method = RequestMethod.GET)
	public String addmobile() {
		return "mobiletrackingHome";
		// return new ModelAndView("login", "command", new Login());
	}

	@RequestMapping(value = "/mtHome", method = RequestMethod.GET)
	public String addmobiless() {
		return "mobiletracking";
		// return new ModelAndView("login", "command", new Login());
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "mobileHome";
	}

	@RequestMapping(value = "/mobileEmployee", method = RequestMethod.GET)
	public String Employee(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		return "employeeView";
	}
	@RequestMapping(value = "/employeeDuplicateCheck", method = RequestMethod.POST)
	public @ResponseBody String EmployeedupCheck(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String mobileNo1=request.getParameter("mobileno");
		String countryphCode=request.getParameter("countryphCode");
		String mobileNoph=countryphCode+mobileNo1;
		String mobileNo=mobileNoph.replace(" ","+");
		logger.info("mobile number=="+mobileNo);
		String sql = "select count(*) from com.mnt.mobiletrack.bean.EmployeeIMIE m where  m.mobileNo='"
				+ mobileNo + "'";
		Long duid = erpDao.duplicateCheck(sql);
System.out.println("duplicate id iss=="+duid);
		String msg="hello";
		if(duid==0)
		{
			msg="hai";
		}
		else
		{
			msg="Warning ! Mobile Number is already exists. Please try some other name";
		}
		
		return msg;
	}
	@RequestMapping(value = "/employeeDuplicateCheckEdit", method = RequestMethod.POST)
	public @ResponseBody String EmployeedupEditCheck(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String msg="hello";
		try
		{
		String mobileNo1=request.getParameter("mobilenoEdit");
		String mobilePhoneEdit=request.getParameter("countryCodeEdit");
		String mobilePhone=mobilePhoneEdit+mobileNo1;
		String mobileNo=mobilePhone.replace(" ","+");
		String imieid=request.getParameter("imieId");
	
		
		String sql ="select count(*) from com.mnt.mobiletrack.bean.EmployeeIMIE b where b.mobileNo='"
				+ mobileNo + "' and b.employeeIMIEId!='" + imieid+ "'";
		Long duid = erpDao.duplicateCheck(sql);
		
		
		if(duid==0)
		{
			msg="hai";
		}
		else
		{
			msg="Warning ! Mobile Number is already exists. Please try some other name";
		}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
		return msg;
	}
	

	
	
	

	@RequestMapping(value = "/employeeAdd", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String res = null;
		String employeeUpadte = null;
		java.util.List<String> list = null;
		java.util.List<EmployeeIMIE> employeeIMIEs = null;
		try {
			String countryphCode=employee.getCountryCode();
			
			employeeIMIEs = new ArrayList<EmployeeIMIE>();
			String[] no = employee.getMobileNo();
			String[] imei = employee.getImieNumber();
		
			for (int i = 0; i < no.length; i++) {
				EmployeeIMIE imie = new EmployeeIMIE();
				imie.setiMIENumber(imei[i]);
				
				imie.setMobileNo(countryphCode+no[i]);
				employeeIMIEs.add(imie);
			}
			System.out.println(" child size iss=="+employeeIMIEs.size());
			employee.setEmployeeImieDetails(employeeIMIEs);
			
				int flag = erpDao.saveDetails(employee);
				model.addAttribute("employee", new Employee());
				if (flag != 0) {
					employeeUpadte = "Employee Data Saved Successfully";
					list = new ArrayList<String>();
					list.add("1");
					res = "redirect:mobileEmployee.mnt?success="
							+ employeeUpadte + "&list=" + list + "";
				} else {
					employeeUpadte = "Employee Data is not Saved properly";
					list = new ArrayList<String>();
					list.add("1");
					res = "redirect:mobileEmployee.mnt?success="
							+ employeeUpadte + "&list=" + list + "";
				}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/employeeSearch", method = RequestMethod.GET)
	public String EmployeeSearch(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		java.util.List<Object[]> list = null;
		String name = null;
		String sql = null;
		Employee employeeslist = null;
		Employee modelemp = null;
		java.util.List<Employee> employeesofList = null;
		java.util.List<Employee> employees = null;
		Employee employeesearch = null;
		try {
			employeesofList = new ArrayList<Employee>();
			modelemp = new Employee();
			name = employee.getfName();

			if (name.equals("")) {
				employees = employeeService.searchEmployeeDetails();
			} else {
				employees = employeeService.searchEmployeeDetailswithName(name);
			}
			Iterator<Employee> iterator = employees.iterator();
			while (iterator.hasNext()) {
				employeeslist = (Employee) iterator.next();
				java.util.List<EmployeeIMIE> employeeIMIEs = (java.util.List<EmployeeIMIE>) employeeslist
						.getEmployeeImieDetails();
				Iterator<EmployeeIMIE> iteratorimie = employeeIMIEs.iterator();
				while (iteratorimie.hasNext()) {
					EmployeeIMIE employeeIMIE = (EmployeeIMIE) iteratorimie
							.next();
					employeesearch = new Employee();
					BeanUtils.copyProperties(employeesearch, employeeslist);
					employeesearch.setMobileNumber(employeeIMIE.getMobileNo());
					employeesearch.setImeiNumber(employeeIMIE.getiMIENumber());
					employeesofList.add(employeesearch);
				}
			logger.info("Employee Search");

			}
			model.addAttribute("employee", modelemp);
			request.setAttribute("employeeSearch", employeesofList);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return "employeeView";
	}

	@RequestMapping(value = "/employeeEditHome", method = RequestMethod.GET)
	public String EmployeeEdit(@ModelAttribute("employee") Employee employeeb,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		logger.info("Employee Edit");
		java.util.List<Employee> list = null;
		String name = null;
		String sql = null;
		Employee employeeslist = null;
		Employee modelemp = null;
		Employee employeeforChild = null;
		java.util.List<Employee> employeesofList = null;
		java.util.List<Employee> employeeforchildlist = null;
		String countryEdit=null;
		try {
			employeesofList = new ArrayList<Employee>();
			employeeforchildlist = new ArrayList<Employee>();
			modelemp = new Employee();
			name = employeeb.getfName();
			int empId = Integer.parseInt(request
					.getParameter("employeeCodeEdit"));

			// sql="select e.employeeId,e.employeeGroupId,e.fName,e.lName,e.mName,e.gender,e.dob,e.doj,e.pAdd,e.pCity,e.pState,e.pCountry,e.tAdd,e.tCity,e.tState,e.tCountry,e.eMail,e.status,e.employeeImieDetails from Employee e where e.employeeId="+empId+"";

			list = employeeService.editEmployeeValues(empId);

			Iterator<Employee> iterator = list.iterator();
			while (iterator.hasNext()) {
				employeeslist = new Employee();
				Employee employee = (Employee) iterator.next();
				employeeslist.setEmployeeId(employee.getEmployeeId());
				employeeslist.setEmployeeGroupIdEdit(employee
						.getEmployeeGroupId());
				employeeslist.setfNameEdit(employee.getfName());
				employeeslist.setlNameEdit(employee.getlName());
				employeeslist.setmNameEdit(employee.getmName());
				employeeslist.setGenderEdit(employee.getGender());
				employeeslist.setDobEdit(employee.getDob());
				employeeslist.setDojEdit(employee.getDoj());
				employeeslist.setpAddEdit(employee.getpAdd());
				employeeslist.setpCityEdit(employee.getpCity());
				employeeslist.setpStateEdit(employee.getpState());
				employeeslist.setpCountryEdit(employee.getpCountry());
				employeeslist.settAddEdit(employee.gettAdd());
				employeeslist.settCityEdit(employee.gettCity());
				employeeslist.settStateEdit(employee.gettState());
				employeeslist.settCountryEdit(employee.gettCountry());
				employeeslist.seteMailEdit(employee.geteMail());
				employeeslist.setSignature(employee.getSignature());
				// employeeslist.setPhoneNoEdit((String)objects[17]);
				/* employeeslist.setMobileNoEdit((String)objects[17]); */
				employeeslist.setStatusEdit(employee.getStatus());

				employeesofList.add(employeeslist);
				java.util.List<EmployeeIMIE> employeeIMIE = (java.util.List<EmployeeIMIE>) employee
						.getEmployeeImieDetails();
				Iterator<EmployeeIMIE> iteratorofImie = employeeIMIE.iterator();
				while (iteratorofImie.hasNext()) {
					EmployeeIMIE employeeIMIEofChild = (EmployeeIMIE) iteratorofImie
							.next();
					employeeforChild = new Employee();
					employeeforChild.setEmployeeImeiId(String
							.valueOf(employeeIMIEofChild.getEmployeeIMIEId()));
					employeeforChild.setEmployeeId(Integer
							.parseInt(employeeIMIEofChild.getEmployeeId()));
					employeeforChild.setImeiNumberEdit(employeeIMIEofChild
							.getiMIENumber());
					countryEdit=employeeIMIEofChild
							.getMobileNo().substring(3);
					employeeforChild.setMobileNoEdit(countryEdit);
					
				logger.info("CountryEdit subString=="+countryEdit);
				employeeslist.setCountryCodeEdit(employeeIMIEofChild
						.getMobileNo().substring(0,3));
					employeeforchildlist.add(employeeforChild);

				}

			}
			model.addAttribute("employee", employeeslist);
			request.setAttribute("employeeEditValues", employeesofList);
			request.setAttribute("employeechildvalues", employeeforchildlist);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return "employeeView";
	}

	@RequestMapping(value = "/employeeUpdate", method = RequestMethod.POST)
	public String EmployeeUpdate(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Employee modelemp = null;
		String mobileNumber = employee.getMobileNoEdit();
		String imienumber = employee.getImeiNumberEdit();

		String employeImeiId = employee.getEmployeeImeiId();

		String[] mobileNumbers = null;
		String[] imieNumbers = null;
		String[] employeImeiIds = null;
		EmployeeIMIE employeeIMIE = null;
		java.util.List<EmployeeIMIE> employeeIMIEsofList = null;
		java.util.List<EmployeeIMIE> childDelete = null;
		String checked = "", s1 = "0", s2 = "1";
		int ss = 0;
		int duid = 0;
		try {

			if (duid == 0) {
				
				employee.setDob(employee.getDobEdit());
				employee.setDoj(employee.getDojEdit());
				employee.seteMail(employee.geteMailEdit());
				employee.setEmployeeGroupId(employee.getEmployeeGroupIdEdit());
				employee.setfName(employee.getfNameEdit());
				employee.setGender(employee.getGenderEdit());
				employee.setlName(employee.getlNameEdit());
				employee.setmName(employee.getmNameEdit());
				employee.setpAdd(employee.getpAddEdit());
				employee.setpCity(employee.getpCityEdit());
				employee.setpCountry(employee.getpCountryEdit());
				employee.setpState(employee.getpStateEdit());
				employee.setStatus(employee.getStatusEdit());
				employee.settAdd(employee.gettAddEdit());
				employee.settCity(employee.gettCityEdit());
				employee.settCountry(employee.gettCountryEdit());
				employee.settState(employee.gettStateEdit());
				if (mobileNumber != null) {
					employeeIMIEsofList = new ArrayList<EmployeeIMIE>();
					childDelete = new ArrayList<EmployeeIMIE>();
					mobileNumbers = mobileNumber.split(",");
					imieNumbers = imienumber.split(",");

					employeImeiIds = employeImeiId.split(",");
					for (int i = 0; i < mobileNumbers.length; i++) {
						int imeinumber = Integer.parseInt(employeImeiIds[i]);

						if (imeinumber == 0) {
							employeeIMIE = new EmployeeIMIE();
							employeeIMIE.setMobileNo(employee.getCountryCodeEdit()+mobileNumbers[i]);
							employeeIMIE.setiMIENumber(imieNumbers[i]);
							employeeIMIEsofList.add(employeeIMIE);
						} else {
							employeeIMIE = new EmployeeIMIE();
							employeeIMIE.setEmployeeIMIEId(Integer
									.parseInt(employeImeiIds[i]));
							employeeIMIE.setMobileNo(employee.getCountryCodeEdit()+mobileNumbers[i]);
							employeeIMIE.setiMIENumber(imieNumbers[i]);
							employeeIMIEsofList.add(employeeIMIE);
							ss = Integer.parseInt(employeImeiIds[i]);

							checked = request.getParameter("Checkdelete" + ss);

							if (s2.equals(checked)) {
								EmployeeIMIE imie = new EmployeeIMIE();
								imie.setEmployeeIMIEId(ss);
								childDelete.add(imie);

							}
						}

					}
				}
				boolean flag = employeeService.deleteChildDetails(childDelete);
				;
				employee.setEmployeeImieDetails(employeeIMIEsofList);
				boolean msg = employeeService.updateEmployee(employee);
				model.addAttribute("employee", new Employee());
				if (msg) {

					request.setAttribute("employeeUpdate",
							"Employee Data updated successfully");
				} else {
					request.setAttribute("employeeUpdateError",
							"Employee Data updated successfully");
				}

			} else {
				request.setAttribute("employeeEditValues", "i");
				request.setAttribute("upDuplicatCheck",
						"Mobile Number is already exists. Please try some other Number");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return "employeeView";

	}

	@RequestMapping(value = "/employeeDelete", method = RequestMethod.GET)
	public String EmployeeDelete(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String updatemsg = null;
		try {
			int empId = Integer.parseInt(request
					.getParameter("employeeCodeDelete"));
			Employee employeeup = new Employee();
			employeeup.setEmployeeId(empId);
			boolean msg = employeeService.deletEmployee(empId);
			if (msg) {
				request.setAttribute("employeeUpdate",
						"Employee Data Deleted successfully");
			} else {
				request.setAttribute("employeeUpdateError",
						"Employee Data is not  Deleted properly");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "employeeView";
	}

	@RequestMapping(value = "/mapSearch", method = RequestMethod.GET)
	public String getMapData(@ModelAttribute("mapSearch") MapSearch mapSearch,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		return "mapHome";

		// return new ModelAndView("login", "command", new Login());
	}
	//get the CountryCode
	@RequestMapping(value = "/getCountryCode", method = RequestMethod.POST)
	public  @ResponseBody String getCountryCode(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String countryCode=null;
		String countryId=null;
try
{
	countryId=request.getParameter("country").toString();
	
	countryCode=employeeService.getCountryCode(countryId);
}
catch(Exception e)
{
	logger.error(e.getMessage());
}
		return countryCode;

		// return new ModelAndView("login", "command", new Login());
	}
	
	@RequestMapping(value = "/getCountryCodeEdit", method = RequestMethod.POST)
	public  @ResponseBody String getCountryCodeEdit(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String countryCode=null;
		String countryId=null;
try
{
	countryId=request.getParameter("countryEdit").toString();
	
	countryCode=employeeService.getCountryCode(countryId);
	
}
catch(Exception e)
{
	logger.error(e.getMessage());
}
		return countryCode;

		// return new ModelAndView("login", "command", new Login());
	}
	
	
	
	

	@RequestMapping(value = "/seacrhMapValues", method = RequestMethod.GET)
	public String getMapSearch(
			@ModelAttribute("mapSearch") MapSearch mapSearch,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String hql = null;
		try {

			String mobilenumer = mapSearch.getMobileNumer();
			java.util.List<MapSearch> list = null;
			java.util.List<Object[]> listofObjs = null;
			MapSearch searchofMaps = null;
			if (mobilenumer.equals("")) {
				list = employeeService.getAllMapValue();
			} else {
				list = employeeService.getMapData(mapSearch.getMobileNumer());
			}
			request.setAttribute("mapvalues", list);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return "mapHome";

		// return new ModelAndView("login", "command", new Login());
	}

	/*
	 * @ModelAttribute("mapsearch") public Map<String, String> populatemap() {
	 * java.util.List<Object[]> listvalues =null; Map<String, String> map =null;
	 * Iterator<Object[]> iterator=null; try {
	 * map=populateService.populatePopUp(
	 * "select m.mobileNo,m.mobileNo from Employee m"); }
	 * 
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return map; }
	 */

	//}
	


}
