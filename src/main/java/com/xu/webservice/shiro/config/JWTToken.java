package com.xu.webservice.shiro.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author CharleyXu Created on 2018/4/10.
 */
public class JWTToken implements AuthenticationToken {
  // 密钥
  private String token;

  public JWTToken(String token) {
    this.token = token;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }
}
