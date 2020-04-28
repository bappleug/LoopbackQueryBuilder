package tw.loopbackquery.newimpl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class FieldMapTest {

    private ObjectMapper objectMapper;

    {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    void should_return_simple_json() throws JsonProcessingException {
        FieldMap fieldMap = new FieldMap();
        fieldMap.add("key", 1);
        final String json = objectMapper.writeValueAsString(fieldMap);
        assertThatJson(json).isEqualTo("{root:{key: 1}}");
    }

}
