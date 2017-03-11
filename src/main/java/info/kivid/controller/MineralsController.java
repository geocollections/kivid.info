package info.kivid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MineralsController {

    @RequestMapping("/minerals")
    public String minerals() {
        return "minerals";
    }
}
