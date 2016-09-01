package com.air.utils.dict.entity;


import com.air.utils.dict.annotation.BeanAttr;
import com.air.utils.dict.annotation.BeanType;

@BeanType(type = 1,description="项目列表")
public class ProjectList extends BaseDict{

	private static final long serialVersionUID = 1L;

	@BeanAttr(cnLabel="默认项目",enLabel="ProjectName")
	 public static final long PROJECT1=1L;
	 
	 @BeanAttr(cnLabel="默认项目1",enLabel="ProjectName1",sort=1,parentId=PROJECT1)
	 public static final long PROJECT2=2L;
	 
	 @BeanAttr(cnLabel="默认项目2",enLabel="ProjectName2",sort=2,enable=false)
	 public static final long PROJECT3=3L;
	 
	 @BeanAttr(cnLabel="默认项目3",enLabel="ProjectName3",sort=3,enable=false)
	 public static final long PROJECT4=4L;
	 
	 
	 @BeanAttr(cnLabel="默认项目4",enLabel="ProjectName4",sort=4,enable=false)
	 public static final long PROJECT5=5L;
	 
	 @BeanAttr(cnLabel="默认项目5",enLabel="ProjectName5",sort=5,enable=false)
	 public static final long PROJECT6=6L;
	 
	 
	 @BeanAttr(cnLabel="默认项目6",enLabel="ProjectName6",sort=6,enable=false)
	 public static final long PROJECT7=7L;
	 
	 @BeanAttr(cnLabel="默认项目7",enLabel="ProjectName7",sort=7,enable=false)
	 public static final long PROJECT8=8L;
	 
	 @BeanAttr(cnLabel="默认项目8",enLabel="ProjectName8",sort=8,enable=false)
	 public static final long PROJECT9=9L;
	 
	 @BeanAttr(cnLabel="默认项目9",enLabel="ProjectName9",sort=9,enable=false)
	 public static final long PROJECT10=10L;
	 
}
