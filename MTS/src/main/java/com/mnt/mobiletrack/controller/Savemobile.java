/**
 * 
 */
package com.mnt.mobiletrack.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;

import com.mnt.mobiletrack.bean.MobileGEOCode;
import com.mnt.mobiletrack.dao.WebserviceDao;
import com.mnt.mobiletracking.model.Call;
import com.mnt.mobiletracking.model.Calls;
import com.mnt.mobiletracking.model.Location;
import com.mnt.mobiletracking.model.Locations;
import com.mnt.mobiletracking.model.Message;
import com.mnt.mobiletracking.model.Messages;
import com.mnt.mobiletracking.model.Tracking;

/**
 * @author venkateshp
 *
 */
public class Savemobile extends TimerTask {
	@Autowired
	ServletContext context;
	@Autowired
	WebserviceDao webServiceDao;
	String succmessages;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		File file=new File(context.getRealPath("/DownloadedFiles"));
		String[] files=file.list();
		List<com.mnt.mobiletrack.bean.Calls> listofCalls=null;
		List<com.mnt.mobiletrack.bean.Messages> listofMsgs=null;
		List<com.mnt.mobiletracking.model.Message> listofMessages=null;
		List<Call> listOfCallWs=null;
		List<Location> listofLocation=null;
		List<MobileGEOCode> mobileGEOCodes=null;
		String imieNo=null;
		Tracking items=null;
		try
		{
		JAXBContext jc = JAXBContext.newInstance(Tracking .class);
		Unmarshaller  unmarshallerq = jc.createUnmarshaller();
		for(String jh:files)
		{
			//System.out.println("file name is=="+jh);
			String path=context.getRealPath("/DownloadedFiles/"+jh+"");
			File xml = new File(path);
			 items = (Tracking) unmarshallerq.unmarshal(xml);
		 imieNo=items.getMobileDeviceId();
		Locations locations=items.getLocationslist();
		if(locations!=null)
		{
		 listofLocation=locations.getLocations();
		}
		if(listofLocation!=null)
		{
			mobileGEOCodes=new ArrayList<MobileGEOCode>();
		Iterator<Location> itrLocation=listofLocation.iterator();
		while(itrLocation.hasNext())
		{
			Location location=(Location)itrLocation.next();	
			MobileGEOCode mobileGEOCode=new MobileGEOCode();
			mobileGEOCode.setdT(location.getDatetime());
			mobileGEOCode.setiMIENumber(imieNo);
			mobileGEOCode.setLatitude(location.getLatitude());
			mobileGEOCode.setLongitude(location.getLongitude());
			mobileGEOCodes.add(mobileGEOCode);
			boolean mobileFlag=webServiceDao.saveMobileGeoCodeDetails(mobileGEOCodes);
			if(mobileFlag)
			{
				succmessages="S";
			}
			else
			{
				succmessages="F";
			}
		}
			
		}
 com.mnt.mobiletracking.model.Messages messages=( com.mnt.mobiletracking.model.Messages)items.getMessageslist();
		if(messages!=null)
		{
		 listofMessages=messages.getMessages();
		}
		if(listofMessages!=null)
		{
			listofMsgs=new ArrayList <com.mnt.mobiletrack.bean.Messages>();
		Iterator<Message> iteMessage=listofMessages.iterator();
		while(iteMessage.hasNext())
		{
			Message message=(Message)iteMessage.next();
			com.mnt.mobiletrack.bean.Messages msgsOfBean=new com.mnt.mobiletrack.bean.Messages();
			msgsOfBean.setDt(message.getDatetime());
			msgsOfBean.setImienumber(imieNo);
			msgsOfBean.setMessagenumber(message.getNumber());
			msgsOfBean.setMessagetype(message.getType());
			listofMsgs.add(msgsOfBean);
			boolean msgflag=webServiceDao.saveMessagesDetails(listofMsgs);
			if(msgflag)
			{
				succmessages="S";
			}
			else
			{
				succmessages="F";
			}
		}
		}
	
		Calls calls=(Calls)items.getCallslist();
		if(calls!=null)
		{

	listOfCallWs=calls.getCalls();
		}
		if(listOfCallWs!=null)
		{
			listofCalls=new ArrayList<com.mnt.mobiletrack.bean.Calls>();
			Iterator<Call> iterCall=listOfCallWs.iterator();
			while(iterCall.hasNext())
			{
				Call meCall=(Call)iterCall.next();
				com.mnt.mobiletrack.bean.Calls callsbean=new com.mnt.mobiletrack.bean.Calls();
				callsbean.setCallNumber(meCall.getNumber());
				callsbean.setCallType(meCall.getType());
				callsbean.setDt(meCall.getDatetime());
				callsbean.setImieNumbe(imieNo);
				callsbean.setDuration(meCall.getDuration());
				listofCalls.add(callsbean);
				boolean callsFlag=webServiceDao.saveCallsDetails(listofCalls);
				if(callsFlag)
				{
					succmessages="S";
				}
				else
				{
					succmessages="F";
				}
		}
		}
		if(succmessages=="S")
		{
		File filedest=new File(context.getRealPath("/UpdatedFiles"),xml.getName());
		boolean suc=xml.renameTo(filedest);
		}

		//System.out.println("file moving=="+suc+"==dest=="+filedest);
		
	}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
