package tw.loopbackquery.newimpl;

public enum Direction {
    ASC("asc"),
    DESC("desc");

    private final String value;

    Direction(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
