package com.xu.webservice.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

/**
 * @author CharleyXu Created on 2018/3/6.
 */
public class SoapClientTest {

  @Test
  public void clientTest() throws Exception {
    JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
    Client client = clientFactory.createClient("http://localhost:8081/user?wsdl");
    Object[] objects = client.invoke("getUser", "123");
    System.out.println(objects[0].toString());
  }
}
