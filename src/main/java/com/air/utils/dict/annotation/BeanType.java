package com.air.utils.dict.annotation;

/**
 * 
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.air.utils.dict.entity.CompanyList;
import com.air.utils.dict.entity.ProjectList;

/**
 * @author air
 *
 *         2016年1月28日 下午3:00:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanType {
	long companyId() default CompanyList.COMPANY1;//唯一
	long projectId() default ProjectList.PROJECT1;//唯一
	long type();//唯一
	String description();//中文
}
