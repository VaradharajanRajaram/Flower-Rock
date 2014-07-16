package com.vail.foodcourt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirctionController {
	@Value("$(local_oauth_client_id)")
	private String msg;
    @RequestMapping(value = "/")
    public String adminPage() {
        System.out.println("-()-()-()-()-()-()-()-(\"STARTUP\")-()-()-()-()-()-()-()");
       System.out.println(msg);
      // return  "innAdminPage";
       return "uploadExcel";
    }

    @RequestMapping(value = "/revealCibos")
    public String revealCibos() {
        System.out.println("-(\".\")-(\".\")-(\".\")--(\"REVEALCIBOS\")-(\".\")-(\".\")-(\".\")-");
        return "revealCibos";
    }

}
