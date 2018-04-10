package com.xu.webservice.shiro.bean;

/**
 * @author CharleyXu Created on 2018/4/10.
 */
public class RestfulResponse {
  private Object data;
  private String code;
  private String msg;

  public RestfulResponse() {
  }

  public RestfulResponse(Object data, String code, String msg) {
    this.data = data;
    this.code = code;
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public String toString() {
    return "RestfulResponse{" +
        "data=" + data +
        ", code='" + code + '\'' +
        ", msg='" + msg + '\'' +
        '}';
  }
}
