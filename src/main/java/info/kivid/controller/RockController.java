package info.kivid.controller;

import info.kivid.model.ImageApiResult;
import info.kivid.model.RockApiResult;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RockController {

    @Autowired
    private RockService rockService;

    @RequestMapping(value = "/rock/{id}", method = RequestMethod.GET)
    public String getRock(@PathVariable("id") String id, Model model) {
        RockApiResult rockApiResult = rockService.getRock(id);
        if (rockApiResult != null) {
            model.addAttribute("rock", rockApiResult);
            return "rock";
        }
        return "rock_error";
    }

    @RequestMapping(value = "/rock/{id}/gallery", method = RequestMethod.GET)
    public String getRockGallery(@PathVariable("id") String id, Model model) {
        List<ImageApiResult> images = rockService.getRockGallery(id);
        if (images != null) {
            model.addAttribute("images", images);
            return "rock_gallery";
        }
        return "rock_error";
    }

    @RequestMapping(value = "/rock/{id}/map", method = RequestMethod.GET)
    public String getRockMap(@PathVariable("id") String id, Model model) {
        return "rock_map";
    }
}