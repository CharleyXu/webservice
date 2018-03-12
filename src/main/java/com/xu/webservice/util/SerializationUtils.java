package com.xu.webservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * @author CharleyXu Created on 2018/3/12.
 */
public class SerializationUtils {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final ObjectMapper xmlMapper = new XmlMapper();

  private SerializationUtils() {
  }

  public static ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  public static ObjectMapper getXmlMapper() {
    return xmlMapper;
  }

  public static <T> T readJsonValue(String json, Class<T> cls) {
    try {
      return objectMapper.readValue(json, cls);
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> T readXmlValue(String xml,Class<T> cls){
    try {
      return xmlMapper.readValue(xml,cls);
    }catch (Exception e){
      return  null;
    }
  }

  public static <T> T readJsonValue(InputStream is,Class<T> cls){
    try{
      return objectMapper.readValue(is,cls);
    }catch (Exception e){
      return null;
    }
  }

  public static <T> T readXmlValue(InputStream is,Class<T> cls){
    try{
      return xmlMapper.readValue(is,cls);
    }catch (Exception e){
      return null;
    }
  }

  public static <T> T readJsonValue(byte[] bytes, Class<T> cls) {
    try {
      return objectMapper.readValue(bytes, cls);
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> T readXmlValue(byte[] bytes, Class<T> cls) {
    try {
      return xmlMapper.readValue(bytes, cls);
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> T readJsonValue(String json, TypeReference valueTypeRef) {
    try {
      return objectMapper.readValue(json, valueTypeRef);
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> T readXmlValue(String json, TypeReference valueTypeRef) {
    try {
      return xmlMapper.readValue(json, valueTypeRef);
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> T readJsonValue(byte[] bytes, TypeReference valueTypeRef) {
    try {
      return objectMapper.readValue(bytes, valueTypeRef);
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> T readXmlValue(byte[] bytes, TypeReference valueTypeRef) {
    try {
      return xmlMapper.readValue(bytes, valueTypeRef);
    } catch (Exception e) {
      return null;
    }
  }

  public static <T> T readValue(InputStream is,TypeReference valueTypeRef){
    try{
      return objectMapper.readValue(is,valueTypeRef);
    }catch (Exception e){
      return null;
    }
  }

  public static String writeJsonValue(Object entity) {
    try {
      return objectMapper.writeValueAsString(entity);
    } catch (Exception var2) {
      return null;
    }
  }

  public static String writeXmlValue(Object entity) {
    try {
      return xmlMapper.writeValueAsString(entity);
    } catch (Exception var2) {
      return null;
    }
  }

  public static byte[] writeJsonByteValue(Object entity) {
    try {
      return objectMapper.writeValueAsBytes(entity);
    } catch (Exception e) {
      return null;
    }
  }

  public static byte[] writeXmlByteValue(Object entity) {
    try {
      return xmlMapper.writeValueAsBytes(entity);
    } catch (Exception e) {
      return null;
    }
  }


  static {
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    objectMapper.getDeserializationConfig().withoutFeatures(new DeserializationFeature[]{DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES});
    objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    objectMapper.configure(JsonParser.Feature.ALLOW_YAML_COMMENTS, true);
    objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

}
