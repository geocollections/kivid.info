package info.kivid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThemePageController {

    @RequestMapping("/rocks")
    public String rocks() {
        return "rocks";
    }

    @RequestMapping("/minerals")
    public String minerals() {
        return "minerals";
    }

    @RequestMapping("/resources")
    public String resources() {
        return "/resources";
    }
}
