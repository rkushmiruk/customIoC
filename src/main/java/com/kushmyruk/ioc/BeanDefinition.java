package com.kushmyruk.ioc;

public interface BeanDefinition {

    String getBeanName();

    Class<?> getBeanType();

    boolean isPrototype();
}
