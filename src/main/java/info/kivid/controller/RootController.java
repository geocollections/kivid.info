package info.kivid.controller;

import info.kivid.model.IntroductionText;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    @Autowired
    private RockService rockService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rocks(Model model) {
        IntroductionText introductionText;
        introductionText = rockService.getText("59");
        System.out.println(introductionText.getContent_et());
        if(introductionText!=null) {
            model.addAttribute("rocksText", introductionText);
            return "front_page";
        } else {
            model.addAttribute("rocksText", null);
            return "front_page";
        }
    }
}
