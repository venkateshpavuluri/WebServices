/**
 * 
 */
package com.mnt.mobiletrack.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnt.erp.daojar.ERPDao;
import com.mnt.mobiletrack.bean.Calls;
import com.mnt.mobiletrack.bean.Employee;
import com.mnt.mobiletrack.bean.Messages;
import com.mnt.mobiletrack.service.PopulateService;

/**
 * @author venkateshp
 *
 */
@Controller
public class CallsHistoryController {
	private Logger logger=Logger.getLogger(CallsHistoryController.class);
	@Autowired
	PopulateService populateService;
	@Autowired
	ERPDao erpDao;
	@RequestMapping (value = "/callsSearchHome", method = RequestMethod.GET)
	public String addCallsHistory(@ModelAttribute("callsForm") Calls calls) {
	
		return "callsHistoryView";
		// return new ModelAndView("login", "command", new Login());
	}
	
	@ModelAttribute("callNumbers")
	public Map<String, String> populatemap() {
		java.util.List<Object[]> listvalues =null;
		Map<String, String> map =null;
		Iterator<Object[]> iterator=null;
		try {
map=populateService.populatePopUp("select c.callNumber,c.callNumber from com.mnt.mobiletrack.bean.Calls c");
		
		}

		 catch (Exception e) {
			 logger.error(e.getMessage());
		}

		return map;
	}
	
	
	
	@RequestMapping(value = "/searchCalls", method = RequestMethod.GET)
	public String callSearch(@ModelAttribute("callsForm")Calls calls ,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Object[]> listofObjects=null;
		String hql=null;
		Iterator<Object[]> iterator=null;
	Calls callsSearch=null;
	List<Calls> list=null;
	HttpSession httpSession=null;
	try
		{
		httpSession=request.getSession();
		
			list=new ArrayList<Calls>();
	
			String mobileNumberi=request.getParameter("mobileNumber");
			
			String mobileNumber=mobileNumberi.replace(" ", "+");
			httpSession.setAttribute("callsNumber",mobileNumber);
			String date=request.getParameter("date");

				hql="select c.callId,c.imieNumbe,c.callNumber,c.duration,case c.callType when '1' then 'RECIEVED' when '2' then 'DIAL' when '3' then 'MISSED' end as callType,c.dt from com.mnt.mobiletrack.bean.Calls c where c.callNumber='"+mobileNumber+"'";	
			
		/*	else
			{
				hql="select c.callId,c.imieNumbe,c.callNumber,c.duration,c.callType,c.dt from com.mnt.mobiletrack.bean.Calls c where c.callNumber='"+callNumber+"'";
			}*/
			
			listofObjects=erpDao.searchDetails(hql);
			
			if(listofObjects!=null)
			{
			 iterator=listofObjects.iterator();
			 while(iterator.hasNext())
			 {
				 Object[] objects=(Object[])iterator.next();
				 callsSearch=new Calls();
				 callsSearch.setCallId((Integer)objects[0]);
				 callsSearch.setImieNumbe((String)objects[1]);
				 callsSearch.setCallNumber((String)objects[2]);
				 callsSearch.setDuration((String)objects[3]);
				 callsSearch.setCallType((String)objects[4]);
				 callsSearch.setDt((String)objects[5]);
				 list.add(callsSearch);
			 }
			}
			 request.setAttribute("callsValues", list);
		}
		catch(Exception e)
		{
			 logger.error(e.getMessage());
		}
		return "callsHistory";
	}
	
	@RequestMapping(value = "/callsEditHome", method = RequestMethod.GET)
	public String callsEdit(@ModelAttribute("callsForm")Calls calls ,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Object[]> listofObjects=null;
		String hql=null;
		Iterator<Object[]> iterator=null;
	Calls callsSearch=null;
	List<Calls> list=null;
		try
		{
			String date=request.getParameter("date");
			list=new ArrayList<Calls>();
			hql="select c.callId,c.imieNumbe,c.callNumber,c.duration,case c.callType when '1' then 'RECIEVED' when '2' then 'DIAL' when '3' then 'MISSED' end as callType,c.dt from com.mnt.mobiletrack.bean.Calls c where c.dt='"+date+"'";
			listofObjects=erpDao.searchDetails(hql);
			if(listofObjects!=null)
			{
			 iterator=listofObjects.iterator();
			 while(iterator.hasNext())
			 {
				 Object[] objects=(Object[])iterator.next();
				 callsSearch=new Calls();
				 callsSearch.setCallId((Integer)objects[0]);
				 callsSearch.setImieNumbe((String)objects[1]);
				 callsSearch.setCallNumber((String)objects[2]);
				 callsSearch.setDuration((String)objects[3]);
				 callsSearch.setCallType((String)objects[4]);
				 callsSearch.setDt((String)objects[5]);
				 list.add(callsSearch);
			 }
			}
			 request.setAttribute("callsPopup", list);

		}
		catch(Exception e)
		{
			 logger.error(e.getMessage());
		}
		
		return "callsHistory";
	}
	
	@RequestMapping(value = "/callsSearchwithDate", method = RequestMethod.GET)
	public   String getCallsData(@ModelAttribute("callsForm") Calls calls ,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Object[]> list=null;
		Iterator<Object[]> iterator=null;
		Calls callsSearch=null;
		List<Calls> listofCalls=null;
		HttpSession httpSession=null;
try
{
	httpSession=request.getSession(false);
	listofCalls=new ArrayList<Calls>();
	String mobileNumber=httpSession.getAttribute("callsNumber").toString();
	//String date=request.getParameter("date");
	String date=calls.getDt();
	String sql=null;
	if(date.equals(""))
	{
		 sql="select c.callId,c.imieNumbe,c.callNumber,c.duration,case c.callType when '1' then 'RECIEVED' when '2' then 'DIAL' when '3' then 'MISSED' end as callType,c.dt from com.mnt.mobiletrack.bean.Calls c where c.callNumber='"+mobileNumber+"'";
		
	}
	else
	{
	String[] datessub=date.split("-");
 sql="select c.callId,c.imieNumbe,c.callNumber,c.duration,case c.callType when '1' then 'RECIEVED' when '2' then 'DIAL' when '3' then 'MISSED' end as callType,c.dt from com.mnt.mobiletrack.bean.Calls c where DATEPART(year,c.dt)="+datessub[0]+" and  DATEPART(month,c.dt)="+datessub[1]+" and DATEPART(day,c.dt)="+datessub[2]+" and c.callNumber='"+mobileNumber+"'";
	}
	list=erpDao.searchDetails(sql);
	if(list!=null)
	{
iterator=list.iterator();
while(iterator.hasNext())
{
	Object[] objects=(Object[])iterator.next();
	callsSearch=new Calls();
	callsSearch.setCallId((Integer)objects[0]);
	 callsSearch.setImieNumbe((String)objects[1]);
	 callsSearch.setCallNumber((String)objects[2]);
	 callsSearch.setDuration((String)objects[3]);
	 callsSearch.setCallType((String)objects[4]);
	 callsSearch.setDt((String)objects[5]);
	 listofCalls.add(callsSearch);
}
	}
request.setAttribute("callsPopup", listofCalls);
}
catch(Exception e)
{
	logger.error(e.getMessage());
}
		return "CallsPopup";	
		// return new ModelAndView("login", "command", new Login());
	}
}
