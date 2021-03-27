package com.meiken.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author glf
 * @Date 2021/1/17
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface GetMapping {
    String value();
}
