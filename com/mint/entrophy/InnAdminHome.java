/**
 * 
 * Author:Varadharajan.R
 * File Name:Handling Redirection 
 * 
 */
package mint.entrophy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InnAdminHome {

    @RequestMapping(value = "/")
    public String adminPage() {
        System.out.println("-()-()-()-()-()-()-()-(\"STARTUP\")-()-()-()-()-()-()-()");
        return "innAdminPage";
    }

    @RequestMapping(value = "/revealCibos")
    public String revealCibos() {
        System.out.println("-(\".\")-(\".\")-(\".\")--(\"REVEALCIBOS\")-(\".\")-(\".\")-(\".\")-");
        return "revealCibos";
    }
}
