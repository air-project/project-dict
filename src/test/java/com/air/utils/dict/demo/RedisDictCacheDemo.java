package com.air.utils.dict.demo;


import java.util.List;

import com.air.utils.dict.cache.RedisSyncAble;
import com.air.utils.dict.entity.BaseDict;

public class RedisDictCacheDemo implements RedisSyncAble {


	@Override
	public List<BaseDict> getDict(String cacheKey) {
		List<BaseDict> list = (List<BaseDict>) RedisUtil.getObject(cacheKey);
		return list;
	}

	@Override
	public void save(String cacheKey, List<BaseDict> dicts) {
		RedisUtil.setObject(cacheKey, dicts, 0);
	}
 
}
