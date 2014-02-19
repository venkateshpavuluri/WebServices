package com.mnt.mobiletrack.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnt.erp.daojar.ERPDao;
import com.mnt.mobiletrack.bean.MapSearch;
import com.mnt.mobiletrack.bean.Messages;
import com.mnt.mobiletrack.service.PopulateService;

/**
 * @author srinivas
 *
 */
@Controller
public class MessageController {
	private Logger logger=Logger.getLogger(MessageController.class);
	@ Autowired
	ERPDao erpdao;
	@ Autowired
	PopulateService populateservice;
	@RequestMapping(value = "/messageSearch", method = RequestMethod.GET)
	public String getMapData(@ModelAttribute("messageSearch") Messages msgs ,HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "messageHome";
		
		// return new ModelAndView("login", "command", new Login());
	}
	@RequestMapping(value = "/seacrhMessageValues", method = RequestMethod.GET)
	public String getMapSearch(@ModelAttribute("messageSearch")Messages msg ,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Object[]> list=null;
		Iterator<Object[]> iterator=null;
		List<Messages> mbeans=new ArrayList<Messages>();
		String number=msg.getMessagenumber();
		HttpSession httpSession=null;
		
	
try
{
	httpSession=request.getSession();
	
	String mobileNumber1=request.getParameter("mobileNumber");
	String mobileNumber=mobileNumber1.replace(" ", "+");

	httpSession.setAttribute("msgNumber", mobileNumber);
	String date=request.getParameter("date");
	list=erpdao.searchDetails("select m.messageid,m.messagenumber,m.imienumber,case m.messagetype when '2' then 'SENT' when '1' then 'RECIEVED' end as messagetype,m.dt from Messages m where m.messagenumber='"+mobileNumber+"'");
	if(list!=null)
	{
	iterator=list.iterator();
    while(iterator.hasNext()){
    	Object[] obj=(Object[])iterator.next();
    	Messages msgs=new Messages();
    	msgs.setMessageid((Integer)obj[0]);
    	msgs.setMessagenumber((String)obj[1]);
    	msgs.setImienumber((String)obj[2]);
    	msgs.setMessagetype((String)obj[3]);
    	msgs.setDt((String)obj[4]);
    	mbeans.add(msgs);
	}
	}
	request.setAttribute("msgvalues", mbeans);
}
catch(Exception e)
{
	logger.error(e.getMessage());
}

		return "messages";
		
		// return new ModelAndView("login", "command", new Login());
	}
	
	
	@ModelAttribute("msgnosearch")
	public Map<String, String> populatemap() {
		java.util.List<Object[]> listvalues =null;
		Map<String, String> map =null;
		Iterator<Object[]> iterator=null;
		try {
map=populateservice.populatePopUp("select m.messagenumber,m.messagenumber from Messages m");
			}

		 catch (Exception e) {
			 logger.error(e.getMessage());
		}

		return map;
	}	
	@RequestMapping(value = "/SearchmessageswithDate", method = RequestMethod.GET)
	public String getDateSearch(@ModelAttribute("messageSearch")Messages msg ,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Object[]> list=null;
		Iterator<Object[]> iterator=null;
		List<Messages> mbeans=new ArrayList<Messages>();
		String date= msg.getDt();
	
		
		String[] datessub=null;
		HttpSession httpSession=null;
		
		String sql=null;
try
{
	httpSession=request.getSession(false);
	String mobileNumber=httpSession.getAttribute("msgNumber").toString();

	if(date.equals(""))
	{
		sql="select m.messageid,m.imienumber,case m.messagetype when '2' then 'SENT' when '1' then 'RECIEVED' end as messagetype,m.messagenumber,m.dt from Messages m where m.messagenumber='"+mobileNumber+"'";	
 
	}
	else
	{
		datessub=date.split("-");
		sql="select m.messageid,m.imienumber,case m.messagetype when '2' then 'SENT' when '1' then 'RECIEVED' end as messagetype,m.messagenumber,m.dt from Messages m where DATEPART(year,m.dt)="+datessub[0]+" and  DATEPART(month,m.dt)="+datessub[1]+" and DATEPART(day,m.dt)="+datessub[2]+" and m.messagenumber='"+mobileNumber+"'";
	}
		list=erpdao.searchDetails(sql);
		if(list!=null)
		{
	    iterator=list.iterator();
	    while(iterator.hasNext()){
	    	Object[] obj=(Object[])iterator.next();
	    	Messages msgs=new Messages();
	    	msgs.setMessageid((Integer)obj[0]);
	    	msgs.setImienumber((String)obj[1]);
	    	msgs.setMessagetype((String)obj[2]);  //where DATEPART(year,c.dt)="+datessub[0]+" and  DATEPART(month,c.dt)="+datessub[1]+" and DATEPART(day,c.dt)="+datessub[2]+"";
	    	msgs.setMessagenumber((String)obj[3]);
	    	msgs.setDt((String)obj[4]);
	    	mbeans.add(msgs);
	}
		}


	request.setAttribute("msgdatevalues", mbeans);
}
catch(Exception e)
{
	logger.error(e.getMessage());
}
		return "messagepopup";
		
		// return new ModelAndView("login", "command", new Login());
	}

}
