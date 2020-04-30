package tw.loopbackquery.newimpl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class Where extends HashMap<String, Object>{

    public static Where.Builder by(String fieldName) {
        final Builder builder = new Builder();
        return builder.by(fieldName);
    }

    Where implicitAndCombine(Where where) {
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

        public Where like(String sqlRegEx) {
            operator = Operator.LIKE;
            this.value = sqlRegEx;
            return build();
        }

        public Where unlike(String sqlRegEx) {
            operator = Operator.NLIKE;
            this.value = sqlRegEx;
            return build();
        }

        public Where match(String regex) {
            operator = Operator.REGEXP;
            this.value = regex;
            return build();
        }

        public Where gt(Number number) {
            operator = Operator.GT;
            this.value = number;
            return build();
        }

        public Where gt(Instant date) {
            operator = Operator.GT;
            this.value = date;
            return build();
        }

        public Where lt(Number number) {
            operator = Operator.LT;
            this.value = number;
            return build();
        }

        public Where lt(Instant date) {
            operator = Operator.LT;
            this.value = date;
            return build();
        }

        public Where gte(Number number) {
            operator = Operator.GTE;
            this.value = number;
            return build();
        }

        public Where gte(Instant date) {
            operator = Operator.GTE;
            this.value = date;
            return build();
        }

        public Where lte(Number number) {
            operator = Operator.LTE;
            this.value = number;
            return build();
        }

        public Where lte(Instant date) {
            operator = Operator.LTE;
            this.value = date;
            return build();
        }

        public <T> Where between(List<T> values) {
            operator = Operator.BETWEEN;
            this.value = values;
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
