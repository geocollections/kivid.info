package info.kivid.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LocationContextController {

    public static final String IN_ESTONIA_SESSION_ATTRIBUTE_NAME = "inEstonia";

    @PostMapping("/setLocationContext")
    public void search(@RequestParam Boolean inEstonia, HttpSession session) {
        session.setAttribute(IN_ESTONIA_SESSION_ATTRIBUTE_NAME, inEstonia);
    }
}
