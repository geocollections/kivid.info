package info.kivid.controller;

import info.kivid.model.IntroductionText;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("inEst")
public class RootController {

    @Autowired
    private RockService rockService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rocks(Model model, HttpSession session) {
        boolean inEst;
        if (!model.containsAttribute("inEst")){
            inEst = true;
            model.addAttribute("inEst", inEst);
            session.setAttribute("inEst",inEst);
        }
        IntroductionText introductionText;
        introductionText = rockService.getText("59");
        if(introductionText!=null) {
            model.addAttribute("rocksText", introductionText);
            return "front_page";
        } else {
            model.addAttribute("rocksText", null);
            return "front_page";
        }
    }


}
