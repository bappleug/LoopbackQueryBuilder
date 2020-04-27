package tw.loopbackquery.newimpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class Query {
    @JsonIgnore
    private LoopbackQuery loopbackQuery;
    private Integer offset;
    private Integer limit;
    private String order;

    @Override
    public String toString() {
        return loopbackQuery.toString(this);
    }

    public static class Builder implements IBuilder<Query> {
        private LoopbackQuery loopbackQuery;
        private Integer offset;
        private Integer limit;
        private Order order;

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

        public Builder orderBy(Order order) {
            this.order = order;
            return this;
        }
    }
}
