package com.test.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 使用下面的元注解和注解策略
 * 此注解将在class文件中存在但是将在VM中被丢弃
 */
@Retention(RetentionPolicy.CLASS)
public @interface Test3 {
}
