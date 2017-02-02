package com.example.vincenthuang.androiddemos.HXML;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * xml消息中的Body部分
 * Created by Vincent Huang on 2017/2/2.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Body {
}
