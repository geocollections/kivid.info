package info.kivid.model;

public class TreeApiResultWrapper {

    private String count;

    private TreeApiResult[] results = new TreeApiResult[1];

    public TreeApiResult[] getResults() {
        return results;
    }

    public void setResults(TreeApiResult[] results) {
        this.results = results;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
