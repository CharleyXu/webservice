package com.xu.webservice.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

/**
 * @author charlie Created on 2018/3/6.
 */
public class PersonTest {
	private Person person = new Person();

	@Before
	public void beforeTest(){
		person.setName("bob").setAge(20).setPhone("114").setFriends(new ArrayList<String>(Arrays.asList(new String[]  {"tom","jack","james","sam"})));
	}

	@Test
	public void beanToJsonTest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		// 	按字母顺序排序属性	objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true);
		String string = objectMapper.writeValueAsString(person);
		System.out.println(string);
	}

	@Test
	public void jsonToBeanTest() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"name\":\"bob\",\"age\":20,\"phone\":\"114\",\"friends\":[\"tom\",\"jack\",\"james\",\"sam\"]}";
		Person person = objectMapper.readValue(json, Person.class);
		System.out.println(person);
	}

	@Test
	public void beanToXmlTest() throws JsonProcessingException {
		XmlMapper mapper = new XmlMapper();
		String string = mapper.writeValueAsString(person);
		System.out.println(string);
	}

	@Test
	public void xmlToBeanTest() throws IOException {
		ObjectMapper mapper = new XmlMapper();
		//		mapper.
		String xml = "<person><name>bob</name><age>20</age><phone>114</phone><friends><friend>tom</friend><friend>jack</friend><friend>james</friend><friend>sam</friend></friends></person>";
		Person person = mapper.readValue(xml, Person.class);
		System.out.println(person);
	}

	@Test
	public void mapToJsonTest() throws JsonProcessingException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name","dagdaa");
		map.put("age",19);
		map.put("phone","122");
		map.put("friends",Arrays.asList(new String[] {"11","22"}));
		ObjectMapper objectMapper = new ObjectMapper();
		//序列化Map时对key进行排序操作，默认false
		objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
		String string = objectMapper.writeValueAsString(map);
		System.out.println(string);
	}

	@Test
	public void jsonToMapTest() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"name\":\"bob\",\"age\":20,\"phone\":\"114\",\"friends\":[\"tom\",\"jack\",\"james\",\"sam\"]}";
		HashMap map = objectMapper.readValue(json, HashMap.class);
		map.forEach((k,v) -> System.out.println(k +" "+ v));
	}

}
