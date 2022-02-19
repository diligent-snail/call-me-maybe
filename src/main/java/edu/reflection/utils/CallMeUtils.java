package edu.reflection.utils;

import edu.reflection.annotation.CallMe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class CallMeUtils {
	private CallMeUtils() {
	}

	public static void invoke(String className)
			throws ReflectiveOperationException {
		Class<?> clazz = Class.forName(className);
		Constructor<?> constructor = clazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		Object instance = constructor.newInstance();
		List<Method> methods = new ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (m.trySetAccessible()) {
				if (m.isAnnotationPresent(CallMe.class)
						&& m.getAnnotation(CallMe.class).maybe()) {
					methods.add(m);
				}
			}
		}


		methods.sort(OrderComparator.INSTANCE);
		for (Method m : methods) {
			m.invoke(instance);
		}
	}
}
