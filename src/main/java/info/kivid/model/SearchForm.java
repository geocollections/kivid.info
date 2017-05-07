package info.kivid.model;


public class SearchForm {

    private boolean searchName;

    private boolean searchDescription;

    private boolean searchFormula;

    private boolean searchClassification;

    private String searchString;

    private String searchSynonymString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public boolean isSearchName() {
        return searchName;
    }

    public void setSearchName(boolean searchName) {
        this.searchName = searchName;
    }

    public boolean isSearchDescription() {
        return searchDescription;
    }

    public void setSearchDescription(boolean searchDescription) {
        this.searchDescription = searchDescription;
    }

    public boolean isSearchFormula() {
        return searchFormula;
    }

    public void setSearchFormula(boolean searchFormula) {
        this.searchFormula = searchFormula;
    }

    public boolean isSearchClassification() {
        return searchClassification;
    }

    public void setSearchClassification(boolean searchClassification) {
        this.searchClassification = searchClassification;
    }

    public String getSearchSynonymString() {
        return searchSynonymString;
    }

    public void setSearchSynonymString(String searchSynonymString) {
        this.searchSynonymString = searchSynonymString;
    }
}
