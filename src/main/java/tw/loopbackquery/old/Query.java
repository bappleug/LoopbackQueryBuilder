package tw.loopbackquery.old;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;

public class Query {

    public Filter filter;
    public Include include;
    private LoopbackQuery loopbackQuery;

    private Query(LoopbackQuery loopbackQuery) {
        this.loopbackQuery = loopbackQuery;
    }

    @Override
    public String toString() {
        try {
            return loopbackQuery.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new LoopbackQueryBuildException();
        }
    }

    static class Builder implements IBuild<Query> {
        private Include.Builder include;
        private Filter.Builder filterBuilder;
        private LoopbackQuery loopbackQuery;

        Builder(LoopbackQuery loopbackQuery) {
            this.loopbackQuery = loopbackQuery;
        }

        @Override
        public Query build() {
            Query query = new Query(loopbackQuery);

            if(filterBuilder != null) {
                query.filter = filterBuilder.build();
            }
            if(this.include != null) {
                query.include = include.build();
            }

            return query;
        }

        public Builder filter(Filter.Builder filterBuilder) {
            this.filterBuilder = filterBuilder;
            return this;
        }

        public Builder where(Where.Builder whereBuilder) {
            if(this.filterBuilder == null) {
                filter(new Filter.Builder());
            }
            this.filterBuilder.where(whereBuilder);

            return this;
        }

        public Builder include(Include.Builder include) {
            this.include = include;
            return this;
        }
    }

    public static class Value {
        private HashMap<String, Object> fields;

        private Value() {
            fields = new HashMap<>();
        }

        public Value(String field, String value) {
            this();
            fields.put(field, value);
        }
    }
}
