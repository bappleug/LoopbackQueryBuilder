package tw.loopbackquery.newimpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.HashMap;

@EqualsAndHashCode(callSuper = false)
@Data
public class Filter extends HashMap<String, Object> {
    @JsonIgnore
    private Loopback loopback;

    public String stringify() {
        return loopback.stringify(this);
    }

    public static class Builder implements IBuilder<Filter> {
        private Loopback loopback;
        private Where[] wheres;

        Builder(Loopback loopback) {
            this.loopback = loopback;
        }

        @Override
        public Filter build() {
            Filter filter = new Filter();
            filter.setLoopback(loopback);

            if (wheres != null) {
                Where combined = Arrays.stream(wheres).reduce(new Where(), Where::implicitAndCombine);
                filter.putAll(combined);
            }

            return filter;
        }

        public Builder wheres(Where... wheres) {
            this.wheres = wheres;
            return this;
        }
    }
}
