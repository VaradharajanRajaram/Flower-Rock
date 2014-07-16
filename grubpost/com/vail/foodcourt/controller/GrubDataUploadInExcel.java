package com.vail.foodcourt.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vail.foodcourt.service.TemplateMethods;

@Controller
@MultipartConfig
public class GrubDataUploadInExcel {
	private static final Logger log=Logger.getLogger(new Object() { }.getClass().getSimpleName());
   
	@RequestMapping(value = "/upload-excel-file", method = RequestMethod.POST)
    public String uploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Upload file shifting to Server Directory Process is starting..");
    		File uploadedFile=null;
    		String excelName;
    		String excelFileFolder=request.getServletContext().getInitParameter("ExcelPath");
                File path = null;
                FileItem item=null;
             /*   DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List fileItems = upload.parseRequest(request);*/
                boolean isMultiPart=ServletFileUpload.isMultipartContent(request);
                List fileItems=new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                Iterator i = fileItems.iterator();
                
                	FileItem fi = (FileItem)i.next();
                	if ( !fi.isFormField () )	
                    {
                       // Get the uploaded file parameters
                		excelName = fi.getName();
                		 if (isMultiPart) {
                             path = new File(excelFileFolder + File.separator);
                             if (!path.exists()) {
                                 boolean status = path.mkdirs();
                                 log.info("FOLDER CREATED OR NOT : "+status+"");
                             }
                              uploadedFile = new File(path +"\\"+ excelName);  
                             fi.write(uploadedFile);
                 } 
                    }
                
        JSONObject foodMenuJson = new JSONObject();
        HashMap<String, String> foodMnuMap = new HashMap<String, String>();
        foodMenuJson = TemplateMethods.extractExcelContentByColumnIndex(0, foodMnuMap,uploadedFile);
        System.out.println("ItemInJson:" + foodMenuJson);
        return "uploadExcel";
    }
   

}
