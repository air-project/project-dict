package com.air.utils.dict.demo;


import java.util.List;

import com.air.utils.dict.cache.DBSyncAble;
import com.air.utils.dict.entity.BaseDict;

public class DBMemDictCacheDemo  implements DBSyncAble{

	@Override
	public List<BaseDict> getAllList() {
		return null;
	}

	@Override
	public void save(List<BaseDict> dicts) {
		
	}
}
