package com.mnt.mobiletrack.controller;
import javax.servlet.*;

import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.mnt.mobiletrack.bean.Mobile;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MobileTracking extends HttpServlet {
private	Logger logger=Logger.getLogger(MobileTracking.class);

	String HeaderURL, FooterURL;
	String RequestURL;
	String SqlStr;

	ServletContext Context;
	RequestDispatcher Dispatcher;

	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet psrs, rs;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Context = getServletContext();
		PrintWriter out = response.getWriter();
		String jsondata = "";
		try {

			
			con = com.mnt.mobiletracking.util.DbConnection.getConnection();
			String imeino = request.getParameter("imeino");
		
			SqlStr = "select Latitude,Longitude from MobileGEOCode where IMIE_Number='"+ imeino + "'  order by DT asc";
			pstmt = con.prepareStatement(SqlStr);
			rs = pstmt.executeQuery();
			String s="LatitudeLongitude";

			List<Mobile > mList = new ArrayList<Mobile>();
			while (rs.next()) {
				com.mnt.mobiletrack.bean.Mobile mb = new com.mnt.mobiletrack.bean.Mobile();
				mb.setSource(rs.getString(1));
				mb.setDestination(rs.getString(2));
				mb.setLatitude(s);
				mList.add(mb);
			}
			ObjectMapper mapper = new ObjectMapper();
			jsondata = mapper.writeValueAsString(mList);
		
			out.write(jsondata);
			//out.print(jsondata);

		} catch (Exception e) {
		logger.error(e.getMessage());
		} finally {
			try {
				con.close();
				pstmt.close();
				rs.close();
				out.close();
			} catch (Exception e) {
				
				logger.error(e.getMessage());
			}
		}
	}
}
