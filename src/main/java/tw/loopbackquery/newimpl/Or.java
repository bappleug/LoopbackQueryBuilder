package tw.loopbackquery.newimpl;

public class Or extends Equal {
    public static Or of(Equal... equals) {
        return new Or.Builder(equals).build();
    }

    public static class Builder implements IBuilder<Or> {

        private Equal[] equals;

        public Builder(Equal[] equals) {
            this.equals = equals;
        }

        public Or build() {
            Or or = new Or();
            or.put("or", equals);
            return or;
        }
    }
}
