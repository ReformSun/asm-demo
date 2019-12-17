package com.test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 使用下面的元注解和注解策略
 * 此注解将被编译器丢弃
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Test2 {
}
