package com.vail.foodcourt.service;

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBConnector;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.tools.ConnectionPoolStat;

public class ResumeProject extends HttpServlet {

	/**
     * 
     */
private static final Logger log=Logger.getLogger(ResumeProject.class);
	private static final long serialVersionUID = 1L;
	Properties folderCreationproperties=null;
	public void init(ServletConfig config)throws ServletException {
		super.init(config);
		folderCreationproperties=new Properties();
		folderCreationproperties=TemplateMethods.readPropertyFleFrmClsPth("/resumeApplication.properties");
		folderFactory();
		resumeLogger();
	resumeMongoInstance();
		
	}

	
	public void folderFactory(){
		String[] folderName={"resumeProjectLog","coreLog","dbLog","dataRetriveLog","xml"};
		
		
		for(int m=0;m<folderName.length;m++){
			File folder=new File(folderCreationproperties.getProperty("foodCourtAppLogPath")+GeneralConstants.FRONT_SLASH+folderName[m]);
			if(!folder.exists())
				folderProducer(folderName[m]);
		}
		
	}
	public void folderProducer(String folderName){
		File folder=new File(folderCreationproperties.getProperty("foodCourtAppLogPath")+GeneralConstants.FRONT_SLASH+folderName);
		folder.mkdir();
		log.info("folder "+folderName+" created successfully");
		
	}
	public void resumeLogger(){
		Properties logFilesProp=new Properties();
		logFilesProp=TemplateMethods.readPropertyFleFrmClsPth("/log4j.properties");
		logFilesProp.put("log4j.appender.R.File", GeneralConstants.$CORE_LOG);
		logFilesProp.put("log4j.appender.R.File", GeneralConstants.$DATA_RETRIVE_LOG);
		logFilesProp.put("log4j.appender.R.File", GeneralConstants.$DB_LOG);
		logFilesProp.put("log4j.appender.R.File", GeneralConstants.$RESUM_PROJECT_LOG);
		PropertyConfigurator.configure(logFilesProp);
		log.info("VARADHARAJAN RAJARAM");
		
		
	}
	public void resumeMongoInstance(){
		try{
		DbPrerequisite.initConfig(GeneralConstants.$MONGO_PROPERTIEFILE);
		DbResumeServices.startDataBase();
	/*	Mongo m=eval.initDatabase(DbPrerequisite.PATH);
		DB db=m.getDB(DbPrerequisite.DB);
		DBCollection collection=db.getCollection("tree");
		System.out.println(db.getCollection("tree"));
		System.out.println(m.getMaxBsonObjectSize());*/
		
		}catch(Exception io){
			
			log.info("Resume loggerMongoInstance has issues");
		}
	}
		
	

}
