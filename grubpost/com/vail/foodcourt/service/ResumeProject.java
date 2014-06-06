package com.vail.foodcourt.service;

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

	public void init(ServletConfig config) {
		readPropertiesFile("resumeApplication.properties");
	}

	public void readPropertiesFile(String propertyFileName) {
		try {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(propertyFileName);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '"
						+ propertyFileName + "' not found in the classpath");
			}
			Properties properties = new Properties();
			properties.load(inputStream);
			String url = properties.getProperty("oauthscopes");
			System.out.println(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
