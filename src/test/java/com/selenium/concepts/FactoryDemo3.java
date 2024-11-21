package com.selenium.concepts;

import org.testng.annotations.Factory;

public class FactoryDemo3 {

	@Factory
	public Object[] executeTest() {
		Object test[] = new Object[2];
		test[0] = new FactoryDemo();
		test[1] = new FactoryDemo1();
		return test;
	}

}
