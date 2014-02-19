package com.mnt.mobiletrack.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;



import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.mnt.mobiletrack.bean.Messages;
import com.mnt.mobiletrack.bean.MobileGEOCode;
import com.mnt.mobiletrack.dao.WebserviceDao;
import com.mnt.mobiletracking.model.Call;
import com.mnt.mobiletracking.model.Calls;
import com.mnt.mobiletracking.model.Location;
import com.mnt.mobiletracking.model.Locations;
import com.mnt.mobiletracking.model.Message;
import com.mnt.mobiletracking.model.Tracking;
import com.mnt.mobiletracking.util.DbConnection;

@Component
@Controller

public class SaveMobileDetails  extends TimerTask {
	private Logger logger=Logger.getLogger(SaveMobileDetails.class);
	@Autowired
	ServletContext context;
	@Autowired
	WebserviceDao webServiceDao;
	String succmessages;
	public void run() {
		File file=new File(context.getRealPath("/DownloadedFiles"));
		String[] files=file.list();
		List<Call> listOfCall=null;
		Connection connection=null;
		PreparedStatement pst=null;
		List<Message> listofMessages=null;
		List<Location>	 listofLocation=null;
		try
		{
		JAXBContext jc = JAXBContext.newInstance(Tracking.class);
		Unmarshaller  unmarshallerq = jc.createUnmarshaller();
		for(String jh:files)
		{
			//System.out.println("file name is=="+jh);
			String path=context.getRealPath("/DownloadedFiles/"+jh+"");
			File xml = new File(path);
			Tracking items = (Tracking) unmarshallerq.unmarshal(xml);
			String imieNo=items.getMobileDeviceId();
			Locations locations=items.getLocationslist();
			if(locations!=null)
			{
				succmessages="F";
				String sql="insert into MobileGEOCode values(?,?,?,?)";
				connection=DbConnection.getConnection();
				pst=connection.prepareStatement(sql);
				connection.setAutoCommit(false);
				if(locations!=null)
				{
		listofLocation=locations.getLocations();
				}
			Iterator<Location> itrLocation=listofLocation.iterator();
			while(itrLocation.hasNext())
			{
				Location location=(Location)itrLocation.next();
				pst.setString(1,imieNo);
				pst.setString(2,location.getLatitude());
				pst.setString(3,location.getLongitude());
				pst.setString(4,location.getDatetime());
				//System.out.println("location date=="+location.getDatetime());
			int id=pst.executeUpdate();
			if(id!=0)
			{
				succmessages="S";
				logger.info("MobileGEOCode Details Saved Successfully");
				
			}
			}
			}
			String sqlmes="insert into Message values(?,?,?,?)";
			pst=connection.prepareStatement(sqlmes);
			com.mnt.mobiletracking.model.Messages messages=(com.mnt.mobiletracking.model.Messages)items.getMessageslist();
		if(messages!=null)
		{
			 listofMessages=messages.getMessages();
		}
			if(listofMessages!=null)
			{
			Iterator<Message> iteMessage=listofMessages.iterator();
			while(iteMessage.hasNext())
			{
				Message message=(Message)iteMessage.next();
				pst.setString(1,imieNo);
				pst.setString(2,message.getNumber());
				pst.setString(3,message.getType());
				pst.setString(4,message.getDatetime());
			int id=pst.executeUpdate();	
			if(id!=0)
			{
				succmessages="S";
				logger.info("Message Details Saved Successfully");
			}
							}
			}
			String sqlcal="insert into Calls(IMIE_Number,CallNumber,Duration,CallType,DT) values(?,?,?,?,?)";
			pst=connection.prepareStatement(sqlcal);
			Calls calls=(Calls)items.getCallslist();
			if(calls!=null)
			{
			 listOfCall=calls.getCalls();
			}
			if(listOfCall!=null)
			{
			Iterator<Call> iterCall=listOfCall.iterator();
			while(iterCall.hasNext())
			{
				Call meCall=(Call)iterCall.next();
				pst.setString(1,imieNo);
				pst.setString(2,meCall.getNumber());
				pst.setString(3,meCall.getDuration());
				pst.setString(4,meCall.getType());
				pst.setString(5,meCall.getDatetime());
				//System.out.println("date iss=="+meCall.getDatetime());
				int u=pst.executeUpdate();
				if(u!=0)
				{
					succmessages="S";
					logger.info("Calls Details Saved Successfully");
				}
			}
			}
			connection.setAutoCommit(true);
			logger.info("successmessage iss=="+succmessages);
			if(succmessages.equals("S"))
			{
			File filedest=new File(context.getRealPath("/UpdatedFiles"),xml.getName());
			boolean suc=xml.renameTo(filedest);
			System.out.println("file msg=="+suc);
			logger.info("filesuc mess=="+suc);
			if(suc)
			{
				logger.info("File Moved Successfully..Location iss"+filedest);
			}
			else {
				logger.info("File was unable to Move");
			}
				
			}
			
		}
		}

		 catch (Exception e) {
			try
			{
			 connection.rollback();
			}
			catch(SQLException sql)
			{
				logger.error(sql.getMessage());
			}
			succmessages="F";
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(connection!=null)
				{
					connection.close();
				}
				if(pst!=null)
				{
					pst.close();
				}
			}
			
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		}
	}
}
		
		
	
	

    


