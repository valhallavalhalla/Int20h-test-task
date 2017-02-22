package task.model.ovvatv;

public enum Language {

    RU("ru"),
    UA("ua");

    Language(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

}
