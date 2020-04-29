package tw.loopbackquery.newimpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tw.loopbackquery.newimpl.where.Where;

import java.util.Arrays;

@Data
public class Query {
    @JsonIgnore
    private LoopbackQuery loopbackQuery;
    private Integer offset;
    private Integer limit;
    private String order;
    private String include;
    private Where where;

    @Override
    public String toString() {
        return loopbackQuery.toString(this);
    }

    public static class Builder implements IBuilder<Query> {
        private LoopbackQuery loopbackQuery;
        private Integer offset;
        private Integer limit;
        private Order order;
        private String include;
        private Where where;

        public Builder(LoopbackQuery loopbackQuery) {
            this.loopbackQuery = loopbackQuery;
        }

        public Query build() {
            final Query query = new Query();
            query.setLoopbackQuery(loopbackQuery);

            if(offset != null) {
                query.setOffset(offset);
            }
            if(limit != null) {
                query.setLimit(limit);
            }
            if(order != null) {
                query.setOrder(order.toString());
            }
            if(include != null) {
                query.setInclude(include);
            }
            if(where != null) {
                query.setWhere(where);
            }

            return query;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder include(String relation) {
            this.include = relation;
            return this;
        }

        public Builder where(Where... wheres) {
            this.where = Arrays.stream(wheres).reduce(new Where(), Where::implicitAndCombine);
            return this;
        }
    }
}
