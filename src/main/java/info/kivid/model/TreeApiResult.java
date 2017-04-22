package info.kivid.model;

public class TreeApiResult {
    private String rock_id;
    private String parent_id;
    private String rock__name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeApiResult that = (TreeApiResult) o;

        if (!rock_id.equals(that.rock_id)) return false;
        if (!parent_id.equals(that.parent_id)) return false;
        return rock__name.equals(that.rock__name);

    }

    @Override
    public int hashCode() {
        int result = rock_id.hashCode();
        result = 31 * result + parent_id.hashCode();
        result = 31 * result + rock__name.hashCode();
        return result;
    }
}
