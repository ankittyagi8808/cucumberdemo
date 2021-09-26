package com.epam.driverProvider;

import java.util.Objects;

import org.openqa.selenium.WebDriver;

public class WebDriverProvider implements IWebdriverProvider{

	ThreadLocal<WebDriver> webDriverProvider = new ThreadLocal<>();

	private static WebDriverProvider instance = new WebDriverProvider();

	private WebDriverProvider()
	{
	}

	public static WebDriverProvider getWebDriverProvider()
	{
		return instance;
	}

	@Override
	public  WebDriver get() {
		WebDriver webDriver = webDriverProvider.get();
		if (Objects.isNull(webDriver)) {
			throw new DelegationBrowserNotFound("Unable to find browser Instance");
		}
		return webDriver;
	}

	public void intializeDriver(final String browserType) {
		webDriverProvider.set(new DriverFactory().getWebDriver(browserType));
	}

	@Override
	public void takeScreenShot() {

	}

	@Override
	public void end() {
		WebDriver webDriver = webDriverProvider.get();
		if(webDriver != null)
		{
			webDriver.quit();
		}
	}

	class DelegationBrowserNotFound extends RuntimeException {
		public DelegationBrowserNotFound(final String message) {
			super(message);
		}
	}

}
