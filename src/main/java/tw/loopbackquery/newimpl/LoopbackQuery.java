package tw.loopbackquery.newimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoopbackQuery {
    private ObjectMapper objectMapper;

    private LoopbackQuery(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static Query.Builder query(ObjectMapper objectMapper) {
        LoopbackQuery loopbackQuery = new LoopbackQuery(objectMapper);
        return new Query.Builder(loopbackQuery);
    }

    public String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
