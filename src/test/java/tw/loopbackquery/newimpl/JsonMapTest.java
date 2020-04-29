package tw.loopbackquery.newimpl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class JsonMapTest {

    private ObjectMapper objectMapper;

    {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    void should_return_json_with_one_field() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("key", 1);
        final String json = objectMapper.writeValueAsString(map);
        assertThatJson(json).isEqualTo("{key: 1}");
    }

    @Test
    void should_return_json_with_two_fields() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        final String json = objectMapper.writeValueAsString(map);
        assertThatJson(json).isEqualTo("{key1: 1, key2: 2}");
    }

    @Test
    void should_return_json_with_two_fields_ignore_order() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        final String json = objectMapper.writeValueAsString(map);
        assertThatJson(json).isEqualTo("{key2: 2, key1: 1}");
    }

    @Test
    void should_return_json_with_nested_json_object_fields() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> nestedMap = new HashMap<>();
        map.put("key", nestedMap);
        nestedMap.put("nested", "value");
        final String json = objectMapper.writeValueAsString(map);
        assertThatJson(json).isEqualTo("{key: {nested: \"value\"}}");
    }

    @Test
    void should_return_json_object_with_primitive_array() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("key", List.of(1, 2, 3));
        final String json = objectMapper.writeValueAsString(map);
        assertThatJson(json).isEqualTo("{key: [1, 2, 3]}");
    }

    @Test
    void should_return_json_object_with_json_array_from_list_of_object() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("key", List.of(Map.of("nested1", 1), Map.of("nested2", 2), Map.of("nested3", 3)));
        final String json = objectMapper.writeValueAsString(map);
        assertThatJson(json).isEqualTo("{key: [{nested1:1}, {nested2:2}, {nested3:3}]}");
    }

    @Test
    void should_return_json_object_with_json_array_from_array_of_object() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("key", new Map[]{Map.of("nested1", 1), Map.of("nested2", 2), Map.of("nested3", 3)});
        final String json = objectMapper.writeValueAsString(map);
        assertThatJson(json).isEqualTo("{key: [{nested1:1}, {nested2:2}, {nested3:3}]}");
    }


}
