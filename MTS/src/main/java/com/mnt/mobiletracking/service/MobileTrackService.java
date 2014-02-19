package com.mnt.mobiletracking.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.mnt.mobiletrack.controller.EmployeeController;
import com.mnt.mobiletrack.service.PopulateService;
import com.mnt.mobiletracking.model.Call;
import com.mnt.mobiletracking.model.Calls;
import com.mnt.mobiletracking.model.Location;
import com.mnt.mobiletracking.model.Locations;
import com.mnt.mobiletracking.model.Message;
import com.mnt.mobiletracking.model.Messages;
import com.mnt.mobiletracking.model.Tracking;
import com.mnt.mobiletracking.util.DbConnection;
@Component
@Path("/mobile")
public class MobileTrackService  {
	private static Logger logger=Logger.getLogger(MobileTrackService.class);
	
	public MobileTrackService() {
		
		// TODO Auto-generated constructor stub
	}
	@Autowired
	PopulateService populateService;

	@Context
	private ServletContext context;
	@POST
	@Path("/save")
	public String uploadFile(InputStream inputStream)throws IOException {
		logger.info("populate  service iss=="+populateService);
		String succmessages="F";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		   String dt = date.getYear()+date.getMonth()+date.getDay()+date.getHours()+date.getMinutes()+date.getSeconds()+""+System.currentTimeMillis();
		String uploadedFileLocation= context.getRealPath("/DownloadedFiles/"+dt+".xml");
		if(succmessages.equals("S"))
		{
			logger.info("File Downloaded Successfully...Location iss=="+uploadedFileLocation);
			
		}
		else
		{
			logger.info("File did not Download");
		}
		return succmessages;

	}

	// save uploaded file to new location
	private String writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {
		String sucmess="F";

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));

			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			sucmess="S";
		} catch (IOException e) {
			sucmess="F";
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return sucmess;

	}
	
	

}


