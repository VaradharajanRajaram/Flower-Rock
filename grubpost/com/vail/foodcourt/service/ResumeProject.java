package com.vail.foodcourt.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ResumeProject extends HttpServlet {

	/**
     * 
     */

	private static final long serialVersionUID = 1L;
	Properties folderCreationproperties=null;
	public void init(ServletConfig config)throws ServletException {
		super.init(config);
		folderCreationproperties=new Properties();
		folderCreationproperties=TemplateMethods.readPropertyFleFrmClsPth("/resumeApplication.properties");
		folderProducePool();
		resumeLogger();
	}

	
	public void folderProducePool(){
		String[] folderName={"resumeProjectLog","coreLog","dbLog","dataRetriveLog","xml"};
		for(int m=0;m<folderName.length;m++)
		folderProduce(folderName[m]);
	}
	public void folderProduce(String folderName){
		File folder=new File(folderCreationproperties.getProperty("foodCourtAppLogPath")+GeneralConstants.FRONT_SLASH+folderName);
		if(!folder.exists())
		folder.mkdir();
	}
	public void resumeLogger(){
		Properties logFilesProp=new Properties();
		logFilesProp.put("log4j.appender.APPL_LOG.File", "");
	}
	
	

}
