package AtomicMethods;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import TestManagers.DriverManager;
import Utilities.ClipBoardUtil;
import Utilities.FileUtilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ElementActs extends DriverManager{

	public synchronized boolean wait(By loc)
	{
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(loc));
			res = true;
		}
		catch(Exception e)
		{
			report("Error occurred during waiting for element "+loc);
		}
		return res;
	}
	public synchronized boolean isExisting(By loc)
	{
		boolean res = false;
		try {
			res = driver.findElements(loc).size()==1;
		}
		catch(Exception e)
		{
			report("Error occurred during validation of existence of "+loc);
		}
		return res;
	}
	
	public synchronized boolean areExisting(By loc)
	{
		boolean res = false;
		try {
			res = driver.findElements(loc).size()>0;
		}
		catch(Exception e)
		{
			report("Error occurred during validation of multiple existence of "+loc);
		}
		return res;
	}
	
	public synchronized boolean click(By loc)
	{
		boolean res = false;
		try {
			driver.findElement(loc).click();
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during clicking "+loc);
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized boolean tap(By loc)
	{
		boolean res = false;
		try {
			TouchAction<?> ta = new TouchAction((AppiumDriver<WebElement>) driver);
			Point leftUpCorner = driver.findElement(loc).getLocation();
			Rectangle rec = driver.findElement(loc).getRect();
			int x = leftUpCorner.x + (rec.width/2);
			int y = leftUpCorner.y + (rec.height/2);
			PointOption<?> point = PointOption.point(x, y);
			ta.tap(point);
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during tapping "+loc);
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized boolean press(By loc)
	{
		boolean res = false;
		try {
			TouchAction<?> ta = new TouchAction((AppiumDriver<WebElement>) driver);
			Point leftUpCorner = driver.findElement(loc).getLocation();
			Rectangle rec = driver.findElement(loc).getRect();
			int x = leftUpCorner.x + (rec.width/2);
			int y = leftUpCorner.y + (rec.height/2);
			PointOption<?> point = PointOption.point(x, y);
			ta.press(point);
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during pressing "+loc);
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized boolean longPress(By loc)
	{
		boolean res = false;
		try {
			TouchAction<?> ta = new TouchAction((AppiumDriver<WebElement>) driver);
			Point leftUpCorner = driver.findElement(loc).getLocation();
			Rectangle rec = driver.findElement(loc).getRect();
			int x = leftUpCorner.x + (rec.width/2);
			int y = leftUpCorner.y + (rec.height/2);
			PointOption<?> point = PointOption.point(x, y);
			ta.longPress(point);
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during long pressing "+loc);
		}
		return res;
	}
	
	//Yet to be implemented
	@SuppressWarnings("unchecked")
	public synchronized boolean swipeTo(By loc, Directions dir, int count)
	{
		boolean res = false; int ANIMATION_TIME = 200, PRESS_TIME = 200, 
				border = 10, amount = 80, 
				x = 0,y = 0
				,x1 = 0,y1 = 0;
		try {
			PointOption<?> pointstart, pointend;
			TouchAction<?> ta = new TouchAction((AppiumDriver<WebElement>) driver);
			Dimension dim = driver.manage().window().getSize();
			x = dim.width/2;
			y = dim.height/2;
			pointstart = PointOption.point(x, y);
			
			switch(dir)
			{
			case DOWN: 
				amount = dim.height/2 - border; x1 = x; y1 = y + amount;
				break;
			case LEFT:
				amount = dim.width/2 - border; x1 = x - amount; y1 = y;
				break;
			case RIGHT:
				amount = dim.width/2 - border; x1 = x + amount; y1 = y;
				break;
			case UP:
				amount = dim.height/2 - border; x1 = x; y1 = y - amount;
				break;
			default:
				break;
			}
			pointend = PointOption.point(x1, y1);
			for(int i=0;i<count;i++)
			{
				if(!isExisting(loc))
				{
					ta.press(pointstart)
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
					.moveTo(pointend)
					.release()
					.perform();
				}
				else
				{
					res =  true;
				}
			}
			hardWait(ANIMATION_TIME);
		}
		catch(Exception e)
		{
			report("Error occurred during swipe to "+loc);
		}
		return res;
	}
	
	public synchronized boolean sendKeys(By loc, String key)
	{
		boolean res = false;
		try {
			driver.findElement(loc).clear();
			driver.findElement(loc).sendKeys(key);
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during sending Keys "+loc);
		}
		return res;
	}
	
	public synchronized boolean sendKeys(By loc, Keys key)
	{
		boolean res = false;
		try {
			driver.findElement(loc).sendKeys(key);
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during sending Keys "+loc);
		}
		return res;
	}
	
	public synchronized boolean sendKeysUsingClipboard(By locator, String keysToSend)
	{
		boolean res = true;
		try {
			if(res) res = ClipBoardUtil.copy(keysToSend);
			if(res) driver.findElement(locator).clear();
			if(res) driver.findElement(locator).click();
			if(res) res = ClipBoardUtil.paste();
		}
		catch(TimeoutException e)
		{
			report("The STring "+keysToSend+" was not given as input in element "+locator);
			res =false;
		}
		return res;
	}
	
	public synchronized String getCurrentWindow()
	{
		String res = "";
		try {
			defaultWindow = driver.getWindowHandle();
			res =  defaultWindow;
		}
		catch(Exception e)
		{
			report("Error occurred during getting current window handle.");
		}
		return res;
	}
	
	public synchronized boolean switchToLatestWindow()
	{
		boolean res = false;
		try {
			Set<String> windows = driver.getWindowHandles();
			for(String w : windows)
			{
				if(!w.equals(defaultWindow))
					driver.switchTo().window(w);
			}
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during getting the latest window handle.");
		}
		return res;
	}
	
	public synchronized boolean switchToDefaultWindow()
	{
		boolean res = false;
		try {
			driver.switchTo().window(defaultWindow);
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during getting the default window handle "+defaultWindow);
		}
		return res;
	}
	
	public synchronized boolean actionOnAlert(AlertActions action)
	{
		boolean res = true;
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			//takeScreenShot();
			report("The alert report text is displayed as "+alert.getText());
			if(action.equals(AlertActions.ACCEPT))
			{
				alert.accept();
				report("The alert was accepted.");
			}
			else
			{
				alert.dismiss();
				report("The alert was dismissed.");
			}
			takeScreenShot();
		}
		catch(TimeoutException e)
		{
			report("The Alert is not visible.");
			res =false;
		}
		return res;
	}
	
	public synchronized boolean actionOnAlert(AlertActions action, String text)
	{
		boolean res = true;
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			//takeScreenShot();
			//takeScreenShot(LogStatus.INFO, "The alert report test is displayed as "+alert.getText());
			report("The alert report text is displayed as "+alert.getText());
			alert.sendKeys(text);
			//takeScreenShot(LogStatus.INFO, "The test "+text+" has been given as input to the alert.");
			//takeScreenShot();
			report("The text "+text+" has been given as input to the alert.");
			if(action.equals(AlertActions.ACCEPT))
			{
				alert.accept();
				report("The alert was accepted.");
			}
			else
			{
				alert.dismiss();
				report("The alert was dismissed.");
			}
			takeScreenShot();
		}
		catch(TimeoutException e)
		{
			report("The Alert is not visible.");
			res =false;
		}
		return res;
	}
	
	public synchronized boolean scrollToElement(By locator)
	{
		boolean res = true;
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement element = driver.findElement(locator);
			js.executeScript("arguments[0].scrollIntoView();", element);
		}
		catch(Exception e)
		{
			report("The page was not scrolled to element : "+locator+".");
			res =false;
		}
		return res;
	}
	
	public synchronized boolean scrollElementToMiddle(By locator)
	{
		boolean res = true;
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement element = driver.findElement(locator);
			js.executeScript("arguments[0].scrollIntoView"
					+ "({behavior: \"smooth\", block: \"center\", inline: \"nearest\"})", element);
		}
		catch(Exception e)
		{
			report("The page was not scrolled to element : "+locator+".");
			e.printStackTrace();
			res =false;
		}
		return res;
	}
	
	public synchronized boolean removeReadOnlyPropertyOfElement(By locator, int i)
	{
		boolean res = true;
		try {
			/*
			 * Second parameter of removeAttribute method of java script is optional we are mentioning it as '0' here for non case sensitive search, meaning of the different values are described below.
				0      It is the default value and performs the non case sensitive search
				1      It performs the case sensitive property search
				2      It returns the property value as it is set in the script or html code
			 */
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement element = driver.findElement(locator);
			js.executeScript("arguments[0].removeAttribute('readonly',"+i+");", element);
		}
		catch(Exception e)
		{
			report("The removal of readonly property from element : "+locator+".");
			res =false;
		}
		return res;
	}
	
	public synchronized boolean addTextInLabelToElement(By locator, String text)
	{
		boolean res = true;
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement element = driver.findElement(locator);
			js.executeScript("arguments[0].innerHTML = '" + text +"';", element);
		}
		catch(Exception e)
		{
			report("The "+text+" was NOT added to element : "+locator+".");
			res =false;
		}
		return res;
	}
	
	public synchronized String getText(By locator)
	{
		String name = "";
		try {
			name = driver.findElement(locator).getText();
		}
		catch(Exception e)
		{
			report("Error occurred during getting the text from element : "+locator+".");
		}
		return name;
	}
	
	public synchronized String getAttribute(By locator, ElementAttributes attr)
	{
		String name = "";
		try {
			switch(attr)
			{
				case VALUE:
					name = driver.findElement(locator).getAttribute("value");
					break;
				default:
					name = driver.findElement(locator).getAttribute("value");
					break;
			}
			
		}
		catch(Exception e)
		{
			report("Error occurred during getting the attribute "+attr+" from element : "+locator+".");
		}
		return name;
	}
	
	public synchronized boolean compareColor(By locator, String color)
	{
		boolean res = true;
		try {
			Color col = Color.fromString(color);
			Color elcol = Color.fromString(driver.findElement(locator).getCssValue("color"));
			res = col.asRgb().equals(elcol.asRgb());
		}
		catch(Exception e)
		{
			report("Error occurred during the validation of colour of element "+locator);
			res =false;
		}
		return res;
	}
	
	public synchronized boolean compareBackgroundColor(By locator, String color)
	{
		boolean res = true;
		try {
			Color col = Color.fromString(color);
			Color elcol = Color.fromString(driver.findElement(locator).getCssValue("background-color"));
			res = col.asRgb().equals(elcol.asRgb());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			report("Error occurred during the validation of backgroud-colour of element "+locator);
			res =false;
		}
		return res;
	}
	
	public synchronized String getColor(By locator)
	{
		String res = "";
		try {
			Color elcol = Color.fromString(driver.findElement(locator).getCssValue("color"));
			res = elcol.asRgb();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			report("Error occurred during getting the colour of element "+locator);
			res ="";
		}
		return res;
	}
	
	public synchronized String getBackgroundColor(By locator)
	{
		String res = "";
		try {
			Color elcol = Color.fromString(driver.findElement(locator).getCssValue("background-color"));
			res = elcol.asRgb();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			report("Error occurred during getting the backgroud-colour of element "+locator);
			res ="";
		}
		return res;
	}
	
	public synchronized boolean dragAndDrop(By locator, By locator1)
	{
		boolean res = false;
		try {
			String jspath = FileUtilities.abs(getConfig("dragndropjsfilepath"));
			
			WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
			WebElement el1 = wait.until(ExpectedConditions.elementToBeClickable(locator1));
			
			// It's even easier in Java 
	        StringBuffer sb = new StringBuffer();
	        //Files.lines(Paths.get(jspath), StandardCharsets.UTF_8).forEach(sb::append);
	        
			String script = sb.toString();
			script = script + "executeDrageAndDrop(arguments[0], arguments[1])";
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript(script, el, el1);

			/*int h = el.getSize().getHeight()/2;
			int w = el.getSize().getWidth()/2;
			int x = el.getLocation().getX()+w;
			int y = el.getLocation().getY()+h;
			
			int h1 = el1.getSize().getHeight()/2;
			int w1 = el1.getSize().getWidth()/2;
			int x1 = el1.getLocation().getX()+w1;
			int y1 = el1.getLocation().getY()+h1;
			
			int actX = x1 - x;
			int actY = y1 - y;*/
			
			/*int x = el.getLocation().getX();
			int y = el.getLocation().getY();
			
			int x1 = el1.getLocation().getX();
			int y1 = el1.getLocation().getY();
			
			
			System.out.println("x = "+x);
			System.out.println("y = "+y);
			System.out.println("x1 = "+x1);
			System.out.println("y1 = "+y1);*/
			
			//System.out.println("actX = "+actX);
			//System.out.println("actY = "+actY);
			
			//Actions actions = new Actions(driver);
			//Action action = actions.dragAndDropBy(el, x, y).build();
			/*Action action = actions.moveToElement(el)
					.clickAndHold(el)
					.moveToElement(el1)
					.build();
			action.perform();
			Action action1 = actions.release().build();
			action1.perform();*/
			/*Action action = actions
					.moveToElement(el)
					.pause(Duration.ofSeconds(1))
					.clickAndHold(el)
					.pause(Duration.ofSeconds(1))
					.moveByOffset(actX, actY)
					.moveToElement(el1)
					//.moveByOffset(x, y)
					.pause(Duration.ofSeconds(1))
					.release()
					.build();
			action.perform();*/
			/*Robot robot  = new Robot();
			robot.mouseMove(x, y);
			int mask = InputEvent.BUTTON1_DOWN_MASK;
			robot.mousePress(mask);
			robot.delay(3000);
			robot.mouseMove(x1, y1);
			robot.delay(3000);
			robot.mouseRelease(mask);*/
			//Thread.sleep(3000);
			res = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			report("Error occurred during dragging element : "+locator+" to element "+locator1);
		}
		return res;
	}
	
	public synchronized List<String> getTextOfAllSimilarElements(By locator)
	{
		List<String> names = new ArrayList<String>();
		List<WebElement> elms = new ArrayList<WebElement>();
		try {
			elms = driver.findElements(locator);
			for(WebElement elm : elms)
			{
				names.add(elm.getText());
			}
		}
		catch(Exception e)
		{
			report("Error occurred during getting the texts from element : "+locator+".");
		}
		return names;
	}
	
	public synchronized boolean selectDropDownList(By locator, String value)
	{
		boolean res = true;
		try {
			WebElement element = driver.findElement(locator);
			Select select = new Select(element);
			select.selectByValue(value);
		}
		catch(TimeoutException e)
		{
			report("The option "+value+" was not selected from Drop Down List element "+locator);
			res =false;
		}
		return res;
	}
	
	public synchronized boolean selectDropDownListMultiple(By locator, String values)
	{
		boolean res = true;
		try {
			String[] value = values.split("#");
			WebElement element = driver.findElement(locator);
			Select select = new Select(element);
			for(String val : value)
				{select.selectByValue(val);}
		}
		catch(TimeoutException e)
		{
			report("Error occurred during selection of options from Multi Drop Down List element "+locator);
			res =false;
		}
		return res;
	}
	
	public synchronized boolean highlightElement(By locator)
	{
		boolean res = true;
		try {
			if(getConfig("highlight").equals("true")) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement element = driver.findElement(locator);
			js.executeScript("arguments[0].style.border='3px solid red'", element);
			}
		}
		catch(Exception e)
		{
			report("Error occurred during high lighting element : "+locator+".");
			res =false;
		}
		return res;
	}
	
	public synchronized boolean lowlightElement(By locator)
	{
		boolean res = true;
		try {
			if(getConfig("highlight").equals("true")) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			WebElement element = driver.findElement(locator);
			js.executeScript("arguments[0].style.border=''",element, "");
			}
		}
		catch(Exception e)
		{
			report("Error occurred during low lighting element : "+locator+".");
			res =false;
		}
		return res;
	}
	
	public synchronized boolean hardWait(long waittime)
	{
		boolean res = false;
		try {
			Thread.sleep(waittime);
			res =  true;
		}
		catch(Exception e)
		{
			report("Error occurred during waiting for "+waittime+" milliseconds.");
		}
		return res;
	}
}
