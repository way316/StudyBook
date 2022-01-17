package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClassUtil {
	public static final String FILE_PROTOCOL = "file";

	/**
	 * Get all classes of the package
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> extractPackageClass(String packageName) {
		//1. Get ClassLoader
		ClassLoader classLoader = getClassLoader();
		//2. Get Resource URL by the ClassLoader
		URL url = classLoader.getResource(packageName.replace(".","/"));
		if (url == null) {
			log.warn("Unable to retrieve anything from package: " + packageName);
			return null;
		}
		Set<Class<?>> classSet = null;
		//3. Get Resource Collections based on the type of Resource
		if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
			classSet = new HashSet<Class<?>>();
			File packageDirectory = new File(url.getPath());
			extractClassFile(classSet,packageDirectory,packageName);
		}
		return classSet;
	}

	/**
	 *
	 * @param emptyClassSet
	 * @param fileSource
	 * @param packageName
	 */

	private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
		if (!fileSource.isDirectory()) {
			return;
		}
		File[] files = fileSource.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if(file.isDirectory()){
					return true;
				} else {
					String absoluteFilePath = file.getAbsolutePath();
					if (absoluteFilePath.endsWith(".class")) {
						addToClassSet(absoluteFilePath);
					}
				}
				return false;
			}
			private void addToClassSet(String absoluteFilePath) {
				absoluteFilePath = absoluteFilePath.replace(File.separator,".");
				String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
				className = className.substring(0,className.lastIndexOf("."));
				Class targetClass = loadClass(className);
				emptyClassSet.add(targetClass);
			}
		});
		if (files != null) {
			for (File f: files) {
				extractClassFile(emptyClassSet,f,packageName);
			}

		}
	}

	public static Class<?> loadClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			log.error("load class ERROR",e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Create Object from Class
	 * @param clazz Class
	 *  @param accessible boolean
	 * @return Current Classloader
	 */
	public static <T> T newInstance(Class<?> clazz, boolean accessible) {
		try {
			Constructor constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(accessible);
			return (T)constructor.newInstance();
		} catch (Exception e) {
			log.error("newInstance Error");
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get ClassLoader
	 * @return Current Classloader
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static void setField(Field field, Object target, Object value, Boolean accessible) {
		field.setAccessible(accessible);
		try {
			field.set(target,value);
		} catch (IllegalAccessException e) {
			log.error("set Filed failed", e);
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] a) {
		extractPackageClass("com.imoc.entity");
	}


}
