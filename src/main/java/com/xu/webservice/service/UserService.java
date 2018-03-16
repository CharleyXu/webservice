package com.xu.webservice.service;

import com.xu.webservice.bean.User;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author CharleyXu Created on 2018/3/5.
 * 接口
 *
 * 暴露服务名称   命名空间,一般是接口的包名倒序
 */
@WebService(name = "UserService" , targetNamespace = "http://service.webservice.xu.com/")
public interface UserService {
  @WebMethod
  @WebResult(name = "String",targetNamespace = "")
  public String getName(@WebParam(name = "userId") Long userId);

  @WebMethod
  public User getUser(Long userId);

}
