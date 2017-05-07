package info.kivid.controller;

import info.kivid.model.RockApiResult;
import info.kivid.model.RockSynonymSearchResult;
import info.kivid.model.SearchForm;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
public class SearchController {

    @Autowired
    private RockService rockService;

    @GetMapping("/search")
    public String search(Model model, @ModelAttribute SearchForm searchForm, HttpServletRequest httpServletRequest) {
        if (searchForm.getSearchString() != null && searchForm.getSearchString().length() > 0) {
            SessionLocaleResolver slr = new SessionLocaleResolver();
            Locale locale = slr.resolveLocale(httpServletRequest);
            List<RockApiResult> rocks = rockService.search(searchForm, locale.getLanguage());
            model.addAttribute("rocks", rocks);
        }
        /*if (searchForm.getSearchSynonymString() != null && searchForm.getSearchSynonymString().length() > 0) {
            List<RockSynonymSearchResult> rocks = rockService.searchSynonyms(searchForm.getSearchSynonymString());
            model.addAttribute("synonyms", rocks);
        }*/
        return "search";
    }
}
