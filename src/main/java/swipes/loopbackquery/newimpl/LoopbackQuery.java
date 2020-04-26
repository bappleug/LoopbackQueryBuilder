package swipes.loopbackquery.newimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoopbackQuery {
    private ObjectMapper objectMapper;
    private Query query;

    private LoopbackQuery(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public static Query.Builder query(ObjectMapper objectMapper) {
        LoopbackQuery loopbackQuery = new LoopbackQuery(objectMapper);
        return new Query.Builder(loopbackQuery);
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(query);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
