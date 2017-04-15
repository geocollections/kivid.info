package info.kivid.model;


public class LocationApiResult {
    private String id;
    private String rock_id;
    private String locality;
    private String locality__locality;
    private String locality__locality_en;
    private String locality__latitude;
    private String locality__longitude;

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

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocality__locality() {
        return locality__locality;
    }

    public void setLocality__locality(String locality__locality) {
        this.locality__locality = locality__locality;
    }

    public String getLocality__locality_en() {
        return locality__locality_en;
    }

    public void setLocality__locality_en(String locality__locality_en) {
        this.locality__locality_en = locality__locality_en;
    }

    public String getLocality__latitude() {
        return locality__latitude;
    }

    public void setLocality__latitude(String locality__latitude) {
        this.locality__latitude = locality__latitude;
    }

    public String getLocality__longitude() {
        return locality__longitude;
    }

    public void setLocality__longitude(String locality__longitude) {
        this.locality__longitude = locality__longitude;
    }
}
