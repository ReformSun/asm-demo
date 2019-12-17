package com.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    /**
     * 默认值限制
     * 元素不能没有不确定的值
     * 如果没有默认值 使用此注解时 需加上id=1
     * 对于非基本数据类型，默认值不能为null
     * @return
     */
    public int id() default  1;
}
