package com.air.utils.dict.cache.impl;


import java.util.List;
import java.util.Map;

import com.air.utils.dict.cache.AbstractDictCache;
import com.air.utils.dict.cache.RedisSyncAble;
import com.air.utils.dict.entity.BaseDict;
import com.google.common.collect.Maps;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@NoArgsConstructor
public class RedisDictCache extends AbstractDictCache {

	private RedisSyncAble redisSyncAble;
	
	
	public RedisSyncAble getRedisSyncAble() {
		return redisSyncAble;
	}
	public void setRedisSyncAble(RedisSyncAble redisSyncAble) {
		this.redisSyncAble = redisSyncAble;
	}
	public RedisDictCache(RedisSyncAble redisSyncAble){
		this.redisSyncAble=redisSyncAble;
	}
	
	public boolean isSync(){
		return redisSyncAble!=null;
	}
	@SuppressWarnings("rawtypes")
	public Class impType(){
		if(isSync()){
			return redisSyncAble.getClass();
		}
		return null;
	}
	private Map<Long, BaseDict> toMap(List<BaseDict> list) {
		Map<Long, BaseDict> map = Maps.newHashMap();
		for (BaseDict a : list) {
			map.put(a.getAttr(), a);
		}
		return map;
	}

	@Override
	public Map<Long, BaseDict> getMap(Class<? extends BaseDict> cls) {
		if (tmpMap.get(cls) == null) {
			List<BaseDict> adList= put(cls);
			if (!adList.isEmpty()) {
				BaseDict ba = adList.get(0);
				tmpMap.put(cls, ba.getKey());
				
				if(isSync()){
					redisSyncAble.save(ba.getKey(),adList);
					log.info(ba.getKey()+"保存数据到redis中");
					return toMap(adList);
				}
//				RedisUtil.setObject(ba.getKey(), adList, 0);
				
			}
		} else {
			String cacheKey = tmpMap.get(cls);
//			List<BaseDict> list = (List<BaseDict>) RedisUtil.getObject(cacheKey);
			if(isSync()){
				log.info(cacheKey+"从redis中获取数据");
				List<BaseDict> list =redisSyncAble.getDict(cacheKey);
				if (list != null && !list.isEmpty()) {
					return toMap(list);
				}
			}
		}
		return null;
	}
}
