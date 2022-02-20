package edu.reflection.utils;

import edu.reflection.annotation.Order;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Objects;

public class OrderComparator implements Comparator<Method> {
	public static final OrderComparator INSTANCE = new OrderComparator();

	private OrderComparator() {
	}

	@Override
	public int compare(Method method1, Method method2) {
		Objects.requireNonNull(method1);
		Objects.requireNonNull(method2);

		return Integer.compare(getOrder(method1), getOrder(method2));
	}

	private static int getOrder(AnnotatedElement annotatedElement) {
		Objects.requireNonNull(annotatedElement);
		if (!annotatedElement.isAnnotationPresent(Order.class)) {
			return Integer.MAX_VALUE;
		}
		Order annotation = annotatedElement.getAnnotation(Order.class);
		return annotation.value();
	}
}
