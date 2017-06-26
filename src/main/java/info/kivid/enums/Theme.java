package info.kivid.enums;

public enum Theme {
  minerals("60"), rocks("61"), mineral_resources("62");
  private final String id;

    Theme(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
