package tw.loopbackquery.newimpl.where;

public enum Operator {
    EQ("eq"),
    LIKE("like"),
    NLIKE("nlike"),
    REGEXP("regexp"),
    GT("gt"),
    LT("lt");

    private String value;

    Operator(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
