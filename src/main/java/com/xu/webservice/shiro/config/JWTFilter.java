package com.xu.webservice.shiro.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

/**
 * @author CharleyXu Created on 2018/4/11.
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

  @Override
  protected boolean executeLogin(ServletRequest request, ServletResponse response)
      throws Exception {
    return super.executeLogin(request, response);
  }


  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
      Object mappedValue) {
    HttpServletRequest req = (HttpServletRequest) request;
    String authorization = req.getHeader("Authorization");
    return authorization != null;
  }

  /**
   * 判断用户是否想要登入。
   * 检测header里面是否包含Authorization字段即可
   * @param request
   * @param response
   * @return
   */
  @Override
  protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
    HttpServletRequest req = (HttpServletRequest) request;
    String authorization = req.getHeader("Authorization");
    return authorization != null;
  }

  @Override
  protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
    return super.preHandle(request, response);
  }
}
