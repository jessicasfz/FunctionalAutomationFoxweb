package com.travelex.framework.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class RAPUtilities {	
	
	public Connection dbConnect(String URL,String dbName, String uName, String pwd) throws SQLException{
		Connection con = null;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+URL+"/"+dbName,uName,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}	
	
	public Connection dbConnect(String URL,String dbName) throws SQLException{
		Connection con = null;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:jtds:sqlserver://"+URL+"/"+dbName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}