package com.kushmyruk.ioc;

import java.util.ArrayList;
import java.util.List;

public interface Config {

    List<BeanDefinition> EMPTY_BEAN_DEFINITION = new ArrayList<>();

    List<BeanDefinition> beanDefinitions();
}
