package tw.loopbackquery.old;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoopbackQuery {

    private ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private LoopbackQuery(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static Query.Builder builder(ObjectMapper objectMapper) {
        return new Query.Builder(new LoopbackQuery(objectMapper));
    }
}
