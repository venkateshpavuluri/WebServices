package com.mnt.Servlets;
/**
 * @author srinivas k
 *
 */
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnt.mobiletracking.util.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")

public class MobileTracking extends HttpServlet {
	private Logger logger=Logger.getLogger(MobileTracking.class);
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
         	con = DbConnection.getConnection();
			String imeino = request.getParameter("imeino");
			//String date=  request.getParameter("date");
			//System.out.println("Date is"+date);
			//System.out.println("IMEI IS" + imeino);
		//	SqlStr = "select Latitude,Longitude from MobileGEOCode where IMIE_Number='"+ imeino + "'  order by DT asc";
		SqlStr="select im.IMIENumber, me.Latitude, me.Longitude, me.DT from MobileGEOCode me,dbo.EmployeeIMIE im where me.IMIE_Number=im.IMIENumber and me.IMIE_Number='"+ imeino + "'";
			pstmt = con.prepareStatement(SqlStr);
			rs = pstmt.executeQuery();
			String s="LatitudeLongitude";
			List<Mobile> mList = new ArrayList<Mobile>(); 
			while (rs.next()){
				Mobile mb = new Mobile();
				mb.setSource(rs.getString(2));
				mb.setDestination(rs.getString(3));
				mb.setLatitude(s);
				mList.add(mb);
			}
			ObjectMapper mapper = new ObjectMapper();
			jsondata = mapper.writeValueAsString(mList);
			//System.out.println(jsondata);
			out.write(jsondata);
			//out.print(jsondata);

		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if(con!=null)
				{
				con.close();
				}
				if(pstmt!=null)
				{
				pstmt.close();
				}
				if(rs!=null)
				{
				rs.close();
				}
				out.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}
