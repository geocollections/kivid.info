package info.kivid.model;

public class ImageApiResult {

    private String image_url;
    private String agent__agent;
    private String date;
    private String date_taken_free;
    private String licence__licence;
    private String copyright_agent__agent;
    private String specimen__id;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAgent__agent() {
        return agent__agent;
    }

    public void setAgent__agent(String agent__agent) {
        this.agent__agent = agent__agent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_taken_free() {
        return date_taken_free;
    }

    public void setDate_taken_free(String date_taken_free) {
        this.date_taken_free = date_taken_free;
    }

    public String getLicence__licence() {
        return licence__licence;
    }

    public void setLicence__licence(String licence__licence) {
        this.licence__licence = licence__licence;
    }

    public String getCopyright_agent__agent() {
        return copyright_agent__agent;
    }

    public void setCopyright_agent__agent(String copyright_agent__agent) {
        this.copyright_agent__agent = copyright_agent__agent;
    }

    public String getSpecimen__id() {
        return specimen__id;
    }

    public void setSpecimen__id(String specimen__id) {
        this.specimen__id = specimen__id;
    }
}
