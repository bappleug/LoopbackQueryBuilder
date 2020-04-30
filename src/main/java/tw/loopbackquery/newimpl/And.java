package tw.loopbackquery.newimpl;

public class And extends Equal {
    public static And of(Equal... equals) {
        return new And.Builder(equals).build();
    }

    public static class Builder implements IBuilder<And> {

        private Equal[] equals;

        public Builder(Equal... equals) {
            this.equals = equals;
        }

        public And build() {
            And and = new And();
            and.put("and", equals);
            return and;
        }
    }
}
