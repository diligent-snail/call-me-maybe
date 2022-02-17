package edu.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void checkClassGoodTest() {
		Assertions.assertDoesNotThrow(() -> Main.checkClass("edu.reflection.CarlyRaeJepsen"));
	}

	@Test
	void checkClassBadClass() {
		Assertions.assertDoesNotThrow(() -> Main.checkClass("edu.reflection.Class"));
	}

	@Test
	void checkClassBadConstructor() {
		Assertions.assertDoesNotThrow(() -> Main.checkClass("edu.reflection.MyClass"));
	}

	@Test
	void checkClassBadAccessible() {
		Assertions.assertDoesNotThrow(() -> Main.checkClass("edu.reflection.MyClass2"));
	}
}