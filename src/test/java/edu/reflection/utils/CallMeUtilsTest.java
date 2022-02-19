package edu.reflection.utils;

import edu.reflection.annotation.CallMe;
import edu.reflection.annotation.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CallMeUtilsTest {

	private static class Foo {
		private static final int FIRST = 1;
		private static final int SECOND = 2;
		private static final int LAST = Integer.MAX_VALUE;
		private static final List<Integer> invokedMethods = new ArrayList<>();

		@Order(FIRST)
		@CallMe
		void first() {
			invokedMethods.add(FIRST);
		}

		@Order(LAST)
		@CallMe
		protected void last() {
			invokedMethods.add(LAST);
		}

		@Order(SECOND)
		@CallMe
		void second() {
			invokedMethods.add(SECOND);
		}

		@CallMe(maybe = false)
		public void withMaybeFalse() {
			throw new IllegalStateException();
		}

		@Order(19)
		public void withoutCallMe() {
			throw new IllegalStateException();
		}
	}

	@Test
	void methodsInvokedInOrder() throws ReflectiveOperationException {
		CallMeUtils.invoke(Foo.class.getName());

		assertEquals(List.of(Foo.FIRST, Foo.SECOND, Foo.LAST), Foo.invokedMethods);
	}

	@Test
	void nonExistingClass_ThrowsClassNotFoundException() {
		Assertions.assertThrows(ClassNotFoundException.class,
				() -> CallMeUtils.invoke("kjytiretwyotoer"));
	}

	@Test
	void nonExistingConstructor_NoSuchMethodException() {
		Assertions.assertThrows(NoSuchMethodException.class, () ->
				CallMeUtils.invoke("edu.reflection.demo.MyClass"));
	}

	static class WithNonPublicMethods {
		private static boolean methodInvoked;

		@CallMe
		private void called() {
			methodInvoked = true;
		}
	}

	@Test
	void nonPublicMethodsInvoked() throws ReflectiveOperationException {
		CallMeUtils.invoke(WithNonPublicMethods.class.getName());

		assertTrue(WithNonPublicMethods.methodInvoked);
	}
}