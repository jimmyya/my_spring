package com.chen.annocation;

import com.chen.enums.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by CHEN on 2016/8/12.
 * 这是一个注解类，用来注解servlet
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    RequestMethod method() default RequestMethod.GET;
    String value() default "/";
}
