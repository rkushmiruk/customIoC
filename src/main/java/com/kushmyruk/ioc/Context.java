package com.kushmyruk.ioc;

public interface Context {

    Object getBean(String beanName);

    String[] getBeanDefinitionNames();
}
