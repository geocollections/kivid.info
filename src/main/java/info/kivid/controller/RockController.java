package info.kivid.controller;

import info.kivid.model.RockApiResult;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RockController {

    @Autowired
    private RockService rockService;

    @RequestMapping(value = "/rock/{id}", method = RequestMethod.GET)
    public String getRock(@PathVariable("id") String id, Model model) {
        RockApiResult rockApiResult= rockService.getRock(id);
        model.addAttribute("rock", rockApiResult);
        return "rock";
    }
}
