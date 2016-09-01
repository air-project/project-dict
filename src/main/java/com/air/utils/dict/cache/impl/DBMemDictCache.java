package com.air.utils.dict.cache.impl;


import java.util.List;
import java.util.Map;

import com.air.utils.dict.cache.AbstractDictCache;
import com.air.utils.dict.cache.DBSyncAble;
import com.air.utils.dict.entity.BaseDict;
import com.google.common.collect.Maps;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class DBMemDictCache extends AbstractDictCache {
	
	protected static Map<String,Map<Long,BaseDict>> map = Maps.newHashMap();
	
	private DBSyncAble syncAble;
	
	public DBMemDictCache(DBSyncAble syncAble)
	{
		this.syncAble=syncAble;
		init();
	}
	public DBSyncAble getSyncAble() {
		return syncAble;
	}

	public void setSyncAble(DBSyncAble syncAble) {
		this.syncAble = syncAble;
		init();
	}

	public boolean isSync(){
		return syncAble!=null;
	}
	
	@SuppressWarnings("rawtypes")
	public Class impType(){
		if(isSync()){
			return syncAble.getClass();
		}
		return null;
	}
	
	private void init(){
		if(isSync()){
			List<BaseDict> dbList=syncAble.getAllList();
			if(dbList!=null && !dbList.isEmpty()){
				for(BaseDict a:dbList){
					if(!map.containsKey(a.getKey())){
						map.put(a.getKey(), Maps.newHashMap());
					}
					map.get(a.getKey()).put(a.getAttr(), a);
				}
			}
		}
	}
	@Override
	public Map<Long,BaseDict> getMap(Class<? extends BaseDict> cls) {
		if(tmpMap.get(cls)==null){
			List<BaseDict> adList = put(cls);
			 if(!adList.isEmpty()){
				 BaseDict ba=adList.get(0);
				 if(map.get(ba.getKey())==null){
					 map.put(ba.getKey(), Maps.newHashMap());
				 }
				 for(BaseDict a:adList){
					 map.get(ba.getKey()).put(a.getAttr(), a);
				 }
				 
				 //save to db
				 if(isSync()){
					 syncAble.save(adList);
					 log.info(cls.getSimpleName()+"保存字典数据到数据库");
				 }
				 adList.clear();
				 tmpMap.put(cls, ba.getKey()); 
				 return map.get(ba.getKey());
			 }
		}else{
			return map.get(tmpMap.get(cls));
		}
		return null;
	}
}
