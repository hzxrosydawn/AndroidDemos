package com.example.vincenthuang.androiddemos.Utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Vincent Huang on 2017/2/2.
 */

/**
 * 提供了一种入口的注解,
 * @author Vincent Huang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Entry {
    /**
     * 返回此类的描述
     */
    String desc();
    String createTime();
}
