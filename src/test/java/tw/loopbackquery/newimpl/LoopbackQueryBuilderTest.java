package tw.loopbackquery.newimpl;

import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

class LoopbackQueryBuilderTest {

    @Nested
    class OffsetTest {

        @Test
        void should_return_json_with_offset_when_offset_set() {
            String json = Loopback.query()
                    .offset(1)
                    .build().stringify();
            assertThat(json).isEqualTo("{\"offset\":1}");
        }

        @Test
        void should_return_json_without_offset_when_offset_not_set() {
            String json = Loopback.query()
                    .build().stringify();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class LimitTest {

        @Test
        void should_return_json_with_limit_when_limit_set() {
            String json = Loopback.query()
                    .limit(1)
                    .build().stringify();
            assertThat(json).isEqualTo("{\"limit\":1}");
        }

        @Test
        void should_return_json_without_limit_when_limit_not_set() {
            String json = Loopback.query()
                    .build().stringify();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class OrderTest {

        @Test
        void should_return_json_with_single_order_asc_when_single_order_set() {
            String json = Loopback.query()
                    .order(Order.by("fieldName").asc())
                    .build().stringify();
            assertThat(json).isEqualTo("{\"order\":\"fieldName asc\"}");
        }

        @Test
        void should_return_json_with_single_order_desc_when_single_order_set() {
            String json = Loopback.query()
                    .order(Order.by("fieldName").desc())
                    .build().stringify();
            assertThat(json).isEqualTo("{\"order\":\"fieldName desc\"}");
        }

        @Test
        void should_return_json_without_order_when_order_not_set() {
            String json = Loopback.query()
                    .build().stringify();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class IncludeTest {

        @Test
        void should_return_json_with_single_include_when_single_include_set() {
            String json = Loopback.query()
                    .include("relation_name")
                    .build().stringify();
            assertThat(json).isEqualTo("{\"include\":\"relation_name\"}");
        }

        @Test
        void should_return_json_with_no_include_when_include_not_set() {
            String json = Loopback.query()
                    .build().stringify();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class WhereTest {

        @Test
        void should_return_json_with_where_equal_string_when_where_equal_string_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").eq("strValue"))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where:{fieldName:{eq:\"strValue\"}}}");
        }

        @Test
        void should_return_json_with_where_equal_int_value_when_where_equal_int_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").eq(100))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {eq:100}}}");
        }

        @Test
        void should_return_json_with_where_equal_float_value_when_where_equal_float_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").eq(3.1415926))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {eq:3.1415926}}}");
        }

        @Test
        void should_return_json_with_where_equal_instant_date_value_when_where_equal_instant_date_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").eq(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {eq:\"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_equal_boolean_value_when_where_equal_boolean_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").eq(false))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {eq:false}}}");
        }

        @Test
        void should_return_json_with_multiple_where_equal_value_when_where_multiple_equal_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName1").eq(false), Where.by("fieldName2").eq("value"))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName1: {eq:false}, fieldName2: {eq:\"value\"}}}");
        }

        @Test
        void should_return_json_with_where_and_simple_equal_operation_when_where_and_equal_operation_set() {
            String json = Loopback.query()
                    .where(And.of(Equal.of("fieldName1", "value1"), Equal.of("fieldName2", "value2")))
                    .build().stringify();
            assertThatJson(json).when(Option.IGNORING_ARRAY_ORDER).node("where").node("and").isArray()
                    .isEqualTo("[{fieldName1: \"value1\"}, {fieldName2: \"value2\"}]");
        }

        @Test
        void should_return_json_with_where_or_simple_equal_operation_when_where_or_simple_equal_operation_set() {
            String json = Loopback.query()
                    .where(Or.of(Equal.of("fieldName1", "value1"), Equal.of("fieldName2", "value2")))
                    .build().stringify();
            assertThatJson(json).when(Option.IGNORING_ARRAY_ORDER).node("where").node("or").isArray()
                    .isEqualTo("[{fieldName1: \"value1\"}, {fieldName2: \"value2\"}]");
        }

        @Test
        void should_return_json_with_where_and_full_operation_when_where_and_full_operation_set() {
            String json = Loopback.query()
                    .where(And.of(Where.by("fieldName1").like("value1"), Equal.of("fieldName2", "value2")))
                    .build().stringify();
            assertThatJson(json).when(Option.IGNORING_ARRAY_ORDER).node("where").node("and").isArray()
                    .isEqualTo("[{fieldName1: {like:\"value1\"}}, {fieldName2: \"value2\"}]");
        }

        @Test
        void should_return_json_with_where_nested_and_or_operation_when_where_nested_and_or_operation_set() {
            String json = Loopback.query()
                    .where(Or.of(
                            And.of(Equal.of("fieldName1", "value1"), Equal.of("fieldName2", "value2")),
                            Equal.of("fieldName3", "value3"))
                    )
                    .build().stringify();
            assertThatJson(json).when(Option.IGNORING_ARRAY_ORDER).node("where").node("or").isArray()
                    .contains("{and: [{fieldName1: \"value1\"}, {fieldName2: \"value2\"}]}")
                    .contains("{fieldName3: \"value3\"}");
        }

        @Test
        void should_return_json_with_where_like_operation_when_where_like_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").like("prefix%"))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {like:\"prefix%\"}}}");
        }

        @Test
        void should_return_json_with_where_nlike_operation_when_where_nlike_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").unlike("prefix%"))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {nlike:\"prefix%\"}}}");
        }

        @Test
        void should_return_json_with_where_regexp_operation_when_where_match_regex_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").match("^T{0-9A-Z}{12}"))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {regexp:\"^T{0-9A-Z}{12}\"}}}");
        }

        @Test
        void should_return_json_with_where_gt_number_value_operation_when_where_greater_than_number_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").gt(100.01))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {gt: 100.01}}}");
        }

        @Test
        void should_return_json_with_where_gt_instant_value_operation_when_where_greater_than_instant_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").gt(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {gt: \"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_lt_number_value_operation_when_where_less_than_number_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").lt(100.01))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {lt: 100.01}}}");
        }

        @Test
        void should_return_json_with_where_lt_instant_value_operation_when_where_less_than_instant_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").lt(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {lt: \"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_gte_number_value_operation_when_where_greater_than_or_equal_to_number_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").gte(100.01))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {gte: 100.01}}}");
        }

        @Test
        void should_return_json_with_where_gte_instant_value_operation_when_where_greater_than_or_equal_to_instant_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").gte(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {gte: \"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_lte_number_value_operation_when_where_less_than_or_equal_to_number_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").lte(100.01))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {lte: 100.01}}}");
        }

        @Test
        void should_return_json_with_where_lte_instant_value_operation_when_where_less_than_or_equal_to_instant_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").lte(Instant.parse("2019-12-03T10:15:30Z")))
                    .build().stringify();
            assertThatJson(json).isEqualTo("{where: {fieldName: {lte: \"2019-12-03T10:15:30Z\"}}}");
        }

        @Test
        void should_return_json_with_where_between_operation_when_where_between_operation_set() {
            String json = Loopback.query()
                    .where(Where.by("fieldName").between(List.of(1, 2, 3)))
                    .build().stringify();
            assertThatJson(json).when(Option.IGNORING_ARRAY_ORDER)
                    .node("where").node("fieldName").node("between").isArray()
                    .isEqualTo("[1, 2, 3]");
        }

        @Test
        void should_return_json_with_no_include_when_include_not_set() {
            String json = Loopback.query()
                    .build().stringify();
            assertThat(json).isEqualTo("{}");
        }
    }

    @Nested
    class FilterForOtherOperators {

        @Test
        void should_return_simple_equal_clause_using_equal_builder() {
            String json = Loopback.filter(
                    Equal.of("field", 1)
            ).build().stringify();
            assertThatJson(json).isEqualTo("{field: 1}");
        }

        @Test
        void should_return_single_where_clause_using_where_builder() {
            String json = Loopback.filter(
                    Where.by("field").gt(100)
            ).build().stringify();
            assertThatJson(json).isEqualTo("{field: {gt:100}}");
        }

        @Test
        void should_return_multiple_wheres_clause_using_where_builder() {
            String json = Loopback.filter(
                    Where.by("field1").gt(100),
                    Where.by("field2").lt(2000),
                    Equal.of("field3", "value")
            ).build().stringify();
            assertThatJson(json).isEqualTo("{field1: {gt:100}, field2: {lt:2000}, field3: \"value\"}");
        }
    }

    @Nested
    class NestedCases {
        @Test
        void should_return_nested_json_with_pagination_include_and_where() {
            final String json = Loopback.query()
                    .offset(0)
                    .limit(10)
                    .order(Order.by("createdOn").desc())
                    .include("articles")
                    .where(And.of(
                            Equal.of("channelId", "123-456-789"),
                            Equal.of("type", "image")
                    ))
                    .build().stringify();
            assertThatJson(json).isEqualTo(
                    "{" +
                            "include: \"articles\"," +
                            "order: \"createdOn desc\","+
                            "where: {and: [" +
                            "   {channelId: \"123-456-789\"}," +
                            "   {type: \"image\"}" +
                            "]}," +
                            "limit: 10," +
                            "offset: 0" +
                            "}"
            );
        }
    }
}
