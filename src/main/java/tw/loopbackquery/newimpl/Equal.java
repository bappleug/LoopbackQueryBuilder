package tw.loopbackquery.newimpl;

public class Equal extends Where {

    public static Equal of(String fieldName, Object value) {
        return new Builder().by(fieldName).to(value).build();
    }

    public static class Builder implements IBuilder<Equal> {

        private String fieldName;
        private Object value;

        @Override
        public Equal build() {
            Equal equal = new Equal();
            equal.put(fieldName, value);
            return equal;
        }

        public Builder by(String fieldName) {
            this.fieldName = fieldName;
            return this;
        }

        public Builder to(Object value) {
            this.value = value;
            return this;
        }
    }
}
