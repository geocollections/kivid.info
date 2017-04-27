package info.kivid.controller;

import info.kivid.model.IntroductionText;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThemePageController {
    @Autowired
    private RockService rockService;

    @RequestMapping(value = "/theme/{id}", method = RequestMethod.GET)
    public String rocks(@PathVariable("id") String id, Model model) {
        IntroductionText introductionText;
        introductionText = rockService.getText(id);
        if(introductionText!=null) {
            model.addAttribute("rocksText", introductionText);
            return "theme";
        } else {
            model.addAttribute("rocksText", null);
            model.addAttribute("id", id);
            return "theme";
        }
    }
}

