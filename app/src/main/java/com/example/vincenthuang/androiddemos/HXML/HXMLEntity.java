package com.example.vincenthuang.androiddemos.HXML;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用HXML的所有实体必须使用此注解
 * Created by Vincent Huang on 2017/2/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HXMLEntity {
}
