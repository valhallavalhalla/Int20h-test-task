package task.model.ovvatv;

public enum Channel {

    ONE_PLUS_ONE("1plus1");

    private final String name;

    Channel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
