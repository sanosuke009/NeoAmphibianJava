package TestManagers;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.JsonUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverManager extends ReportManager{
	
	public synchronized void initBrowser()
	{
		try {
		if(getConfig("browser").equals("chrome"))
		{
			driver = getConfig("headless").equals("true")?
			new ChromeDriver(getChromeOptions()):new ChromeDriver();
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
			//options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//options.addArguments("start-maximized");
			//options.addArguments("-headless");

			//========================================================
			//ChromeDriverService services = new ChromeDriverService.Builder().withSilent(true).withLogFile(new File(FileUtilities.abs(getConfig("chromelogpath")))).build();
			//driver = new ChromeDriver(services, options);
			//driver = ThreadGuard.protect(new ChromeDriver(options));//Thread guard protects the ownership of the webdriver.
			//System.out.println("Initiated webdriver.");
			driver.manage().window().maximize();
			
		}
		else if(getConfig("browser").equals("firefox"))
		{
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
			driver = new InternetExplorerDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.valueOf(getConfig("implicitwait"))));
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.valueOf(getConfig("explicitwait"))));
		fwait = new FluentWait<RemoteWebDriver>(driver)
				.withTimeout(Duration.ofSeconds(Integer.valueOf(getConfig("fluentwaitTimeout"))))
				.pollingEvery(Duration.ofSeconds(Integer.valueOf(getConfig("fluentwaitPolling"))))
				.ignoring(NoSuchElementException.class)
				.withMessage("Waiting for 10 seconds polling every 2 seconds.");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean getURL(String url)
	{
		try {
			driver.get(url);
			return true;
		} catch (Exception e) {
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
			e.printStackTrace();
			return false;
		}
	}

	/**
     * Method getChromeOptions.
     *
     * @return the chrome options.
     */
    public static ChromeOptions getChromeOptions() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        //chromeOptions.setExperimentalOption("useAutomationExtension", false);

        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        //chromeOptions.addArguments("--headless");
		chromeOptions.setHeadless(true);
        //chromeOptions.addArguments("--window-size=1580,1280");

        final HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }

}
