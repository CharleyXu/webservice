package com.xu.webservice.service;

import com.xu.webservice.bean.User;
import javax.jws.WebService;

/**
 * @author CharleyXu Created on 2018/3/6.
 * 接口实现
 *
 * 与接口中指定的name、命名空间一致、服务接口全路径-接口地址
 */
@WebService(serviceName = "UserService",targetNamespace = "http://service.webservice.xu.com/",endpointInterface = "com.xu.webservice.service.UserService")
public class UserServiceImpl implements UserService {

  @Override
  public String getName(String userId) {
    return "the_name_"+userId;
  }

  @Override
  public User getUser(String userId) {
    User user = new User();
    user.setUserId(userId).setUserName("the_name_"+userId);
    return user;
  }
}
