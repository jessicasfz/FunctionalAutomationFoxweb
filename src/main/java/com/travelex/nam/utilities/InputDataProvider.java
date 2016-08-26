package com.travelex.nam.utilities;

public class InputDataProvider {

/*	
	static Connection con=	getConnection(new File("").getAbsolutePath()+"/TestData/WNPDatasheet.xls");
	
	public static Connection getConnection(String strFileName) {
		Connection connection = null;
		Fillo fillo = new Fillo();
		try {
			connection = fillo.getConnection(strFileName);
		} catch (FilloException e) {

			e.printStackTrace();
		}

		return connection;
	}
	public static Recordset getRecordSet(String strQuery) {

		Recordset recordset = null;
		try {
			System.out.println("inside getRecordSet strQuery is "+strQuery);
			recordset = con.executeQuery(strQuery);
		} catch (FilloException e) {
			
			e.printStackTrace();
		}
		return recordset;
	}*/

	public static  Object[][] getTableArray(String AutomationID){
		String[][] tabArray=null;
/*		ConfigurationProperties configurationProperties = new ConfigurationProperties();
		boolean executionFlag = Boolean.parseBoolean(configurationProperties
				.getProperty(ConfigurationProperties.EXECUTIONFLAG));
    	String scenario= AutomationID;
    	String strQuery ="";
    	
    	if(!executionFlag){
    		strQuery = "Select * from TestData where Scenario = '"+scenario+"'";
    			}
    		else
    			{
    		strQuery = "Select * from TestData where Scenario = '"+scenario+"'and Execution_Flag='Yes'";
    			}
    	
	 	try{
    	System.out.println("Query "+strQuery);
    	Recordset rs= getRecordSet(strQuery);
    	int row=0;
    
    	tabArray= new String[rs.getCount()][rs.getFieldNames().size()];
    	while (rs.hasNext()) {
    		rs.next();
    		int col=0;
    		for(String header : rs.getFieldNames()){
    			tabArray[row][col]=rs.getField(header);
    			col=col+1;
    		}
    		row=row+1;
		}
    	}catch(Exception e){
    		System.out.println("error in getTableArray()"+e.getMessage());
    	}*/
    	
        return(tabArray);     
 
    }
	

}
	

