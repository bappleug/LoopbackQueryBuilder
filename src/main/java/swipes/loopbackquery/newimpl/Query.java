package swipes.loopbackquery.newimpl;

import lombok.Data;

@Data
public class Query {
    private Integer offset;

    public static class Builder {
        private LoopbackQuery loopbackQuery;
        private int offset;

        public Builder(LoopbackQuery loopbackQuery) {
            this.loopbackQuery = loopbackQuery;
        }

        public String build() {
            final Query query = new Query();
            query.setOffset(offset);
            loopbackQuery.setQuery(query);
            return loopbackQuery.toString();
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }
    }
}
