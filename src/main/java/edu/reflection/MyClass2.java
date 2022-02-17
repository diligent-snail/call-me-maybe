package edu.reflection;

public class MyClass2 {
	@Order(1)
	@CallMe
	private void sayHello() {
		System.out.println("Hello");
	}

	@Order(2)
	@CallMe
	private void hereIsMyNumber() {
		System.out.println("Here's my number");
	}

	@Order(3)
	@CallMe
	void soCallMe() {
		System.out.println("So call me");
	}

	@Order(4)
	@CallMe(maybe = false)
	void maybe() {
		System.out.println("Maybe");
	}

	@Order(Integer.MAX_VALUE)
	@CallMe
	void theLastOne() {
		System.out.println("А меня надо вызвать последним");
	}

	@CallMe
	void withoutOrder() {
		System.out.println("А я без @Order'а меня можно вызывать в любом порядке");
	}
}
