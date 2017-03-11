package info.kivid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RocksController {

    @RequestMapping("/rocks")
    public String rocks() {
        return "rocks";
    }
}
