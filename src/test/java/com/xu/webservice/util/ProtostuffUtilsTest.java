package com.xu.webservice.util;

import com.google.common.base.Stopwatch;
import com.xu.webservice.bean.User;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author CharleyXu Created on 2018/3/19.
 */
public class ProtostuffUtilsTest {

  @Test
  public void serializeTest(){
    Stopwatch stopwatch = Stopwatch.createStarted();
    User user = new User().setUserId(123L).setUserName("tom").setAge(20).setCreateDate(new Date());
    System.out.println(user);
    byte[] data = ProtostuffUtils.serialize(user);
    User user1 = ProtostuffUtils.deserialize(data, User.class);
    System.out.println(user1+"\n"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
    //242ms
  }

  @Test
  public void jacksonTest(){
    Stopwatch stopwatch = Stopwatch.createStarted();
    User user = new User().setUserId(123L).setUserName("tom").setAge(20).setCreateDate(new Date());
    System.out.println(user);
    byte[] bytes = SerializationUtils.writeJsonByteValue(user);
    User user1 = SerializationUtils.readJsonValue(bytes, User.class);
    System.out.println(user1+"\n"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
    //754ms
  }

  @Test
  public void jdkTest(){
    Stopwatch stopwatch = Stopwatch.createStarted();
    User user = new User().setUserId(123L).setUserName("tom").setAge(20).setCreateDate(new Date());
    System.out.println(user);
    byte[] bytes = JDKSerializeUtils.serialize(user);
    User user1= (User)JDKSerializeUtils.deserialize(bytes);
    System.out.println(user1+"\n"+stopwatch.elapsed(TimeUnit.MILLISECONDS));
    //39ms
  }

}
