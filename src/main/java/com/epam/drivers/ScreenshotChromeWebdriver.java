package com.epam.drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ScreenshotChromeWebdriver extends ChromeDriver {

	public ScreenshotChromeWebdriver(final DesiredCapabilities additonalCapabilities, boolean isPrivateMode,
			boolean isHeadless) {
		super(getDriverService(), getAllCapabilities(additonalCapabilities, isPrivateMode, isHeadless));
	}

	private static DesiredCapabilities getAllCapabilities(DesiredCapabilities additonalCapabilities, boolean isPrivateMode,
			boolean isHeadless) {
		DesiredCapabilities desiredCapabilities = getBaseCapabilities(isPrivateMode, isHeadless);
		desiredCapabilities.merge(additonalCapabilities);
		return desiredCapabilities;
	}

	private static ChromeDriverService getDriverService() {

		File driverExecutabke = new File(
				ScreenshotChromeWebdriver.class.getClassLoader().getResource("chromedriver.exe").getFile());
		return new ChromeDriverService.Builder().usingDriverExecutable(driverExecutabke).usingAnyFreePort().build();
	}

	private static DesiredCapabilities getBaseCapabilities(boolean isPrivateMode, Boolean isHeadless) {
		DesiredCapabilities desiredCapabailities = new DesiredCapabilities();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments(new String[] { "--test-type" });
		chromeOptions.addArguments(new String[] { "--disable-device-discovery-notifications" });
		chromeOptions.addArguments(new String[] { "--disable-desktop-notifications" });
		chromeOptions.addArguments(new String[] { "--start-maximized" });
		if (isHeadless) {
			chromeOptions.addArguments(new String[] { "--headless" });
			chromeOptions.addArguments(new String[] { "--window-size=1200*600" });
		}
		if (isPrivateMode) {
			chromeOptions.addArguments(new String[] { "incognito" });
		}
		desiredCapabailities.setCapability("goog:chromeOptions", chromeOptions);
		desiredCapabailities.setCapability("takesScreenshot", true);
		return desiredCapabailities;
	}
}
