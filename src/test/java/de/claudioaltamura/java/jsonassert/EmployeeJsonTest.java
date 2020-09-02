package de.claudioaltamura.java.jsonassert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class EmployeeJsonTest {

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void lenient() throws JsonProcessingException, JSONException {
    Employee clark = new Employee(1L, "Clark Kent");

    JSONAssert.assertEquals(
        "{id:1,name:\"Clark Kent\"}",
        objectMapper.writeValueAsString(clark),
        JSONCompareMode.LENIENT);
  }

  @Test
  void strictExceptionMissingName() throws JsonProcessingException, JSONException {
    Employee clark = new Employee(1L);

    String actual = objectMapper.writeValueAsString(clark);
    Assertions.assertThrows(
        AssertionError.class,
        () ->
            JSONAssert.assertEquals("{id:1,name:\"Clark Kent\"}", actual, JSONCompareMode.STRICT));
  }
}
