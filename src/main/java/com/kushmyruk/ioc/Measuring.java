package com.kushmyruk.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Measuring implements InvocationHandler {
    Object bean;

    public Measuring(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method oldMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
        if(oldMethod != null && oldMethod.isAnnotationPresent(BenchMark.class) && oldMethod.getAnnotation(BenchMark.class).enable()){
            long start = System.nanoTime();
            Object ob = method.invoke(bean, args);
            long end = System.nanoTime();
            System.out.println(end - start);
            return ob;
        } else {
            return method.invoke(bean, args);
        }
    }
}
