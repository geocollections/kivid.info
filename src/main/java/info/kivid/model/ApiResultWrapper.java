package info.kivid.model;

import java.util.List;

public class ApiResultWrapper {

    private int count;
    private List<ImageApiResult> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ImageApiResult> getResults() {
        return results;
    }

    public void setResults(List<ImageApiResult> results) {
        this.results = results;
    }
}
