package com.travelex.cif.pages;

import java.io.File;

public class CreateHtmlReport {
	
	public void createDirectoryForReports(){
		String directoryLocation = System.getProperty("user.dir") +"/target/CIFValidationReport";				
		File directory = new File(directoryLocation); 
		if(!directory.exists()){	
			directory.mkdir();		
		}
	}	
}