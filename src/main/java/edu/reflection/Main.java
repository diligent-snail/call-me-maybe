package edu.reflection;

import edu.reflection.utils.CallMeUtils;

import java.util.Objects;
import java.util.Scanner;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Введите имя класса для поиска: ");

		while (scanner.hasNext()) {
			String className = scanner.next();
			if (Objects.equals(className, "exit")) {
				break;
			}

			invokeCallMeMethods(className);
			System.out.print("Введите имя класса для поиска: ");
		}
	}

	public static void invokeCallMeMethods(String className) {
		try {
			CallMeUtils.invoke(className);
		} catch (ReflectiveOperationException e) {
			System.out.println("Произошла ошибка. " + getExceptionMessage(e) + ". Stack trace:");
			e.printStackTrace(System.out);
		}
	}

	private static String getExceptionMessage(Throwable throwable) {
		String message = throwable.getMessage();
		if (message != null) {
			return "Сообщение исключения: '" + message + "'";
		}
		return "Сообщения исключения нет";
	}
}
