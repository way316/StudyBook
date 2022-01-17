package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class DependencyInjector {
	/**
	 * Bean Container
	 */
	private BeanContainer beanContainer;
	public DependencyInjector() {
		beanContainer = BeanContainer.getInstance();
	}

	/**
	 * IOC
	 */
	public void doIoc() {
		if (ValidationUtil.isEmpty(beanContainer.getClasses())) {
			log.warn("empty classet in BeanContainer -doIoc");
			return;
		}
		for (Class<?> clazz : beanContainer.getClasses()) {
			Field[] fields = clazz.getDeclaredFields();
			if (ValidationUtil.isEmpty(fields)) {
				continue;
			}
			for (Field field:fields) {
				if (field.isAnnotationPresent(Autowired.class)) {
					Autowired autowired = field.getAnnotation(Autowired.class);
					String autowiredValue = autowired.value();
					Class<?> fieldClass = field.getType();
					Object fieldValue = getFieldInstance(fieldClass,autowiredValue);
					if (fieldValue == null) {
						throw new RuntimeException("unable to inject relevant type, target fieldClass is: " + fieldClass.getName() + autowiredValue);
					} else {
						Object targetBean = beanContainer.getBean(clazz);
						ClassUtil.setField(field,targetBean,fieldValue,true);
					}
				}
			}
		}
	}

	/**
	 * Get field Instance
	 * @param fieldClass
	 * @return
	 */
	private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
		Object fieldValue = beanContainer.getBean(fieldClass);
		if (fieldValue != null) {
			return fieldValue;
		} else {
			Class<?> implementedClass = getImplementClass(fieldClass,autowiredValue);
			if (implementedClass != null) {
				return beanContainer.getBean(implementedClass);
			} else {
				return null;
			}
		}
	}

	/**
	 * get implement Class from interface
	 * @param fieldClass
	 * @return
	 */
	private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
		Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
		if (!ValidationUtil.isEmpty(classSet)) {
			if(ValidationUtil.isEmpty(autowiredValue)) {
				 if(classSet.size() == 1) {
					 return classSet.iterator().next();
				 } else {
					 throw new RuntimeException("multiple implement classes for " + fieldClass.getName());
				 }
			} else {
				for (Class<?> clazz : classSet) {
					if (autowiredValue.equals(clazz.getSimpleName())) {
						return clazz;
					}
				}
			}
			log.warn("get Super Class failed -getImplementClass");
		}
		return null;
	}
}
