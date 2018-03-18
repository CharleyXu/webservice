package com.xu.webservice.util;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

/**
 * @author CharleyXu Created on 2018/3/18.
 */
public class ProtostuffUtils {
  private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

  private static Objenesis objenesis = new ObjenesisStd(true);

  private ProtostuffUtils() {
  }

  @SuppressWarnings("unchecked")
  private static <T> Schema<T> getSchema(Class<T> cls) {
    Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
    if (schema == null) {
      schema = RuntimeSchema.createFrom(cls);
      if (schema != null) {
        cachedSchema.put(cls, schema);
      }
    }
    return schema;
  }

  @SuppressWarnings("unchecked")
  public static <T> byte[] serialize(T obj) {
    Class<T> cls = (Class<T>) obj.getClass();
    LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    try {
      Schema<T> schema = getSchema(cls);
      return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
    } catch (Exception e) {
      throw new IllegalStateException(e.getMessage(), e);
    } finally {
      buffer.clear();
    }
  }

  public static <T> T deserialize(byte[] data, Class<T> cls) {
    try {
      T message = (T) objenesis.newInstance(cls);
      Schema<T> schema = getSchema(cls);
      ProtostuffIOUtil.mergeFrom(data, message, schema);
      return message;
    } catch (Exception e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
}
