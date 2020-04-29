package tw.loopbackquery.newimpl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tw.loopbackquery.newimpl.where.And;
import tw.loopbackquery.newimpl.where.Or;
import tw.loopbackquery.newimpl.where.Where;

import java.time.Instant;
import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

public class LoopbackQueryTest {

    private ObjectMapper objectMapper;

    {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.registerModule(customizeJavaTimeModule());
        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.registerModule(customizeLocaleModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Nested
    class OffsetTest {

        @Test
        void should_return_json_with_offset_when_offset_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .offset(1)
                    .build().toString();
            assertThat(json).isEqualTo("{\"offset\":1}");
        }

        @Test
        void should_return_json_without_offset_when_offset_not_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .build().toString();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class LimitTest {

        @Test
        void should_return_json_with_limit_when_limit_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .limit(1)
                    .build().toString();
            assertThat(json).isEqualTo("{\"limit\":1}");
        }

        @Test
        void should_return_json_without_limit_when_limit_not_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .build().toString();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class OrderTest {

        @Test
        void should_return_json_with_single_order_asc_when_single_order_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .order(Order.by("fieldName").asc())
                    .build().toString();
            assertThat(json).isEqualTo("{\"order\":\"fieldName asc\"}");
        }

        @Test
        void should_return_json_with_single_order_desc_when_single_order_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .order(Order.by("fieldName").desc())
                    .build().toString();
            assertThat(json).isEqualTo("{\"order\":\"fieldName desc\"}");
        }

        @Test
        void should_return_json_without_order_when_order_not_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .build().toString();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class IncludeTest {

        @Test
        void should_return_json_with_single_include_when_single_include_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .include("relation_name")
                    .build().toString();
            assertThat(json).isEqualTo("{\"include\":\"relation_name\"}");
        }

        @Test
        void should_return_json_with_no_include_when_include_not_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .build().toString();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class WhereTest {

        @Test
        void should_return_json_with_where_equal_string_when_where_equal_string_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").eq("strValue"))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where:{fieldName:{eq:\"strValue\"}}}");
        }

        @Test
        void should_return_json_with_where_equal_int_value_when_where_equal_int_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").eq(100))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {eq:100}}}");
        }

        @Test
        void should_return_json_with_where_equal_float_value_when_where_equal_float_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").eq(3.1415926))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {eq:3.1415926}}}");
        }

        @Test
        void should_return_json_with_where_equal_instant_date_value_when_where_equal_instant_date_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").eq(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {eq:\"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_equal_boolean_value_when_where_equal_boolean_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").eq(false))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {eq:false}}}");
        }

        @Test
        void should_return_json_with_multiple_where_equal_value_when_where_multiple_equal_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName1").eq(false), Where.by("fieldName2").eq("value"))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName1: {eq:false}, fieldName2: {eq:\"value\"}}}");
        }

        @Test
        void should_return_json_with_where_and_operation_when_where_nested_and_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(And.of(Where.by("fieldName1").eq("value1"), Where.by("fieldName2").eq("value2")))
                    .build().toString();
            assertThatJson(json).when(Option.IGNORING_ARRAY_ORDER).node("where").node("and").isArray()
                    .isEqualTo("[{fieldName1: {eq:\"value1\"}}, {fieldName2: {eq:\"value2\"}}]");
        }

        @Test
        void should_return_json_with_where_or_operation_when_where_nested_or_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Or.of(Where.by("fieldName1").eq("value1"), Where.by("fieldName2").eq("value2")))
                    .build().toString();
            assertThatJson(json).when(Option.IGNORING_ARRAY_ORDER).node("where").node("or").isArray()
                    .isEqualTo("[{fieldName1: {eq:\"value1\"}}, {fieldName2: {eq:\"value2\"}}]");
        }

        @Test
        void should_return_json_with_where_like_operation_when_where_like_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").like("prefix%"))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {like:\"prefix%\"}}}");
        }

        @Test
        void should_return_json_with_where_nlike_operation_when_where_nlike_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").unlike("prefix%"))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {nlike:\"prefix%\"}}}");
        }

        @Test
        void should_return_json_with_where_regexp_operation_when_where_match_regex_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").match("^T{0-9A-Z}{12}"))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {regexp:\"^T{0-9A-Z}{12}\"}}}");
        }

        @Test
        void should_return_json_with_where_gt_number_value_operation_when_where_greater_than_number_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").gt(100.01))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {gt: 100.01}}}");
        }

        @Test
        void should_return_json_with_where_gt_instant_value_operation_when_where_greater_than_instant_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").gt(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {gt: \"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_lt_number_value_operation_when_where_less_than_number_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").lt(100.01))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {lt: 100.01}}}");
        }

        @Test
        void should_return_json_with_where_lt_instant_value_operation_when_where_less_than_instant_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").lt(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {lt: \"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_gte_number_value_operation_when_where_greater_than_or_equal_to_number_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").gte(100.01))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {gte: 100.01}}}");
        }

        @Test
        void should_return_json_with_where_gte_instant_value_operation_when_where_greater_than_or_equal_to_instant_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").gte(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {gte: \"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_lte_number_value_operation_when_where_less_than_or_equal_to_number_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").lte(100.01))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {lte: 100.01}}}");
        }

        @Test
        void should_return_json_with_where_lte_instant_value_operation_when_where_less_than_or_equal_to_instant_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").lte(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().toString();
            assertThatJson(json).isEqualTo("{where: {fieldName: {lte: \"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_between_operation_when_where_between_operation_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .where(Where.by("fieldName").between(List.of(1, 2, 3)))
                    .build().toString();
            assertThatJson(json).when(Option.IGNORING_ARRAY_ORDER)
                    .node("where").node("fieldName").node("between").isArray()
                    .isEqualTo("[1, 2, 3]");
        }

        @Test
        void should_return_json_with_no_include_when_include_not_set() {
            String json = LoopbackQuery.query(objectMapper)
                    .build().toString();
            assertThat(json).isEqualTo("{}");
        }
    }
}
