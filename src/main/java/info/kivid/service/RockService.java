package info.kivid.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.kivid.model.*;
import info.kivid.service.helper.ApiConnectionHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Service
public class RockService {

    public RockApiResult getRock(String id) {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock/" + id + "?in_portal=1&format=json";
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

    public List<TreeApiResult> getRockTree(String id) {
        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        Set<TreeApiResult> results = new HashSet<TreeApiResult>();

        try {
            results.addAll(getTreeResults(apiConnectionHelper, id));

            if(!CollectionUtils.isEmpty(results)) {
                for(TreeApiResult res: results) {
                    if(!res.getParent_id().equals(id)) {
                        results.addAll(getTreeResults(apiConnectionHelper, res.getParent_id()));
                    }
                }
            }
            return new ArrayList<TreeApiResult>(results);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<TreeApiResult> getTreeResults(ApiConnectionHelper helper, String id) {
        String reqStringBegin = "rock_tree/?fields=rock_id,parent_id,rock__name&multi_search=value:";
        String reqStringEnd = ";fields:rock_id,parent_id;lookuptype:exact&format=json";
        String requestString = reqStringBegin + id + reqStringEnd;

        try {
            String resultString = helper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            TreeApiResultWrapper apiResultWrapper = objectMapper.readValue(resultString, TreeApiResultWrapper.class);

            return Arrays.asList(apiResultWrapper.getResults());
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<TreeApiResult>();
        }
    }

    public List<ImageApiResult> getRockGallery(String id) {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock_image/?rock_id=" + id + "&format=json";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper<ImageApiResult> apiResultWrapper = objectMapper.readValue(resultString, ApiResultWrapper.class);
            return apiResultWrapper.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<LocationApiResult> getRockLocations(String id) {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock_locality/?rock_id=" + id + "&format=json";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper<LocationApiResult> apiResultWrapper = objectMapper.readValue(resultString, ApiResultWrapper.class);
            return apiResultWrapper.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<RockApiResult> search(String searchString) {
        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock/?name__icontains=" + searchString + "&format=json";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper<RockApiResult> apiResultWrapper = objectMapper.readValue(resultString, ApiResultWrapper.class);
            return apiResultWrapper.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RockNameSearchResult> searchName(String searchString) {
        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock/?name__icontains=" + searchString + "&fields=id,name&format=json";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper<RockNameSearchResult> apiResultWrapper = objectMapper.readValue(resultString, ApiResultWrapper.class);
            return apiResultWrapper.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
