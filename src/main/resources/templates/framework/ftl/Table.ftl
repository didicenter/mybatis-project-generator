package org.hddframework.jdbc.annotation;

import org.hddframework.core.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    @AliasFor("value")
    String name() default "";

    @AliasFor("name")
    String value();

}
