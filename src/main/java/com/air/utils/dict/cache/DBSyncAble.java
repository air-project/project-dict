package com.air.utils.dict.cache;


/**
 * 数据字典同步接口
 */

import java.util.List;

import com.air.utils.dict.entity.BaseDict;

/**
 * @author air
 *
 * 2016年2月3日 下午2:57:02
 */
public interface DBSyncAble {
	/**
	 *	获取所有数据字典 
	 */
	List<BaseDict> getAllList();
	
	/**
	 * 保存数据字典
	 * @param dict 字典
	 */
	void save(List<BaseDict> dicts);
}
