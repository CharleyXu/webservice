package com.xu.webservice.shiro.service;

import com.xu.webservice.shiro.persist.DataSource;
import com.xu.webservice.shiro.bean.User;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author CharleyXu Created on 2018/4/10.
 */
@Component
public class UserService {
  public User getUser(String username) {
    // 没有此用户直接返回null
    if (! DataSource.getData().containsKey(username))
      return null;

    User user = new User();
    Map<String, String> detail = DataSource.getData().get(username);

    user.setUsername(username);
    user.setPassword(detail.get("password"));
    user.setRole(detail.get("role"));
    user.setPermission(detail.get("permission"));
    return user;
  }
}
