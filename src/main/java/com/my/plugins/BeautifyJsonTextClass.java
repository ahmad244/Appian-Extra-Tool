package com.my.plugins;

import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@MyCustomFunctionsCategory
public class BeautifyJsonTextClass {

  private static final Logger LOG = Logger
    .getLogger(BeautifyJsonTextClass.class);

  @Function
  public String beautifyJsonText(
    @Parameter String uglyJson) {
    if (uglyJson == null)
      throw new IllegalArgumentException("Input cannot be null");

    ObjectMapper objectMapper = new ObjectMapper();
    String prettyJson = "";
    try {
      Object jsonObject = objectMapper.readValue(uglyJson, Object.class);
      prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

    } catch (JsonProcessingException e) {
      LOG.error("JsonProcessingException: " + e.getMessage());
      return null;
    }
    // catch JsonProcessingException, JsonMappingException
    return prettyJson;
  }

  /// add a main method to test the function
  public static void main(String[] args) {

    String uglyJsonString = "{\"one\":\"AAA\",\"two\":[\"BBB\",\"CCC\"],\"three\":{\"four\":\"DDD\",\"five\":[\"EEE\",\"FFF\"]}}";

    String goodLookingJson = new BeautifyJsonTextClass().beautifyJsonText(uglyJsonString);
    System.out.println(goodLookingJson);
  }

}
