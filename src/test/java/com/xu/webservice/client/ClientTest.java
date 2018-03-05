package com.xu.webservice.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

/**
 * @author CharleyXu Created on 2018/3/6.
 */
public class ClientTest {

  @Test
  public void clientTest() throws Exception {
    JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
    Client client = clientFactory.createClient("http://localhost:8080/soap/user?wsdl");
    Object[] objects = client.invoke("getName", "123");
    System.out.println(objects[0].toString());
  }
}
