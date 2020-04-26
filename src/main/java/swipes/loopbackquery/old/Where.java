package swipes.loopbackquery.old;

public class Where extends FieldsQuery {

    public String id;
    public Query.Value[] or;

    private Where() { }

    public static class Builder extends FieldsQuery<Builder> implements IBuild<Where> {

        private String id;
        private Query.Value[] or;

        public Builder() { }

        @Override
        public Where build() {
            Where where = new Where();

            if(id != null && !id.isEmpty()) {
                where.id = id;
            }
            if(fields.size() > 0) {
                where.fields = fields;
            }
            if(or != null && or.length > 0) {
                where.or = or;
            }

            return where;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder add(String key, Object val) {
            return add(this, key, val);
        }

        public Builder or(String key, Object val) {
            if(or == null && or.length == 0) {
                or = new Query.Value[1];
            } else {
                throw new IllegalStateException();
            }
            or[0] = new Query.Value(key, val.toString());

            return this;
        }

        public Builder and(Query.Value[] values) {
            return this;
        }

        public Builder or(Query.Value value) {
            return or(new Query.Value[] {value});
        }

        public Builder or(Query.Value[] value) {
            or = value;
            return this;
        }

        public Builder add(String key, Object... vals) {
            return add(this, key, vals);
        }
    }
}
