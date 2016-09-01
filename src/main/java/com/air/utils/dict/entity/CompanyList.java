package com.air.utils.dict.entity;


import com.air.utils.dict.annotation.BeanAttr;
import com.air.utils.dict.annotation.BeanType;

@BeanType(type = 2,description="公司列表")
public class CompanyList extends BaseDict{

	private static final long serialVersionUID = 1L;

	@BeanAttr(cnLabel="系统默认",enLabel="CompanyName")
	 public static final long COMPANY1=1L;
	 
	 @BeanAttr(cnLabel="系统默认1",enLabel="CompanyName1",sort=1,parentId=COMPANY1)
	 public static final long COMPANY2=2L;
	 
	 @BeanAttr(cnLabel="系统默认2",enLabel="CompanyName2",sort=2,enable=false)
	 public static final long COMPANY3=3L;
	 
	 @BeanAttr(cnLabel="系统默认3",enLabel="CompanyName3",sort=3,enable=false)
	 public static final long COMPANY4=4L;
	 
	 
	 @BeanAttr(cnLabel="系统默认4",enLabel="CompanyName4",sort=4,enable=false)
	 public static final long COMPANY5=5L;
	 
	 @BeanAttr(cnLabel="系统默认5",enLabel="CompanyName5",sort=5,enable=false)
	 public static final long COMPANY6=6L;
	 
	 
	 @BeanAttr(cnLabel="系统默认6",enLabel="CompanyName6",sort=6,enable=false)
	 public static final long COMPANY7=7L;
	 
	 @BeanAttr(cnLabel="系统默认7",enLabel="CompanyName7",sort=7,enable=false)
	 public static final long COMPANY8=8L;
	 
	 @BeanAttr(cnLabel="系统默认8",enLabel="CompanyName8",sort=8,enable=false)
	 public static final long COMPANY9=9L;
	 
	 @BeanAttr(cnLabel="系统默认9",enLabel="CompanyName9",sort=9,enable=false)
	 public static final long COMPANY10=10L;
	 
}
