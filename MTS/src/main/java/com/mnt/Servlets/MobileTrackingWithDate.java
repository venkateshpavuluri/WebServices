package com.mnt.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mnt.mobiletracking.util.DbConnection;

public class MobileTrackingWithDate extends HttpServlet {

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
			String date = request.getParameter("datepick");
			String[] datessub=date.split("-");
			//String date=  request.getParameter("date");
			//System.out.println("Date is"+date);
			//System.out.println("IMEI IS" + imeino);
		//	SqlStr = "select Latitude,Longitude from MobileGEOCode where IMIE_Number='"+ imeino + "'  order by DT asc";
		//SqlStr="select im.IMIENumber, me.Latitude, me.Longitude, me.DT from MobileGEOCode me,dbo.EmployeeIMIE im where me.IMIE_Number=im.IMIENumber and me.IMIE_Number='"+ imeino + "'";
		SqlStr="select me.Latitude,me.Longitude,me.DT from MobileGEOCode me where DATEPART(year,me.DT)="+datessub[0]+" and  DATEPART(month,me.DT)="+datessub[1]+" and DATEPART(day,me.DT)="+datessub[2]+""; 
		pstmt = con.prepareStatement(SqlStr);
			rs = pstmt.executeQuery();
			String s="LatitudeLongitude";
			List<Mobile> mList = new ArrayList<Mobile>(); 
			while (rs.next()){
				Mobile mb = new Mobile();
				mb.setSource(rs.getString(1));
				mb.setDestination(rs.getString(2));
				mb.setLatitude(s);
				mList.add(mb);
			}
			ObjectMapper mapper = new ObjectMapper();
			jsondata = mapper.writeValueAsString(mList);
			//System.out.println(jsondata);
			out.write(jsondata);
			//out.print(jsondata);

		} catch (Exception e) {
			e.printStackTrace();
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

			}
		}
	} 

}
