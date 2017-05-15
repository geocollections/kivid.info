package info.kivid.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.kivid.model.*;
import info.kivid.service.helper.ApiConnectionHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;


@Service
public class RockService {

    private static final String SEARCH_NAME_REQUEST = "name";
    private static final String SEARCH_NAME_REQUEST_EN = "name_en";
    private static final String SEARCH_DESCRIPTION_REQUEST = "description,description_in_estonia,description_usage";
    private static final String SEARCH_DESCRIPTION_REQUEST_EN = "description_en,description_in_estonia_en,description_usage_en";
    private static final String SEARCH_FORMULA_REQUEST = "formula,formula_html";
    private static final String SEARCH_CLASSIFICATION_REQUEST = "rock_type__name,rock_rank__name,stratigraphy__stratigraphy,lithostratigraphy__stratigraphy,stratigraphy__id";

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
        Set<TreeApiResult> fullResults = new HashSet<TreeApiResult>();
        Set<TreeApiResult> results = new HashSet<TreeApiResult>();

        try {
            List<TreeApiResult> ress = getTreeResults(apiConnectionHelper, id);
            results.addAll(ress);
            fullResults.addAll(ress);

            if(!CollectionUtils.isEmpty(ress)) {
                for(TreeApiResult res: ress) {
                    if(!res.getParent_id().equals(id)) {
                        fullResults.addAll(getTreeResults(apiConnectionHelper, res.getParent_id()));
                    }
                }
            }
            return new ArrayList<TreeApiResult>(fullResults);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<TreeApiResult> getTreeResults(ApiConnectionHelper helper, String id) {
        String reqStringBegin = "rock_tree/?fields=rock_id,parent_id,rock__name,rock__name_en,parent__name,parent__name_en," +
                "rock_classification_id&multi_search=value:";
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

    public List<PropertyApiResult> getRockProperties(String id) {
        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock_property/?rock_id=" + id + "&format=json";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper mapper = new ObjectMapper();
            PropertyApiResultWrapper apiResultWrapper = mapper.readValue(resultString, PropertyApiResultWrapper.class);

            List<PropertyApiResult> adjusteds = new ArrayList<PropertyApiResult>();
            for(PropertyApiResult result: apiResultWrapper.getResults()) {
                adjusteds.add(adjustPropertyApiResult(result));
            }
            return adjusteds;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private PropertyApiResult adjustPropertyApiResult(PropertyApiResult result) {
        if(StringUtils.isEmpty(result.getValue_txt())) {
            String range = result.getValue_min() + " – " + result.getValue_max();
            result.setValue_txt(range);
        }
        return result;
    }

    public List<RockApiResult> search(SearchForm searchForm, String language) {
        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock/?format=json&multi_search=value:" + searchForm.getSearchString() + ";fields:";
        if (!searchForm.isSearchName() && !searchForm.isSearchDescription() && !searchForm.isSearchFormula() && !searchForm.isSearchClassification()) {
            requestString = "rock/?format=json&multi_search=value:" + searchForm.getSearchString() + ";fields:name,name_en,description,description_en,description_in_estonia,description_usage,description_in_estonia_en,description_usage_en,formula,formula_html,rock_type__name,rock_rank__name,stratigraphy__stratigraphy,lithostratigraphy__stratigraphy,stratigraphy__id;lookuptype:icontains";
        } else {
            if (language.equalsIgnoreCase("en")) {
                if (searchForm.isSearchName()) {
                    requestString = requestString.concat(SEARCH_NAME_REQUEST_EN);
                }
                if (searchForm.isSearchDescription()) {
                    if (searchForm.isSearchName()) {
                        requestString = requestString.concat("," + SEARCH_DESCRIPTION_REQUEST_EN);
                    } else {
                        requestString = requestString.concat(SEARCH_DESCRIPTION_REQUEST_EN);
                    }
                }

            } else {
                if (searchForm.isSearchName()) {
                    requestString = requestString.concat(SEARCH_NAME_REQUEST);
                }
                if (searchForm.isSearchDescription()) {
                    if (searchForm.isSearchName()) {
                        requestString = requestString.concat("," + SEARCH_DESCRIPTION_REQUEST);
                    } else {
                        requestString = requestString.concat(SEARCH_DESCRIPTION_REQUEST);
                    }
                }
            }
            if (searchForm.isSearchFormula()) {
                if (searchForm.isSearchName() || searchForm.isSearchDescription()) {
                    requestString = requestString.concat("," + SEARCH_FORMULA_REQUEST);
                } else {
                    requestString = requestString.concat(SEARCH_FORMULA_REQUEST);
                }
            }
            if (searchForm.isSearchClassification()) {
                if (searchForm.isSearchName() || searchForm.isSearchDescription() || searchForm.isSearchFormula()) {
                    requestString = requestString.concat("," + SEARCH_CLASSIFICATION_REQUEST);
                } else {
                    requestString = requestString.concat(SEARCH_CLASSIFICATION_REQUEST);
                }
            }
            requestString = requestString.concat(";lookuptype:icontains");
        }
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

    public List<RockSynonymSearchResult> searchSynonyms(String searchString) {
        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock_synonym/?format=json&fields=rock_id,name&name__icontains=" + searchString;

        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper<RockSynonymSearchResult> apiResultWrapper = objectMapper.readValue(resultString, ApiResultWrapper.class);
            return apiResultWrapper.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<RockNameSearchResult> searchName(String searchString, String language) {
        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock/?name__icontains=" + searchString + "&fields=id,name,name_en&format=json";
        if (language.equalsIgnoreCase("en")) {
            requestString = "rock/?name_en__icontains=" + searchString + "&fields=id,name,name_en&format=json";
        }
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public IntroductionText getText(String id) {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "webpages/"+id +"?format=json";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper<IntroductionText> result = objectMapper.readValue(resultString, new TypeReference <ApiResultWrapper<IntroductionText>>() {});
            return result.getResults().get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public List<Parents> getParents() {

        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock_tree/?format=json&fields=parent_id,parent__name,parent__name_en,rock_classification__hierarchy_top_rock_id";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper<Parents> result = objectMapper.readValue(resultString, new TypeReference <ApiResultWrapper<Parents>>() {});
            List<Parents> resultFiltered = result.getResults().stream()
                    .filter(p -> p.getRock_classification__hierarchy_top_rock_id()==3).sorted().collect(toList());
            SortedSet<Parents> sortedSet = new TreeSet<Parents>();
            sortedSet.addAll(resultFiltered);
            List<Parents> resultUnique = sortedSet.stream().collect(toList());
            System.out.println(resultUnique.toString());
            return resultUnique;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Parents> getParents2() {
        ApiConnectionHelper apiConnectionHelper = new ApiConnectionHelper();
        String requestString = "rock_tree/?format=json&fields=parent_id,parent__name,parent__name_en,rock_classification__hierarchy_top_rock_id&parent_id__lt=500";
        try {
            String resultString = apiConnectionHelper.makeRequest(requestString);
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResultWrapper<Parents> result = objectMapper.readValue(resultString, new TypeReference <ApiResultWrapper<Parents>>() {});
            List<Parents> resultFiltered = result.getResults().stream()
                    .filter(p -> p.getRock_classification__hierarchy_top_rock_id()<20).sorted().collect(toList());
            SortedSet<Parents> sortedSet = new TreeSet<Parents>();
            sortedSet.addAll(resultFiltered);
            List<Parents> resultUnique = sortedSet.stream().collect(toList());
            System.out.println(resultUnique.toString());
            return resultUnique;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
