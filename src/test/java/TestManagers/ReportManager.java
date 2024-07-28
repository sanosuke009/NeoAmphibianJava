package TestManagers;

import org.openqa.selenium.OutputType;

import BaseClasses.BaseC;

public class ReportManager extends BaseC{
	
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

	public synchronized void takeScreenShot(String name)
	{
		byte[] sc = driver.getScreenshotAs(OutputType.BYTES);
		scenario.attach(sc, "image/png", name);
	}

	public synchronized void takeScreenShot(String screenshotFileType, String name)
	{
		byte[] sc = driver.getScreenshotAs(OutputType.BYTES);
		scenario.attach(sc, screenshotFileType, name);
	}

}
