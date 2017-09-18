package com.kushmyruk.ioc;

public class SimpleBeanDefinition implements BeanDefinition {
    private String beanName;
    private Class<?> beanType;
    private boolean isPrototype;


    public SimpleBeanDefinition(String beanName, Class<?> beanType, boolean isPrototype) {
        this.beanName = beanName;
        this.beanType = beanType;
        this.isPrototype = isPrototype;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public Class<?> getBeanType() {
        return beanType;
    }

    @Override
    public boolean isPrototype() {
        return isPrototype;
    }

}
