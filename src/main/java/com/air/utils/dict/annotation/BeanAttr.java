package com.air.utils.dict.annotation;


/**
 * 
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author air
 *
 *         2016年1月28日 下午3:00:35
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanAttr {
	
	String value() default "";
	
	long sort() default 0L;
	
	long parentId() default 0L;
	
	boolean enable() default true;
	//中文
	String cnLabel() default "";

	String enLabel() default "";

	String remak() default "";

}
