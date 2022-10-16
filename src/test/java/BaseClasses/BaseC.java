package BaseClasses;

import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class BaseC {
	
	protected Properties prop = new Properties();
	protected InputStream in;
	protected Scenario scenario;
	protected RemoteWebDriver driver;
	protected WebDriverWait wait;
	protected FluentWait<RemoteWebDriver> fwait;
	protected DesiredCapabilities dc;
	protected String defaultWindow;
	protected String currentWindow;
}
