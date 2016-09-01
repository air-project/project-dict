/**
 * 使用如下：
 * 
 * 提供两种实现方式
 * 
 * 一、单服务器->实现DBMemDictCacheDemo的getAllList和save即可。
 * 在服务器启动时初始化DictManager.init(new DBMemDictCache(new DBMemDictCacheDemo()));
 * 会加载实体BaseDict对应表到内存中，BaseDict默认采用hibernate配置
 * 
 * 	注意1.如果您的系统也采用hibernate,请在spring配置文件中添加（采用mybatis请忽略这一点）
 *  	<property name="packagesToScan">
            <list>
                <value>com.air.utils.dict.*</value>
            </list>
        </property>
        
        2. DBMemDictCacheDemo 实现save方法时
        	每次获取字典数据的时候，如果内存中没有会调用save方法
        	调用save方法时，请您自己判断数据库是否已经存在相同数据，区分数据的唯一条件是：company+project+type
        	
 * 二、分布式->实现RedisDictCacheDemo的getDict和save即可。
 * 在服务器启动时初始化DictManager.init(new RedisDictCache(new RedisDictCacheDemo()));
 * 不会加载任何数据到内存中
 * 		注意：1.您需要单独把你要的数据转发到您的redis中，redis中的key请参考BaseDict中的getKey，value为整个type对应的值，是一个List<BaseDict>
 * 
 *           2.在实现RedisDictCacheDemo中的getDict和save方法，请不要重新定义cacheKey
 *     
 *  
 *   使用方式：
 *   定义类如下
 *  @BeanType(type = 112,description="公司列表")
	public class CompanyTest extends BaseDict{
		 @BeanAttr(cnLabel="系统默认1",enLabel="CompanyName1",sort=1,parentId=COMPANY1)
		 public static final long COMPANY2=2L;
	 }
 *   获取字典：
 *   DictManager.getDict(CompanyTest.class, CompanyTest.COMPANY2)  
 *         
 *   获取缓存key：
 *   DictManager.getCacheKey(CompanyTest.class, CompanyTest.COMPANY2)  
 *         
 *   获取字典Value：
 *   DictManager.getValue(CompanyTest.class, CompanyTest.COMPANY2)  
 *         
 *   获取整个类型字典：
 *   DictManager.getList(CompanyTest.class)  
 *          
 *    如有疑问请参考测试包中的DictManagerTest
 */

package com.air.utils.dict;

import java.util.List;
import java.util.Map;

import com.air.utils.dict.cache.DBSyncAble;
import com.air.utils.dict.cache.DictCache;
import com.air.utils.dict.cache.RedisSyncAble;
import com.air.utils.dict.entity.BaseDict;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * @author air
 *
 *         2016年1月28日 下午3:15:53
 */
@Slf4j
public final class DictManager {
	private static DictCache cache;
	
	private DictManager(){
		
	}
	/**
	 * 初始化
	 * @param cache
	 */
	public static void init(DictCache cache){
		DictManager.cache=cache;
		if(!cache.isSync()){
			System.err.println("没有任何同步接口");
			System.exit(0);
		}else{
			if(cache.impType().isInstance(DBSyncAble.class)){
				log.info("采取的是DB实现");
			}else if(cache.impType().isInstance(RedisSyncAble.class)){
				log.info("采取的是Redis实现");
			}
		}
	}
	
	/**
	 * 获取数据字典
	 * @param cls
	 */
	public static <T extends BaseDict>  BaseDict getDict(Class<T> cls,long attr) {
		Map<Long,BaseDict>  tmp = cache.getMap(cls);
		return tmp.get(attr);
	}
	/**
	 * 获取数据字典Value
	 * @param cls
	 */
	public static <T extends BaseDict>  String getValue(Class<T> cls,long attr) {
		Map<Long,BaseDict>  tmp = cache.getMap(cls);
		return tmp.get(attr).getValue();
	}
	/**
	 * 获取数据字典List
	 * @param cls
	 */
	public static <T extends BaseDict>  List<BaseDict> getList(Class<T> cls) {
		Map<Long,BaseDict>  tmp = cache.getMap(cls);
		return Lists.newArrayList(tmp.values());
	}
	/**
	 * 获取数据字典cacheKey
	 * @param cls
	 */
	public static <T extends BaseDict>  String getCacheKey(Class<T> cls,long attr) {
		Map<Long,BaseDict>  tmp = cache.getMap(cls);
		return tmp.get(attr).getKey();
	}
}
