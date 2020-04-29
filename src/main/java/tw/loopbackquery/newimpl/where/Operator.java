package tw.loopbackquery.newimpl.where;

public enum Operator {
    EQ("eq"),
    LIKE("like"),
    NLIKE("nlike"),
    REGEXP("regexp");

    private String value;

    Operator(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
