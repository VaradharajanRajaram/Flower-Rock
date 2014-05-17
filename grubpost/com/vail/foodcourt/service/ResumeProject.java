package com.vail.foodcourt.service;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ResumeProject extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String name;
    @Value("${installPath}")
    String installPath;
    public void init(ServletConfig config){
       
        System.out.println(installPath);
        System.out.println(name);
    }
    
   
    

}
