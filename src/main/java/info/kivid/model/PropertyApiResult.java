package info.kivid.model;

public class PropertyApiResult {

    private String id;
    private String rock_id;
    private String property;
    private String property_en;
    private String value_min;
    private String value_max;
    private String value_txt;
    private String reference;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRock_id() {
        return rock_id;
    }

    public void setRock_id(String rock_id) {
        this.rock_id = rock_id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProperty_en() {
        return property_en;
    }

    public void setProperty_en(String property_en) {
        this.property_en = property_en;
    }

    public String getValue_min() {
        return value_min;
    }

    public void setValue_min(String value_min) {
        this.value_min = value_min;
    }

    public String getValue_max() {
        return value_max;
    }

    public void setValue_max(String value_max) {
        this.value_max = value_max;
    }

    public String getValue_txt() {
        return value_txt;
    }

    public void setValue_txt(String value_txt) {
        this.value_txt = value_txt;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
