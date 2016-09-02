package com.air.utils.dict.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sys_dict_list")
@Data
@NoArgsConstructor
public class BaseDict implements Dict, Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column(name="create_user_id")
	private long createUserId; // 创建者
	
	@Column(name="create_user")
	private String createUser="system"; // 创建者
	
	@Column(name="create_date")
	private Date createDate = new Date(); // 创建日期

	@Column(name="update_user_id")
	private long updateUserId; // 更新者
	
	@Column(name="update_user")
	private String updateUser; // 更新者
	
	@Column(name="update_date")
	private Date updateDate; // 更新日期

	private long attr;  //属性值每次使用这个取值

	private String value;//属性VALUE值

	private long sort;//属性排序

	private boolean enabe; //属性是否启用，默认启用

	private String cnLabel;//属性中文

	private String enLabel;//属性英文

	private String remark;//属性备注

	private long type;//属性类型（很重要）在一个公司一个项目中必须唯一

	private String description;//属性类型描述

	private long company;//公司Id

	private long project;//项目Id

	private long parentId;//属性的父类

	/**
	 * 
	 * @return 缓存KEY,唯一
	 */
	public final String getKey() {
		return new StringBuilder("dict.cache.").append(company).append(".").append(project).append(".").append(type).toString();
	}

}
