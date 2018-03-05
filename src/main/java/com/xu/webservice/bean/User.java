package com.xu.webservice.bean;

import java.io.Serializable;

/**
 * @author CharleyXu Created on 2018/3/5.
 */
public class User implements Serializable {

  private static final long serialVersionUID = 8830160143422999070L;
  private String userId;
  private String userName;

  public String getUserId() {
    return userId;
  }

  public User setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public User setUserName(String userName) {
    this.userName = userName;
    return this;
  }
}
