package com.kushmyruk.ioc;

import com.kushmyruk.service.impl.PrototypeTweetServiceProxy;
import com.kushmyruk.service.TweetService;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Stream;

public class ApplicationContext implements Context {
    private List<BeanDefinition> beanDefinitions;
    private Map<String, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beanDefinitions = Config.EMPTY_BEAN_DEFINITION;
    }

    public ApplicationContext(Config config) {
        beanDefinitions = config.beanDefinitions();
        initContext(beanDefinitions);
    }

    private void initContext(List<BeanDefinition> beanDefinitions) {
        beanDefinitions.forEach(bd -> getBean(bd.getBeanName()));
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = getBeanDefinitionByName(beanName);

        return Optional.ofNullable(beans.get(beanName))
                .orElseGet(() -> createBeanWithDefinition(beanName, beanDefinition));
    }

    private Object createBeanWithDefinition(String beanName, BeanDefinition beanDefinition) {
        Object bean = createNewBean(beanDefinition);

        if (!beanDefinition.isPrototype()) {
            beans.put(beanName, bean);
        }
        return bean;
    }

    private Object createNewBean(BeanDefinition bd) {
        BeanBuilder beanBuilder = new BeanBuilder(bd);
        beanBuilder.createNewBeanInstance();
        beanBuilder.callPostConstructAnnotatedMethod();
        beanBuilder.callInitMethod();
        beanBuilder.benchmarkProxy();
        if (Objects.equals(bd.getBeanName(), "tweetService")) {
            beanBuilder.createTweetServiceProxy();
        }
        return beanBuilder.build();
    }

    private BeanDefinition getBeanDefinitionByName(String beanName) {
        return beanDefinitions
                .stream()
                .filter(bd -> bd.getBeanName().equals(beanName))
                .findAny()
                .orElseThrow(NoSuchBeanException::new);
    }

    class BeanBuilder {
        private BeanDefinition beanDefinition;
        private Object bean;

        public BeanBuilder(BeanDefinition beanDefinition) {
            this.beanDefinition = beanDefinition;
        }

        private void createNewBeanInstance() {
            Class<?> type = beanDefinition.getBeanType();
            Constructor<?> constructor = type.getConstructors()[0];
            Object newBean;
            if (constructor.getParameterCount() == 0) {
                newBean = createBeanWithDefaultConstructor(type);
            } else {
                newBean = createBeanWithConstructorWithParams(type);
            }

            bean = newBean;
        }

        private void callPostConstructAnnotatedMethod() {
            Stream.of(bean.getClass().getMethods())
                    .filter(e -> e.isAnnotationPresent(MyPostConstruct.class))
                    .forEach(e -> {
                        try {
                            e.invoke(bean);
                        } catch (IllegalAccessException | InvocationTargetException e1) {
                            throw new RuntimeException();
                        }
                    });
        }

        private void callInitMethod() {
            Class<?> beanType = bean.getClass();
            try {
                Method initMethod = beanType.getMethod("init");
                initMethod.invoke(bean);
            } catch (NoSuchMethodException e) {
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }
        }

        public void benchmarkProxy() {
            Class<?> beanType = bean.getClass();
            if (Arrays
                    .stream(beanType.getMethods())
                    .anyMatch(e -> e.isAnnotationPresent(BenchMark.class) && e.getAnnotation(BenchMark.class).enable())) {
                bean = Proxy.newProxyInstance(beanType.getClassLoader(),
                        beanType.getInterfaces(),
                        new Measuring(bean)
                );
            }
        }

        private Object createBeanWithConstructorWithParams(Class<?> type) {
            Constructor<?> constructor = type.getConstructors()[0];
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object newBean;
            try {
                newBean = constructor.newInstance(
                        Stream.of(parameterTypes)
                                .map(e -> Character.toLowerCase(e.getSimpleName().charAt(0)) + e.getSimpleName().substring(1))
                                .map(ApplicationContext.this::getBean)
                                .toArray());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }
            return newBean;
        }

        private Object createBeanWithDefaultConstructor(Class<?> type) {
            Object newBean;
            try {
                newBean = type.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException();
            }
            return newBean;
        }

        public Object build() {
            return bean;
        }

        public void createTweetServiceProxy() {
            bean = new PrototypeTweetServiceProxy((TweetService) bean, ApplicationContext.this).createProxy();
        }
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitions.stream()
                .map(BeanDefinition::getBeanName)
                .toArray(String[]::new);
    }
}
