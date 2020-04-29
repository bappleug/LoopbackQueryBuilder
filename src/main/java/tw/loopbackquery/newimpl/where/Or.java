package tw.loopbackquery.newimpl.where;

import tw.loopbackquery.newimpl.IBuilder;

public class Or extends Where {
    public static Or of(Where... wheres) {
        return new Or.Builder(wheres).build();
    }

    public static class Builder implements IBuilder<Or> {

        private Where[] wheres;

        public Builder(Where... wheres) {
            this.wheres = wheres;
        }

        public Or build() {
            Or or = new Or();
            or.put("or", wheres);
            return or;
        }
    }
}
