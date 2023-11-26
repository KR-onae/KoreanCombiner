package com.kronae.korean.combiner.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.LOCAL_VARIABLE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface StringLimit {
    int length();
}
