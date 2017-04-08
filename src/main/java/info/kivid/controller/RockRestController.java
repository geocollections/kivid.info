package info.kivid.controller;

import info.kivid.model.Location;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RockRestController {

    //TODO: redo this controller when rock location works on API side
    @RequestMapping(value = "/rock/{id}/locations", method = RequestMethod.GET)
    public List<Location> getRockLocations(@PathVariable("id") String id) {

        Location location1 = new Location(1, "Test1", 59.436962, 24.753574);
        Location location2 = new Location(2, "Test2", 58.3858, 24.4966);
        Location location3 = new Location(3, "Test3", 58.3776, 26.7290);
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        return locations;
    }
}
