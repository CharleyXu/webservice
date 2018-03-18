package com.xu.webservice.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author CharleyXu Created on 2018/3/19.
 */
public class JDKSerializeUtils {

  /**
   * 序列化
   * @param object
   * @return
   */
  public static byte[] serialize(Object object) {
    ObjectOutputStream oos = null;
    ByteArrayOutputStream baos = null;
    byte[] bytes = null;
    try {
      baos = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      bytes = baos.toByteArray();
      return bytes;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 反序列化
   * @param bytes
   * @return
   */
  public static Object deserialize(byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    ByteArrayInputStream bais = null;
    try {
      bais = new ByteArrayInputStream(bytes);
      ObjectInputStream ois = new ObjectInputStream(bais);
      return ois.readObject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
