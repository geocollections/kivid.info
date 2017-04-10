package info.kivid.controller;

import info.kivid.model.RockApiResult;
import info.kivid.model.SearchForm;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private RockService rockService;

    @GetMapping("/search")
    public String searchForm(Model model, @ModelAttribute SearchForm searchForm) {
        if (searchForm.getSearchString() != null) {
            List<RockApiResult> rocks = rockService.search(searchForm.getSearchString());
            model.addAttribute("rocks", rocks);
        }
        return "search";
    }
}
