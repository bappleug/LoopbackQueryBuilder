package swipes.loopbackquery.newimpl;

import lombok.Data;

@Data
public class Query {
    private Integer offset;

    public static class Builder {
        private LoopbackQuery loopbackQuery;
        private Integer offset;

        public Builder(LoopbackQuery loopbackQuery) {
            this.loopbackQuery = loopbackQuery;
        }

        public String build() {
            final Query query = new Query();

            if(offset != null) {
                query.setOffset(offset);
            }
            loopbackQuery.setQuery(query);
            return loopbackQuery.toString();
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }
    }
}
