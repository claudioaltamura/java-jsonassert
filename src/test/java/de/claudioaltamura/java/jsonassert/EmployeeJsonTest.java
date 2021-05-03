package de.claudioaltamura.java.jsonassert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class EmployeeJsonTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void lenient() throws JsonProcessingException, JSONException {
    Employee clark = new Employee(1L, "Clark Kent");

    JSONAssert.assertEquals(
        "{id:1,name:\"Clark Kent\"}",
        objectMapper.writeValueAsString(clark),
        JSONCompareMode.LENIENT);
  }

  @Test
  void strictExceptionMissingName() throws JsonProcessingException {
    Employee clark = new Employee(1L);

    String actual = objectMapper.writeValueAsString(clark);

    Throwable thrown =
        catchThrowable(
            () ->
                JSONAssert.assertEquals(
                    "{id:1,name:\"Clark Kent\"}", actual, JSONCompareMode.STRICT));

    assertThat(thrown).isInstanceOf(AssertionError.class);
  }

  @Test
  void failureMessage() throws JsonProcessingException {
    Employee clark = new Employee(1L, "Clark Kent");

    String actual = objectMapper.writeValueAsString(clark);
    String failureMsg = "";

    Throwable thrown =
        catchThrowable(
            () ->
                JSONAssert.assertEquals(
                    "property missing", "{id:1}", actual, JSONCompareMode.STRICT));

    // property missing \n Unexpected: name\n
    assertThat(thrown).hasMessageContaining("Unexpected: name");
  }
}
