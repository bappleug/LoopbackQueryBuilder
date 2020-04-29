package tw.loopbackquery.newimpl.where;

public enum Operator {
    EQ("eq");

    private String value;

    Operator(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
