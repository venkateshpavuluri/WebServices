/**
 * 
 */
package com.mnt.mobiletracking.service;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jersey.spi.dispatch.RequestDispatcher;

/**
 * @author venkateshp
 *
 */
public class MobileServlet extends HttpServlet {
	private Logger logger=Logger.getLogger(MobileServlet.class);
	
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws FileNotFoundException,IOException,ServletException
	{
		HttpURLConnection connection=null;
	
		String uri="http://192.168.1.203:8067/MTS/rest/mobile/save";
		String uriEncode=urlEncode(uri);		
	response.setContentType("text/html");
	PrintWriter pw=response.getWriter();
	InputStream iu=new FileInputStream(getServletContext().getRealPath("/upload.xml"));
	//BufferedInputStream inputStream=new BufferedInputStream(iu);
	try
	{
connection=getHTTPConnection(uri);
//System.out.println("connection iss==="+connection);
DataOutputStream dataOutputStream=new DataOutputStream(connection.getOutputStream());

	int read = 0;
	byte[] bytes = new byte[1024];

//	out = new FileOutputStream(new File("D:\\Projecsts mnt\\MobileTracking\\src\\main\\webapp\\upload.xml"));
	
	while ((read = iu.read(bytes)) != -1) {
		dataOutputStream.write(bytes, 0, read);
	}
	dataOutputStream.flush();
	dataOutputStream.close();
	iu.close();
	InputStream inputStream=connection.getInputStream();
	int ch=inputStream.read();
	while(ch!=-1)
	{
		pw.write((char)ch);
		ch=inputStream.read();	
	}
	}
	catch(Exception e)
	{
		logger.error(e.getMessage());
	}
	}
	
	public HttpURLConnection getHTTPConnection(String url) throws Exception {

		HttpURLConnection _conn = null;
		URL serverAddress = null;
		for (int i = 0; i <= 1; i++) {

			try {
				serverAddress = new URL(url);
				_conn = (HttpURLConnection) serverAddress.openConnection();
				if (_conn != null) {

					_conn.setDoInput(true);
					_conn.setDoOutput(true);
					_conn.setUseCaches(false);
					_conn.setRequestMethod("POST");
					_conn.setRequestProperty("Connection", "Keep-Alive");
					
										
					_conn.setRequestProperty("Content-Type",
							"application/xml");
						
					_conn.setReadTimeout(120000);
					_conn.setConnectTimeout(120000);

				
					}
					_conn.connect();

					return _conn;
				}
			

			catch (MalformedURLException me) {
				me.printStackTrace();
				throw me;
			}

			catch (SocketTimeoutException se) {
				se.printStackTrace();
				_conn = null;
				if (i != 0)
					throw se;
			}

			catch (Exception e) {
				logger.error(e.getMessage());
				throw e;
			}
		}
		return null;
	}
	public static String urlEncode(String sUrl) {
		int i = 0;
		String urlOK = "";
		while (i < sUrl.length()) {
			if (sUrl.charAt(i) == ' ') {
				urlOK = urlOK + "%20";
			} else {
				urlOK = urlOK + sUrl.charAt(i);
			}
			i++;
		}
		return (urlOK);
	}
	
	

}
