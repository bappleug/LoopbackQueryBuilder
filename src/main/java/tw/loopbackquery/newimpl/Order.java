package tw.loopbackquery.newimpl;

import lombok.Data;

@Data
public class Order {
    private String byDirection;

    @Override
    public String toString() {
        return byDirection;
    }

    public static Order.Builder by(String fieldName) {
        final Builder builder = new Builder();
        return builder.by(fieldName);
    }

    public static class Builder implements IBuilder<Order> {
        private String by;
        private Direction direction;

        public Builder by(String fieldName) {
            this.by = fieldName;
            return this;
        }

        public Order asc() {
            this.direction = Direction.ASC;
            return build();
        }

        public Order desc() {
            this.direction = Direction.DESC;
            return build();
        }

        @Override
        public Order build() {
            Order order = new Order();
            order.setByDirection(by + " " + direction.value());
            return order;
        }
    }

    public enum Direction {
        ASC("asc"),
        DESC("desc");

        private final String value;

        Direction(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }
}
