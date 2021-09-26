package com.epam.driverProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.epam.drivers.ScreenshotChromeWebdriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	
	public WebDriver getWebDriver(final String browserType)  {
	     SupportedBrowser supportedBrowser=SupportedBrowser.valueOf(browserType);
	     if(Objects.isNull(supportedBrowser))
	         throw new InvalidBrowserTypeException(supportedBrowser + " is not supported");

	      switch(supportedBrowser){
	          case IE:
	          return null;
	          case CHROME:
				   String USERNAME = System.getenv("SAUCE_USERNAME");
				   String AUTOMATE_KEY = System.getenv("SAUCE_ACCESS_KEY");;
				   String UL = String.format("https://%s:%s@ondemand.eu-central-1.saucelabs.com:443/wd/hub",USERNAME,AUTOMATE_KEY);
				  try {
					  DesiredCapabilities caps = new DesiredCapabilities();
					  caps.setCapability(ChromeOptions.CAPABILITY, getChromeCapabilities());
					  // Iterate over the hashtable and set the capabilities
					 /* Set<String> keys = getChromeCapabilities().keySet();
					  Iterator<String> itr = keys.iterator();
					  while (itr.hasNext()) {
						 String key = itr.next();
						  caps.setCapability(key, getChromeCapabilities().get(key));
					  }*/
					  return new RemoteWebDriver(new URL(UL),caps);
				  } catch (MalformedURLException e) {
					  e.printStackTrace();
				  }
				  //return new ScreenshotChromeWebdriver(DesiredCapabilities.chrome(), false, false);
	          case FIREFOX:
	              return null;
	          default:
	              throw new InvalidBrowserTypeException(supportedBrowser + " browser is not supported");


	      }

	 }
	
	class InvalidBrowserTypeException extends RuntimeException {
		public InvalidBrowserTypeException(final String message) {
			super(message);
		}
	}

	public ChromeOptions getChromeCapabilities()
	{
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setCapability("platformName", "Windows 10");
		browserOptions.setCapability("browserVersion", "latest");
		//browserOptions.setCapability("build", "1234");
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("build", "1234");
		sauceOptions.setCapability("tags", "tag1");
		sauceOptions.setCapability("name", "webdriver Demo test");
		//sauceOptions.setCapability("tunnelIdentifier","oauth-ankittlife11-99eef_tunnel_id");
		browserOptions.setCapability("sauce:options", sauceOptions);
		/*Map<String, String> capsHashtable = new HashMap<String, String>();
		capsHashtable.put("browser", "chrome");
		capsHashtable.put("browser_version", "92.0");
		capsHashtable.put("os", "Windows");
		capsHashtable.put("os_version", "10");
		capsHashtable.put("build", "DEMO Test build");
		capsHashtable.put("name", "Demo tt");*/
        //capsHashtable.put("browserstack.local","true");
		return browserOptions;
	}
}
