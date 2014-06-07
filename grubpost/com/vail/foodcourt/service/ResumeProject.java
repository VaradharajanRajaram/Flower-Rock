package com.vail.foodcourt.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

public class ResumeProject extends HttpServlet {

	/**
     * 
     */

	private static final long serialVersionUID = 1L;
	Properties folderCreationproperties;
	public void init(ServletConfig config) {
		readPropertiesFile("resumeApplication.properties");
		folderProducePool();
		resumeLogger();
	}

	public void readPropertiesFile(String propertyFileName) {
		try {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(propertyFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '"
						+ propertyFileName + "' not found in the classpath");
			}
			folderCreationproperties = new Properties();
			folderCreationproperties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void folderProducePool(){
		folderProduce("resumeProjectLog");
		folderProduce("coreLog");
		folderProduce("dbLog");
		folderProduce("dataRetriveLog");
		folderProduce("xml");
		
	}
	public void folderProduce(String folderName){
		File folder=new File(folderCreationproperties.getProperty("foodCourtApp")+GeneralConstants.FRONT_SLASH+folderName);
		if(!folder.exists())
		folder.mkdir();
	}
	public void resumeLogger(){
		Properties logFilesProp=new Properties();
		logFilesProp.put("log4j.appender.APPL_LOG.File", "");
		
	}
	
	

}
