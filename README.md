project-dict
	Dict/dictionary is a easy and simple tool for most project.

maven

	<dependency>
	  <groupId>com.github.air-project</groupId>
	  <artifactId>project-dict</artifactId>
	  <version>0.1</version>
	</dependency>


 * 使用如下：
 * 
 * 提供两种实现方式
 * 
 * 一、单服务器->实现DBMemDictCacheDemo的getAllList和save即可。
 * 在服务器启动时初始化DictManager.init(new DBMemDictCache(new DBMemDictCacheDemo()));
 * 会加载实体BaseDict对应表到内存中，BaseDict默认采用hibernate配置
 * 
 * 	注意
 * 	1.如果您的系统也采用hibernate,请在spring配置文件中添加（采用mybatis请忽略这一点）
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
 *   

 *  附(mysql建表语句)：
 *  
 *DROP TABLE IF EXISTS sys_dict_list;
*	CREATE TABLE sys_dict_list (
*	  id bigint(20) NOT NULL AUTO_INCREMENT,
*	  attr bigint(20) NOT NULL,
*	  cnLabel varchar(255) DEFAULT NULL,
*	  company bigint(20) NOT NULL,
*	  create_date datetime DEFAULT NULL,
*	  create_user varchar(255) DEFAULT NULL,
*	  create_user_id bigint(20) DEFAULT NULL,
*	  description varchar(255) DEFAULT NULL,
*	  enLabel varchar(255) DEFAULT NULL,
*	  enabe bit(1) NOT NULL,
*	  parentId bigint(20) NOT NULL,
*	  project bigint(20) NOT NULL,
*	  remark varchar(255) DEFAULT NULL,
*	  sort bigint(20) NOT NULL,
*	  type bigint(20) NOT NULL,
*	  update_date datetime DEFAULT NULL,
*	  update_user varchar(255) DEFAULT NULL,
*	  update_user_id bigint(20) DEFAULT NULL,
*	  value varchar(255) DEFAULT NULL,
*	  PRIMARY KEY (id)
*	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
