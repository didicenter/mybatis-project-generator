package org.hddframework.jdbc.annotation;

import org.hddframework.core.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ID {

    @AliasFor("value")
    String name() default "";

    @AliasFor("name")
    String value();

}
