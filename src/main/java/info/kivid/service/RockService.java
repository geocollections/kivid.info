package info.kivid.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.kivid.model.RockApiResultWrapper;
import info.kivid.model.RockApiResult;
import info.kivid.service.helper.ApiConnectionHelper;
import org.springframework.stereotype.Service;

@Service
public class RockService {

    public RockApiResult getRock(String id) {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock/" + id;
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
}
