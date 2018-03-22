package com.xu.webservice.util;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author charlie Created on 2018/3/22.
 *
 * Spring 容器中获取 Bean
 */
@Component
public class SpringContextUtils implements BeanFactoryPostProcessor {

	private static ConfigurableListableBeanFactory beanFactory;

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		SpringContextUtils.beanFactory = configurableListableBeanFactory;
	}

	public static <T> T getBean(String name) {
		return (T) beanFactory.getBean(name);
	}

	public static <T> T getBean(Class<T> clz) {
		return beanFactory.getBean(clz);
	}

	public static <T> List<T> getBeanOfType(Class<T> type) {
		return beanFactory.getBeansOfType(type).entrySet().stream()
				.map(entry -> entry.getValue()).collect(Collectors.toList());
	}

	public static List<Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
		Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(annotationType);
	/*
		JDK7
		List<Object> result = new ArrayList<Object>();
		for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	*/
		return beansWithAnnotation.entrySet().stream().map(entry -> entry.getValue())
				.collect(Collectors.toList());
	}


}
