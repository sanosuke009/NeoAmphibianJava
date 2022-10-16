package TestManagers;

import org.openqa.selenium.OutputType;

public class ReportManager extends ConfigManager{
	
	public synchronized void report(String s)
	{
		scenario.log(s);
		System.out.println(s);
	}
	
	public synchronized void takeScreenShot()
	{
		byte[] sc = driver.getScreenshotAs(OutputType.BYTES);
		scenario.attach(sc, "image/png", "ScreenShot");
	}

}
