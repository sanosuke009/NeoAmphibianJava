package PageObjects;

import org.openqa.selenium.By;

import BaseClasses.DataInjection;

public class YouTubePage{
	
	DataInjection di;
	public By headerVideoTitle(String text) { return By.xpath("//h1/*[contains(text(),'"+text+"')]");}
	
	public YouTubePage(DataInjection di)
	{
		this.di = di;
	}
	
	public synchronized boolean validateVideoTitle(String title)
	{
		boolean res= true;
		if(res) res = di.wait(headerVideoTitle(title));
		if(res) res = di.isExisting(headerVideoTitle(title));
		if(res) di.report("The title of the video is "+title);
		else di.report("The title of the video is NOT "+title);
		di.takeScreenShot();
		return res;
	}
	
}
