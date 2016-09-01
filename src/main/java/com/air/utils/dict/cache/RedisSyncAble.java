package com.air.utils.dict.cache;


import java.util.List;

import com.air.utils.dict.entity.BaseDict;

public interface RedisSyncAble {
	/**
	 * 保存数据插入到数据库，分发到其他redis等等
	 */
	void save(final String cacheKey,List<BaseDict> dicts);
	
	/**
	 *	获取数据字典 
	 */
	List<BaseDict> getDict(final String cacheKey);
}
