package swipes.loopbackquery.newimpl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoopbackQueryTest {

    private ObjectMapper objectMapper;

    {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Nested
    class OffsetTest {

        @Test
        void should_return_json_with_offset_when_offset_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .offset(1)
                    .build();
            assertThat(json).isEqualTo("{\"offset\":1}");
        }

        @Test
        void should_return_json_without_offset_when_offset_not_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .build();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class LimitTest {

        @Test
        void should_return_json_with_limit_when_limit_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .limit(1)
                    .build();
            assertThat(json).isEqualTo("{\"limit\":1}");
        }

        @Test
        void should_return_json_without_limit_when_limit_not_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .build();
            assertThat(json).isEqualTo("{}");
        }
    }
}
