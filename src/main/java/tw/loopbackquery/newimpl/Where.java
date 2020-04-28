package tw.loopbackquery.newimpl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = false)
@Data
public class Where extends HashMap<String, Object>{

    public static Where.Builder by(String fieldName) {
        final Builder builder = new Builder();
        return builder.by(fieldName);
    }

    public static class Builder implements IBuilder<Where> {
        private String by;
        private Operator operator;
        private Object value;

        public Builder by(String fieldName) {
            this.by = fieldName;
            return this;
        }

        public Where eq(String value) {
            operator = Operator.EQ;
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
}
