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
            return rockApiResultWrapper.getResults()[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ImageApiResult> getRockGallery(String id) {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "specimen_image/?rockimages__rock_id=" + id + "&fields=image_url&format=json";
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
