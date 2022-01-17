package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//Singleton
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BeanContainer {
	// Use to store all marked object
	private final Map<Class<?>,Object> beanMap = new ConcurrentHashMap();
	private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Controller.class, Repository.class, Service.class);

	public static BeanContainer getInstance() {
		return ContainerHolder.HOLDER.instance;
	}

	private enum ContainerHolder {
		HOLDER;
		private BeanContainer instance;
		ContainerHolder() {
			instance = new BeanContainer();
		}
	}

	private boolean loaded = false;

	public boolean isLoaded() {
		return loaded;
	}

	public int size() {
		return beanMap.size();
	}

	public synchronized void loadBeans(String packageName) {
		if (isLoaded()) {
			log.warn("Bean Container Has Been Loaded");
			return;
		}
		Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
		if (ValidationUtil.isEmpty(classSet)) {
			log.warn("extract nothing from packageName:" + packageName );
			return;
		}
		for (Class<?> clazz : classSet) {
			for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
				if(clazz.isAnnotationPresent(annotation)) {
					beanMap.put(clazz,ClassUtil.newInstance(clazz,true));
				}
			}
		}
		loaded = true;
	}

	/**
	 * add a Class and object to the beanContainer
	 *
	 * @param clazz
	 * @param bean
	 * @return bean
	 */

	public Object addBean(Class<?> clazz, Object bean) {
		return beanMap.put(clazz,bean);
	}

	/**
	 * remove an object from the beanContainer
	 *
	 * @param clazz
	 * @return Bean
	 */
	public Object getBean(Class<?> clazz) {
		return beanMap.get(clazz);
	}

	/**
	 * get All class object from the beanContainer
	 *
	 * @return Bean
	 */
	public Set<Class<?>> getClasses() {
		return beanMap.keySet();
	}

	/**
	 * get All bean Object from the beanContainer
	 *
	 * @return Bean Collection
	 */
	public Set<Object> getBeans() {
		return new HashSet<>(beanMap.values());
	}

	/**
	 * get Classes by annotation
	 *
	 * @return Set
	 */
	public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
		Set<Class<?>> keySet = getClasses();
		if (ValidationUtil.isEmpty(keySet)) {
			log.warn("nothing in the bean Map -getClassesByAnnotation");
			return null;
		}
		Set<Class<?>> classSet = new HashSet<>();
		for(Class<?> clazz : keySet) {
			if (clazz.isAnnotationPresent(annotation)) {
				classSet.add(clazz);
			}
		}
		return classSet.size()> 0? classSet: null;
	}

	/**
	 * get chile Classes or implement Classes by interface or  parent class.
	 *
	 * @return Set
	 */
	public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
		Set<Class<?>> keySet = getClasses();
		if (ValidationUtil.isEmpty(keySet)) {
			log.warn("nothing in the bean Map -getClassesBySuper");
			return null;
		}
		Set<Class<?>> classSet = new HashSet<>();
		for(Class<?> clazz : keySet) {
			if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)) {
				classSet.add(clazz);
			}
		}
		return classSet.size()> 0? classSet: null;
	}

	/**
	 * remove an object from the beanContainer
	 *
	 * @param clazz
	 * @return Bean
	 */
	public Object removeBean(Class<?> clazz) {
		return beanMap.remove(clazz);
	}

}
