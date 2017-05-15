package info.kivid.model;

public class TreeApiResult {
    private String rock_id;
    private String parent_id;
    private String rock__name;
    private String rock__name_en;
    private String parent__name;
    private String parent__name_en;
    private String rock_classification_id;

    public String getRock_id() {
        return rock_id;
    }

    public void setRock_id(String rock_id) {
        this.rock_id = rock_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getRock__name() {
        return rock__name;
    }

    public void setRock__name(String rock__name) {
        this.rock__name = rock__name;
    }

    public String getRock__name_en() {
        return rock__name_en;
    }

    public void setRock__name_en(String rock__name_en) {
        this.rock__name_en = rock__name_en;
    }

    public String getParent__name() {
        return parent__name;
    }

    public void setParent__name(String parent__name) {
        this.parent__name = parent__name;
    }

    public String getParent__name_en() {
        return parent__name_en;
    }

    public void setParent__name_en(String parent__name_en) {
        this.parent__name_en = parent__name_en;
    }

    public String getRock_classification_id() {
        return rock_classification_id;
    }

    public void setRock_classification_id(String rock_classification_id) {
        this.rock_classification_id = rock_classification_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeApiResult that = (TreeApiResult) o;

        if (!rock_id.equals(that.rock_id)) return false;
        if (!parent_id.equals(that.parent_id)) return false;
        if (!rock__name.equals(that.rock__name)) return false;
        if (rock__name_en != null ? !rock__name_en.equals(that.rock__name_en) : that.rock__name_en != null)
            return false;
        if (parent__name != null ? !parent__name.equals(that.parent__name) : that.parent__name != null) return false;
        if (parent__name_en != null ? !parent__name_en.equals(that.parent__name_en) : that.parent__name_en != null)
            return false;
        return rock_classification_id != null ? rock_classification_id.equals(that.rock_classification_id) : that.rock_classification_id == null;

    }

    @Override
    public int hashCode() {
        int result = rock_id.hashCode();
        result = 31 * result + parent_id.hashCode();
        result = 31 * result + rock__name.hashCode();
        result = 31 * result + (rock__name_en != null ? rock__name_en.hashCode() : 0);
        result = 31 * result + (parent__name != null ? parent__name.hashCode() : 0);
        result = 31 * result + (parent__name_en != null ? parent__name_en.hashCode() : 0);
        result = 31 * result + (rock_classification_id != null ? rock_classification_id.hashCode() : 0);
        return result;
    }
}
