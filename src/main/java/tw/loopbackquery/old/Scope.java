package tw.loopbackquery.old;

public class Scope {

    public Include include;
    public String[] fields;
    public String relation;
    public String order;
    public Where where;

    private Scope() { }

    public static class Builder implements IBuild<Scope> {

        private Include.Builder includeBuilder;
        private Where.Builder whereBuilder;
        private String[] fields;
        private String relation;
        private String order;
        private Where where;

        public Builder() { }

        @Override
        public Scope build() {
            Scope scope = new Scope();

            if(includeBuilder != null) {
                scope.include = includeBuilder.build();
            }

            if(whereBuilder != null) {
                scope.where = whereBuilder.build();
            }

            if(fields != null && fields.length > 0) {
                scope.fields = fields;
            }

            if(relation != null && relation.isEmpty()) {
                scope.relation = relation;
            }

            if(order != null && order.isEmpty()) {
                scope.order = order;
            }

            return scope;
        }

        public Builder where(Where.Builder whereBuilder) {
            this.whereBuilder = whereBuilder;
            return this;
        }

        public Builder fields(String...fields) {
            this.fields = fields;
            return this;
        }

        public Builder relation(String relation) {
            this.relation = relation;
            return this;
        }

        public Builder order(String order) {
            this.order = order;
            return this;
        }

        public Builder include(Include.Builder includeBuilder) {
            this.includeBuilder = includeBuilder;
            return this;
        }
    }
}
