package com.air.utils.dict;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.air.utils.dict.cache.impl.DBMemDictCache;
import com.air.utils.dict.cache.impl.RedisDictCache;
import com.air.utils.dict.demo.DBMemDictCacheDemo;
import com.air.utils.dict.demo.RedisDictCacheDemo;
import com.air.utils.dict.entity.CompanyList;
import com.air.utils.dict.entity.ProjectList;

/**
 * @author air
 *
 *         2016年1月28日 下午3:15:53
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DictManagerTest {

	@Before
	public   void init() {
		DictManager.init(new RedisDictCache(new RedisDictCacheDemo()));
//		DictManager.init(new DBMemDictCache(new DBMemDictCacheDemo()));
	}
	@Test
	public   void getDict() {
		System.out.println(DictManager.getDict(ProjectList.class, 1L));
	}
//	
//	@Test
//	public   void getList() {
//		System.out.println(DictManager.getList(ProjectList.class));
//	}
	

	
	@Test
	public   void getValue() {
		System.out.println(DictManager.getValue(ProjectList.class,1));
	}
	
	@Test
	public   void getKey() {
		System.out.println(DictManager.getCacheKey(ProjectList.class,2));
	}
}
