package StepDefs;


import org.testng.Assert;

import BaseClasses.DataInjection;
import PageObjects.YouTubePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinitions{

	DataInjection di;
	
	@Before
	public void startUp(Scenario sc)
	{
		di = new DataInjection(sc);
		di.initBrowser();
	}
	
	@After
	public void tearDown()
	{
		di.quit();
	}
	
	@Given("I want to open {string} in browser")
	public void openLink(String url)
	{
		Assert.assertTrue(di.getURL(url));
	}
	
	@Then("I verify the {string} of video")
	public void verifyTitle(String title)
	{
		YouTubePage yp = new YouTubePage(di);
		Assert.assertTrue(yp.validateVideoTitle(title));
	}

}
