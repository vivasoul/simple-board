package com.odth.config.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateMembers {
	
	String dsName() default "";
	
	String[] members() default {};
}
