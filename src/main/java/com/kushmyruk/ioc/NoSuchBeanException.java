package com.kushmyruk.ioc;

public class NoSuchBeanException extends RuntimeException {
    public NoSuchBeanException() {
        super("NoSuchBean");
    }
}
