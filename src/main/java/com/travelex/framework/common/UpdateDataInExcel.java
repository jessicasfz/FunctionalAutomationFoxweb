package com.travelex.framework.common;


import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;

public class UpdateDataInExcel {
		
	public Connection getConnection() {
		
		ConfigurationProperties configurationProperties = new ConfigurationProperties();
		 String fileN = configurationProperties.getProperty(ConfigurationProperties.Test_Data_Folder_Path);
		 fileN = System.getProperty("user.dir") + fileN;			
		Connection connection = null;
		Fillo fil = new Fillo();
		try{
			connection = fil.getConnection(fileN);
		}catch(FilloException e){
			e.printStackTrace();
		}
		return connection;
	}
		
	public void updateDataInExcel(String sheetName, String colName, String textTobeUpdated, String AutomationID) throws FilloException{
		if(textTobeUpdated==null || textTobeUpdated=="" || textTobeUpdated.contains("NA")){
			return;
		}
		String updateQuery = "Update "+sheetName+" set "+colName+"='"+textTobeUpdated+"' where AutomationID = '"+AutomationID+"'";
		getConnection().executeUpdate(updateQuery);
	}

}
