package com.air.utils.dict.cache;


import java.util.Map;

import com.air.utils.dict.entity.BaseDict;

public interface DictCache {
	/**
	 * 获取数据字典 
	 * @param cls 字典 
	 * @return 数据字典 
	 */
	
	public Map<Long,BaseDict> getMap(Class<? extends BaseDict> cls) ;
	
	/**
	 * 
	 * @return 是否有同步接口 
	 */
	public boolean isSync();
	
	/**
	 * 
	 * @return 有同步接口 的实现子类
	 */
	@SuppressWarnings("rawtypes")
	public Class impType();
	
}
