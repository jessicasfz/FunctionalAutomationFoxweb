package com.travelex.framework.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.Reporter;

public class SendReport {
	
	static String absolutePath=new File("").getAbsolutePath();
	static ConfigurationProperties configurationProperties = new ConfigurationProperties();
	
	@SuppressWarnings("null")
	public void sendMail() throws Exception {	   
		{
			
			//String attachFiles = "";
			//createZip();
			zipOutout();
			
			String [] attachFiles={absolutePath+"\\target\\TestReport.zip"};
		      // Recipient's email ID needs to be mentioned.
		     // String to = "sneha.chemburkar@travelex.com";
			String[] to=null;
			 String toList= configurationProperties.getProperty(ConfigurationProperties.MAIL_LIST);
			 if(toList.length()>1)
			 {
			  to = toList.split(",",-1);
			 }
			 else
			 { to[0]="SeleniumAutomationTeam@travelex.com";}
		      // Sender's email ID needs to be mentioned
		      String from = "SeleniumAutomationTeam@travelex.com";


		      // Setup mail server
		      Properties props = new Properties();
		      
		      props.put("mail.smtp.host","145.224.216.57 ");
		      props.put("mail.smtp.port","25");
		    
		      Session session = Session.getDefaultInstance(props);
		      
		     

		      try{
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);
		         
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));
		       
		         // Set To: header field of the header.
		       //  message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		         InternetAddress[] toAddress = new InternetAddress[to.length];

		            // To get the array of addresses
		            for( int i = 0; i < to.length; i++ ) {
		                toAddress[i] = new InternetAddress(to[i]);
		            }

		            for( int i = 0; i < toAddress.length; i++) {
		            	message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		            }
		         // Set Subject: header field
		         message.setSubject("Cucumber Automation Reports");
		       
		        
					MimeBodyPart messageBodyPart =   new MimeBodyPart();
					
						    //fill message
						    messageBodyPart.setText("Automation reports for  "+new Date());

						    Multipart multipart = new MimeMultipart();
						    
						    multipart.addBodyPart(messageBodyPart);

						    // Part two is attachment
						    messageBodyPart = new MimeBodyPart();
						
						    // adds attachments
					        if (attachFiles != null && attachFiles.length > 0) {
					            for (String filePath : attachFiles) {
					                MimeBodyPart attachPart = new MimeBodyPart();
					 
					                try {
					                    attachPart.attachFile(filePath);
					                } catch (IOException ex) {
					                    ex.printStackTrace();
					                }
					 
					                multipart.addBodyPart(attachPart);
					            }
					        }
						    //System.out.println("after for loop");
						    
						    // Put parts in message
						    message.setContent(multipart);
						   
					
		                   // Send message
		                   Transport.send(message);
		                
		                   Reporter.log("Sent mail successfully....");
		      }catch (Exception e) {
		    	  throw new IllegalStateException("Mail not send", e);
		      }
		      }
		   }
		
	
	public void zipOutout() throws IOException{
		try {
			String outFilename = absolutePath+"\\target\\TestReport.zip";
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outFilename));
			String dir = absolutePath+"\\target\\cucumber-html-reports";
			zipDir(dir, zos); 
		    //close the stream 
		    zos.close(); 
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		    //assuming that there is a directory named inFolder (If there 
		    //isn't create one) in the same directory as the one the code 
		    //call the zipDir method 
		    
	}
		
	public void zipDir(String dir2zip, ZipOutputStream zos)
	{ 
	    try 
	   { 
	        //create a new File object based on the directory we have to zip 
	    	File zipDir = new File(dir2zip); 
	        //get a listing of the directory content 
	        String[] dirList = zipDir.list(); 
	        byte[] readBuffer = new byte[2156]; 
	        int bytesIn = 0; 
	        //loop through dirList, and zip the files 
	        for(int i=0; i<dirList.length; i++) 
	        { 
	            File f = new File(zipDir, dirList[i]); 
	        if(f.isDirectory()) 
	        { 
	                //if the File object is a directory, call this 
	                //function again to add its content recursively 
	            String filePath = f.getPath(); 
	            zipDir(filePath, zos); 
	                //loop again 
	            continue; 
	        } 
	            //if we reached here, the File object f was not  a directory 
	            //create a FileInputStream on top of f 
	            FileInputStream fis = new FileInputStream(f); 
	           // create a new zip entry 
	            ZipEntry anEntry = new ZipEntry(f.getPath()); 
	            //place the zip entry in the ZipOutputStream object 
	            zos.putNextEntry(anEntry); 
	            //now write the content of the file to the ZipOutputStream 
	            while((bytesIn = fis.read(readBuffer)) != -1) 
	            { 
	                zos.write(readBuffer, 0, bytesIn); 
	            } 
	           //close the Stream 
	           fis.close(); 
	    } 
	} 
	catch(Exception e) 
	{ 
		e.printStackTrace();
	    //handle exception 
	} 
	}


}
