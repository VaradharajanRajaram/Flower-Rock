package com.vail.foodcourt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirctionController {
    @RequestMapping(value = "/")
    public String adminPage() {
        System.out.println("-()-()-()-()-()-()-()-(\"STARTUP\")-()-()-()-()-()-()-()");
        return "uploadExcel";
    }

    @RequestMapping(value = "/revealCibos")
    public String revealCibos() {
        System.out.println("-(\".\")-(\".\")-(\".\")--(\"REVEALCIBOS\")-(\".\")-(\".\")-(\".\")-");
        return "revealCibos";
    }

}
