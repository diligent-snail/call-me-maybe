package edu.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Введите имя класса для поиска: ");
		String className = scanner.next();
		while (!Objects.equals(className, "exit")) {
			checkClass(className);
			System.out.print("Введите имя класса для поиска: ");
			className = scanner.next();
		}

	}

	public static void checkClass(String className) {
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			Object instance = constructor.newInstance();
			ArrayList<Method> methods = new ArrayList<>();
			for (Method m : clazz.getDeclaredMethods()) {
				if (m.trySetAccessible()) {
					if (m.isAnnotationPresent(CallMe.class) && m.getAnnotation(CallMe.class).maybe()) {
						methods.add(m);
					}
				}
			}

			Comparator<Method> comparator = (method1, method2) -> {
				if (method1.getAnnotation(Order.class) != null && method2.getAnnotation(Order.class) != null) {
					return Integer.compare(method1.getAnnotation(Order.class).value(), method2.getAnnotation(Order.class).value());
				} else {
					return -1;
				}
			};

			methods.sort(comparator);
			for (Method m : methods) {
				m.invoke(instance);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Класса нет");
		} catch (InvocationTargetException e) {
			System.out.println("Что-то пошло не так");
		} catch (NoSuchMethodException | InstantiationException e) {
			System.out.println("У класса нет конструктора без параметров");
		} catch (IllegalAccessException e) {
			System.out.println("Метод недоступен");
		}
	}
}
