package info.kivid.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourcesController {

    @RequestMapping("/resources")
    public String rocks() {
        return "/resources";
    }
}
