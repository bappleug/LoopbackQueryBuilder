package tw.loopbackquery.newimpl;

public class Or extends Where {
    public static Or of(Where... wheres) {
        return new Or.Builder(wheres).build();
    }

    public static class Builder implements IBuilder<Or> {

        private Where[] fieldObjects;

        public Builder(Where[] wheres) {
            this.fieldObjects = wheres;
        }

        public Or build() {
            Or or = new Or();
            or.put("or", fieldObjects);
            return or;
        }
    }
}
