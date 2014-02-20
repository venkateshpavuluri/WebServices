/**
 * 
 */
package com.mnt.mobiletracking.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author venkateshp
 *
 */
public class DbConnection {
static{
	try
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
}
	
	public static Connection getConnection()
	{
		Connection connection=null;
		try
		{
			
	 connection=DriverManager.getConnection("");
		
			}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}
}
	
	

