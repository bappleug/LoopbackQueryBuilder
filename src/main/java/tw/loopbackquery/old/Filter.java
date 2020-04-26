package tw.loopbackquery.old;

import java.util.ArrayList;

public class Filter extends FieldsQuery<Filter> {

    public Where where;
    public Include[] include;
    public String order;
    public Integer offset;
    public Integer limit;

    private Filter() {
    }

    @Override
    public void field(String fieldName, Object value) {
        super.field(fieldName, value);
    }

    public static class Builder implements IBuild<Filter> {

        Where.Builder whereBuilder;
        private ArrayList<Include.Builder> includes;
        private String order;
        private int offset;
        private int limit;

        public Builder() {
        }

        @Override
        public Filter build() {
            Filter filter = new Filter();

            if (whereBuilder != null) {
                filter.where = whereBuilder.build();
            }
            if (includes != null && includes.size() > 0) {
                filter.include = new Include[includes.size()];
                for (int i = 0; i < includes.size(); i++) {
                    filter.include[i] = includes.get(i).build();
                }
            }
            if (order != null && !order.isEmpty()) {
                filter.order = order;
            }
            if (offset > 0) {
                filter.offset = offset;
            }
            if (limit > 0) {
                filter.limit = limit;
            }

            return filter;
        }

        public Builder include(Include.Builder includeBuilder) {
            if (includes == null) {
                includes = new ArrayList<>();
            }
            includes.add(includeBuilder);
            return this;
        }

        public Builder where(Where.Builder whereBuilder) {
            if (this.whereBuilder == null) {
                this.whereBuilder = whereBuilder;
            } else {
                mergeSafe(whereBuilder, whereBuilder);
                this.whereBuilder.fields.putAll(whereBuilder.fields);
            }
            return this;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder order(String order) {
            this.order = order;
            return this;
        }
    }

    public static <T> void mergeSafe(Where.Builder a, Where.Builder b) {
        merge(a, b);
    }

    private static <T> void merge(Where.Builder a, Where.Builder b) {
//        Field[] fields = a.getClass().getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            Field current = fields[i];
//
//            if (!Modifier.isPrivate(current.getModifiers())) {
//                if (current.get(a) != null && current.get(b) == null) {
//                    current.set(b, current.get(a));
//                } else if (current.get(a) == null && current.get(b) != null) {
//                    current.set(a, current.get(b));
//                }
//            }
//        }
    }
}
