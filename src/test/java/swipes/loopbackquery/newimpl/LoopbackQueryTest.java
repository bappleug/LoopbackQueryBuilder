package swipes.loopbackquery.newimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoopbackQueryTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Nested
    class LimitOffsetTest {

        @Test
        void should_return_json_with_limit_when_limit_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .offset(1)
                    .build();
            assertThat(json).isEqualTo("{\"offset\":1}");
        }

    }
}
