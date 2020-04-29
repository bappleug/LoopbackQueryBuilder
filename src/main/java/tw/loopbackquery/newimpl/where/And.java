package tw.loopbackquery.newimpl.where;

import tw.loopbackquery.newimpl.IBuilder;

import java.util.Arrays;

public class And extends Where {
    public static And of(Where... wheres) {
        return new And.Builder(wheres).build();
    }

    public static class Builder implements IBuilder<And> {

        private Where[] wheres;

        public Builder(Where... wheres) {
            this.wheres = wheres;
        }

        public And build() {
            And and = new And();
            and.put("and", wheres);
            return and;
        }
    }
}
