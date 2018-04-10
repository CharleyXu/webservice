package com.xu.webservice.shiro.config;

import com.xu.webservice.shiro.bean.User;
import com.xu.webservice.shiro.service.UserService;
import com.xu.webservice.shiro.util.JWTUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author CharleyXu Created on 2018/4/10.
 */
public class MyRealm extends AuthenticatingRealm {
  private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);
  @Autowired
  private UserService userService;


  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    String credentials = (String) token.getCredentials();
    // 解密获得username，用于和数据库进行对比
    String username = JWTUtils.getUsername(credentials);
    if (username == null) {
      throw new AuthenticationException("token invalid");
    }

    User user = userService.getUser(username);
    if (user == null) {
      throw new AuthenticationException("User didn't existed!");
    }

    if (! JWTUtils.verify(credentials, username, user.getPassword())) {
      throw new AuthenticationException("Username or password error");
    }

    return new SimpleAuthenticationInfo(token, token, "my_realm");
  }
}
