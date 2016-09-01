package com.air.utils.dict.cache;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.air.utils.dict.annotation.BeanAttr;
import com.air.utils.dict.annotation.BeanType;
import com.air.utils.dict.entity.BaseDict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDictCache implements DictCache {

	protected static Map<Class<? extends BaseDict>, String> tmpMap = Maps.newHashMap();

	protected List<BaseDict> put(Class<? extends BaseDict> cls) {
		List<BaseDict> adList = Lists.newArrayList();
		try {
			if (cls.isAnnotationPresent(BeanType.class)) {
				BeanType beanType = (BeanType) cls.getAnnotation(BeanType.class);
				long type = beanType.type();
				long companyId = beanType.companyId();
				long projectId = beanType.projectId();
				String description = beanType.description();
				Field[] at = cls.getDeclaredFields();
				for (Field f : at) {
					f.setAccessible(true);
					if (f.isAnnotationPresent(BeanAttr.class)) {
						long attr = (long) f.get(cls);
						BeanAttr annotation = (BeanAttr) f.getAnnotation(BeanAttr.class);
						BaseDict ba = new BaseDict();
						ba.setAttr(attr);
						if (null != annotation.value() && !"".equals(annotation.value())) {
							ba.setValue(annotation.value());
						} else {
							ba.setValue(String.valueOf(attr));
						}
						ba.setType(type);
						ba.setCompany(companyId);
						ba.setProject(projectId);
						ba.setCnLabel(annotation.cnLabel());
						ba.setEnLabel(annotation.enLabel());
						ba.setDescription(description);
						ba.setSort(annotation.sort());
						ba.setParentId(annotation.parentId());
						ba.setEnabe(annotation.enable());
						ba.setRemark(annotation.remak());
						adList.add(ba);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return adList;
	}
}
