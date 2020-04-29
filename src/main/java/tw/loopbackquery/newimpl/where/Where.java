package tw.loopbackquery.newimpl.where;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tw.loopbackquery.newimpl.IBuilder;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = false)
@Data
public class Where extends HashMap<String, Object>{

    public static Where.Builder by(String fieldName) {
        final Builder builder = new Builder();
        return builder.by(fieldName);
    }

    public Where implicitAndCombine(Where where) {
        this.putAll(where);
        return this;
    }

    public static class Builder implements IBuilder<Where> {
        private String by;
        private Operator operator;
        private Object value;

        public Builder by(String fieldName) {
            this.by = fieldName;
            return this;
        }

        public Where eq(Object value) {
            operator = Operator.EQ;
            this.value = value;
            return build();
        }

        public Where like(String value) {
            operator = Operator.LIKE;
            this.value = value;
            return build();
        }

        @Override
        public Where build() {
            final Where where = new Where();
            final HashMap<String, Object> whereClause = new HashMap<>();
            whereClause.put(operator.value(), value);
            where.put(by, whereClause);
            return where;
        }
    }
}
