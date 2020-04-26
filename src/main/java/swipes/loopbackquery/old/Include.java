package swipes.loopbackquery.old;

public class Include {

    public Scope scope;
    public String relation;
    public String order;
    public Integer limit;

    private Include() { }

    public static class Builder implements IBuild<Include> {

        private Scope.Builder scopeBuilder;
        private String relation;
        private String order;
        private Integer limit;

        public Builder() { }

        @Override
        public Include build() {
            Include include = new Include();

            if(scopeBuilder != null) {
                include.scope = scopeBuilder.build();
            }
            if(order != null && !order.isEmpty()) {
                include.order = order;
            }
            if(relation != null && !relation.isEmpty()) {
                include.relation = relation;
            }
            if(limit != null) {
                include.limit = limit;
            }

            return include;
        }

        public Builder scope(Scope.Builder scopeBuilder) {
            this.scopeBuilder = scopeBuilder;
            return this;
        }

        public Builder order(String order) {
            this.order = order;
            return this;
        }

        public Builder relation(String relation) {
            this.relation = relation;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }
    }
}
