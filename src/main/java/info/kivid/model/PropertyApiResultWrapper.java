package info.kivid.model;


public class PropertyApiResultWrapper {

    private String count;

    private PropertyApiResult[] results = new PropertyApiResult[1];

    public PropertyApiResult[] getResults() {
        return results;
    }

    public void setResults(PropertyApiResult[] results) {
        this.results = results;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
