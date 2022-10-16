package TestManagers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.FileUtilities;
import Utilities.JsonUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager extends ReportManager{
	
	public synchronized void initBrowser()
	{
		try {
		if(getConfig("browser").equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			//options.setPageLoadStrategy(PageLoadStrategy.NONE); //Only wait till the basic HTML page loading
			//options.setPageLoadStrategy(PageLoadStrategy.EAGER);//Wait till all the basic images, not the stylesheets
			//options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			if(getConfig("proxyon").contains("Y"))
			{
				Proxy proxy = new Proxy();
				proxy.setHttpProxy(getConfig("proxy"));
				options.setCapability("proxy", proxy);
			}
			//options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			Map<String, Object> prefs = new HashMap<String, Object>();
			//prefs.put("profile.default_content_setting_values.notifications", 2);
			//prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.prompt_for_download", "false");
			//prefs.put("download.", "false");
			prefs.put("download.default_directory", System.getProperty("user.dir")+"\\"+getConfig("downloadpath"));
			options.setExperimentalOption("prefs", prefs);
			//options.setExperimentalOption("useAutomationExtension", false);//Deprecated
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//options.addArguments("start-maximized");
			//options.addArguments("-headless");

			//========================================================
			ChromeDriverService services = new ChromeDriverService.Builder().withSilent(true).withLogFile(new File(FileUtilities.abs(getConfig("chromelogpath")))).build();
			//driver = new ChromeDriver(services, options);
			//driver = ThreadGuard.protect(new ChromeDriver(options));//Thread guard protects the ownership of the webdriver.
			//System.out.println("Initiated webdriver.");
			driver.manage().window().maximize();
			
		}
		else if(getConfig("browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(getConfig("browser").equals("android"))
		{
			dc = new DesiredCapabilities(JsonUtils.readJson(getConfig("appiumCapabilitiesDefault")));
			driver = new AndroidDriver<WebElement>(new URL(getConfig("appiumServerURL")), dc);
		}
		else if(getConfig("browser").equals("iOS"))
		{
			dc = new DesiredCapabilities(JsonUtils.readJson(getConfig("appiumCapabilitiesDefault")));
			driver = new IOSDriver<WebElement>(new URL(getConfig("appiumServerURL")), dc);
		}
		else
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(getConfig("implicitwait"))));
		wait = new WebDriverWait(driver, Integer.valueOf(getConfig("explicitwait")));
		fwait = new FluentWait<RemoteWebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Integer.valueOf(getConfig("fluentwaitTimeout"))))
				.pollingEvery(Duration.ofSeconds(Integer.valueOf(getConfig("fluentwaitPolling"))))
				.ignoring(NoSuchElementException.class)
				.withMessage("Waiting for 10 seconds polling every 2 seconds.");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized boolean getURL(String url)
	{
		try {
			driver.get(url);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public synchronized boolean close()
	{
		try {
			driver.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public synchronized boolean quit()
	{
		try {
			driver.quit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
