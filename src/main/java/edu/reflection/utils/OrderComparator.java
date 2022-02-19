package edu.reflection.utils;

import edu.reflection.annotation.Order;

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

		int order1 = Integer.MIN_VALUE;
		int order2 = Integer.MIN_VALUE;
		if (method1.isAnnotationPresent(Order.class)) {
			order1 = method1.getAnnotation(Order.class).value();
		}
		if (method2.isAnnotationPresent(Order.class)) {
			order2 = method2.getAnnotation(Order.class).value();
		}

		return Integer.compare(order1, order2);
	}
}
