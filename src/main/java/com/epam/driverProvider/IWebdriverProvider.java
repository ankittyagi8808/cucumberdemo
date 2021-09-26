package com.epam.driverProvider;

import org.openqa.selenium.WebDriver;

public interface IWebdriverProvider {

	 WebDriver get();

	 void takeScreenShot();

	 void end();
}
