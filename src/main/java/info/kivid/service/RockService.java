package info.kivid.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.kivid.model.ApiResultWrapper;
import info.kivid.model.ImageApiResult;
import info.kivid.model.RockApiResultWrapper;
import info.kivid.model.RockApiResult;
import info.kivid.service.helper.ApiConnectionHelper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RockService {

    public RockApiResult getRock(String id) {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock/" + id + "?format=json";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            RockApiResultWrapper rockApiResultWrapper = objectMapper.readValue(resultString, RockApiResultWrapper.class);
            RockApiResult initialResult = rockApiResultWrapper.getResults()[0];

            return adjustRockApiResult(initialResult);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private RockApiResult adjustRockApiResult(RockApiResult initial) {
        if (initial == null){
            return initial;
        } else {
            if(initial.getMindat_id() != null) {
                //https://www.mindat.org/min-3826.html
                String mindatLink = "https://www.mindat.org/min-" + initial.getMindat_id() + ".html";
                initial.setMindat_id(mindatLink);
            }
            if(initial.getLink_wikipedia_en() != null) {
                //https://en.wikipedia.org/wiki/Pyrite
                String wikiLink = "https://en.wikipedia.org/wiki/" + initial.getLink_wikipedia_en();
                initial.setLink_wikipedia_en(wikiLink);
            }
            if(initial.getLink_wikipedia() != null) {
                //https://et.wikipedia.org/wiki/P%C3%BCriit
                String wikiLinkEst = "https://et.wikipedia.org/wiki/" + initial.getLink_wikipedia();
                initial.setLink_wikipedia(wikiLinkEst);
            }
        }
        return initial;
    }

    public List<ImageApiResult> getRockGallery(String id) {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "specimen_image/?rockimages__rock_id=" + id + "&fields=agent__agent,date,date_taken_free,image_url,licence__licence,copyright_agent__agent,specimen__id&format=json";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper apiResultWrapper = objectMapper.readValue(resultString, ApiResultWrapper.class);
            return apiResultWrapper.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
