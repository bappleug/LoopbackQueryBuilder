package swipes.loopbackquery.newimpl;

import lombok.Data;

@Data
public class Query {
    private Integer offset;
    private Integer limit;

    public static class Builder {
        private LoopbackQuery loopbackQuery;
        private Integer offset;
        private Integer limit;

        public Builder(LoopbackQuery loopbackQuery) {
            this.loopbackQuery = loopbackQuery;
        }

        public String build() {
            final Query query = new Query();

            if(offset != null) {
                query.setOffset(offset);
            }
            if(limit != null) {
                query.setLimit(limit);
            }

            loopbackQuery.setQuery(query);
            return loopbackQuery.toString();
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }
    }
}
