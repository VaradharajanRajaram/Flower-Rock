package com.vail.foodcourt.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vail.foodcourt.service.TemplateMethods;

@Controller
public class GrubDataUploadInExcel {
    
    @RequestMapping(value = "/upload-excel", method = RequestMethod.POST)
    public String uploadExcel(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException,
        IOException, JSONException {
        JSONObject foodMenuJson = new JSONObject();
        HashMap<String, String> foodMnuMap = new HashMap<String, String>();
        foodMenuJson = TemplateMethods.extractExcelContentByColumnIndex(0, foodMnuMap);
        System.out.println("ItemInJson:" + foodMenuJson);
        return "uploadExcel";
    }
    

}
