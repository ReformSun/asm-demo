package com.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


/**
 * Target 元注解 决定了这个注解可以在什么地方使用
 *
 *
            TYPE, 类 接口 枚举


            FIELD, 域生命 包括枚举实例


            METHOD, 方法声明


            PARAMETER, 参数声明


            CONSTRUCTOR, 构造器声明


            LOCAL_VARIABLE, 局部变量声明


            ANNOTATION_TYPE,


            PACKAGE, 包声明


            TYPE_PARAMETER, 参数类型声明 1.8新加入


            TYPE_USE 类型使用声明（1.8新加入)
 */
@Target(ElementType.TYPE_USE)
public @interface TestTarget {
}
