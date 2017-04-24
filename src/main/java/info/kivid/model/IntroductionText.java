package info.kivid.model;

/**
 * Created by eveve on 22.04.2017.
 */
public class IntroductionText {
    private int id;
    private String title_et;
    private String title_en;
    private String menu_title_et;
    private String menu_title_en;
    private String content_et;
    private String content_en;

    public IntroductionText(int id, String title_et, String title_en, String content_et, String content_en) {
        this.id = id;
        this.title_et = title_et;
        this.title_en = title_en;
        this.content_et = content_et;
        this.content_en = content_en;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle_et(String title_et) {
        this.title_et = title_et;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public void setMenu_title_et(String menu_title_et) {
        this.menu_title_et = menu_title_et;
    }

    public void setMenu_title_en(String menu_title_en) {
        this.menu_title_en = menu_title_en;
    }

    public void setContent_et(String content_et) {
        this.content_et = content_et;
    }

    public void setContent_en(String content_en) {
        this.content_en = content_en;
    }

    public IntroductionText() {}

    public int getId() {
        return id;
    }

    public String getTitle_et() {
        return title_et;
    }

    public String getTitle_en() {
        return title_en;
    }

    public String getMenu_title_et() {
        return menu_title_et;
    }

    public String getMenu_title_en() {
        return menu_title_en;
    }

    public String getContent_et() {
        return content_et;
    }

    public String getContent_en() {
        return content_en;
    }
}
