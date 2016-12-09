package com.travelex.framework.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class ConfigurationProperties {

	//private static final Logger log = Logger.getLogger(ConfigurationProperties.class);
	private final String PROPERTY_FILENAME = "config/configuration.properties";
	private Properties properties = new Properties();
	private static ConfigurationProperties  configProperties;
	public final static String Test_Data_Folder_Path = "Test_Data_File_Path";
	public final static String REMOTE_SERVER_URL = "REMOTE_SERVER_URL";
	public final static String COL_APPLICATION_URL = "COL_Application_URL";
	public final static String LOG_LEVEL = "log_level";
	public final static String LOG_FLAG = "log_flag";
	public final static String SCREENSHOT_FLAG = "screenshot_flag";
	public final static String CURRENTEXEC_RESULT = "currentExecResult_flag";
	public final static String CURRENTRELEASE = "currentRelease";
	public final static String CURRENTSPRINT = "currentSprint";
	public final static String EXECUTIONFLAG = "executionFlag";
	public final static String BROWSER_VERSION = "browserVersion";
	public final static String PLATFORM = "platform";
	public final static String BROWSER_NAME ="browserName";
	public final static String MAIL_LIST ="Mail_list";

	public ConfigurationProperties() {
		properties = loadProperties();
	}
	
	private Properties loadProperties() {
		File file = new File(PROPERTY_FILENAME);
		FileInputStream fileInput = null;
		Properties props = new Properties();

		try {
			fileInput = new FileInputStream(file);
			props.load(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return props;
	}
	
	public static ConfigurationProperties getInstance() {
		if (configProperties == null) {
			configProperties = new ConfigurationProperties();
		}
		return configProperties;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public boolean hasProperty(String key) {		
		return StringUtils.isNotBlank(properties.getProperty(key));
	}

}
