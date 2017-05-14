package info.kivid.model;

/**
 * Created by eveve on 13.05.2017.
 */
public class Parents implements Comparable<Parents>{
    private int parent_id;
    private String parent_string;
    private String parent__name;
    private String parent__name_en;
    private int rock_classification__hierarchy_top_rock_id;
    private boolean in_estonia;

    public Parents() {
    }

    public Parents(int parent_id, String parent__name, String parent__name_en, int rock_classification__hierarchy_top_rock_id) {
        this.parent_id = parent_id;
        this.parent__name = parent__name;
        this.parent__name_en = parent__name_en;
        this.rock_classification__hierarchy_top_rock_id = rock_classification__hierarchy_top_rock_id;
    }
    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
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
    public int getRock_classification__hierarchy_top_rock_id() {
        return rock_classification__hierarchy_top_rock_id;
    }

    public void setRock_classification__hierarchy_top_rock_id(int rock_classification__hierarchy_top_rock_id) {
        this.rock_classification__hierarchy_top_rock_id = rock_classification__hierarchy_top_rock_id;
    }
    public boolean isIn_estonia() {
        return in_estonia;
    }

    public void setIn_estonia(boolean in_estonia) {
        this.in_estonia = in_estonia;
    }

    @Override
    public int compareTo(Parents parents) {
        return (this.getParent_id() < parents.getParent_id() ? -1 :
                (this.getParent_id() == parents.getParent_id() ? 0 : 1));
    }

    @Override
    public String toString() {
        return "Parents{" +
                "parent_id=" + parent_id +
                ", parent_string='" + parent_string + '\'' +
                ", parent__name='" + parent__name + '\'' +
                ", parent__name_en='" + parent__name_en + '\'' +
                ", rock_classification__hierarchy_top_rock_id=" + rock_classification__hierarchy_top_rock_id +
                ", in_estonia=" + in_estonia +
                '}';
    }
}
