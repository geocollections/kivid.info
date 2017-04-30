package info.kivid.controller;

import info.kivid.model.LocationApiResult;
import info.kivid.model.RockNameSearchResult;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@RestController
public class RockRestController {

    @Autowired
    private RockService rockService;

    @RequestMapping(value = "/rock/{id}/locations", method = RequestMethod.GET)
    public List<LocationApiResult> getRockLocations(@PathVariable("id") String id) {
        return rockService.getRockLocations(id);
    }

    @RequestMapping(value = "/rock/search/{searchString}", method = RequestMethod.GET)
    public List<RockNameSearchResult> getRockNames(@PathVariable("searchString") String searchString, HttpServletRequest httpServletRequest) {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        Locale locale = slr.resolveLocale(httpServletRequest);
        return rockService.searchName(searchString, locale.getLanguage());
    }
}
