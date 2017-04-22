package info.kivid.controller;

import info.kivid.model.LocationApiResult;
import info.kivid.model.RockNameSearchResult;
import info.kivid.service.RockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RockRestController {

    @Autowired
    private RockService rockService;

    @RequestMapping(value = "/rock/{id}/locations", method = RequestMethod.GET)
    public List<LocationApiResult> getRockLocations(@PathVariable("id") String id) {
        return rockService.getRockLocations(id);
    }

    @RequestMapping(value = "/rock/search/{searchString}", method = RequestMethod.GET)
    public List<RockNameSearchResult> getRockNames(@PathVariable("searchString") String searchString) {
        return rockService.searchName(searchString);
    }
}
