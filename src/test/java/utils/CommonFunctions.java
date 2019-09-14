package utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public abstract class CommonFunctions extends MyListener{
	
	AndroidDriver<MobileElement> driver;
	
	public CommonFunctions(AndroidDriver<MobileElement> driver){
		this.driver=driver;
	}
	
	public AppiumDriver<MobileElement> getDriver(){
		return driver;
	}
	
	/**
	 * It will return the locator type during run time based on locator supply.
	 * 
	 * @param locator
	 * @return
	 */
	public By ByLocator(String locator) {
		By result = null;

		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		}else if(locator.startsWith("name=")) {
				result=By.name(locator.replace("name=", ""));
		}else if(locator.startsWith("id=")) {
			  result=By.id(locator.replace("id=", ""));
		 }else if(locator.startsWith("(")) {
			  result = By.xpath(locator);
		 }else if(locator.startsWith("uiautomator=")){
			  result=MobileBy.AndroidUIAutomator(locator.replace("uiautomator=", ""));
		 }else if(locator.startsWith("class=")){
			  result=By.className(locator.replace("class=", ""));
		 }else {
			  
			result = By.id(locator);
		}

		return result;
	}
	
	/**
	 * Wait till given time to visible element on screen.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void waitForElementsToVisible(String locator,int timeout){
	       try {
	           new WebDriverWait(getDriver(),timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ByLocator(locator)));
	       } catch (Exception e) {
	           test.log(Status.INFO, "Element is not visible after : "+timeout+" secods.");
	       }
	   }
	
	/**
	 * Check if element present or not. Return boolean value.
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isElementPresent(String locator) {
		Boolean result = false;
		try {
			getDriver().findElement(ByLocator(locator));
			result = true;
		} catch (Exception ex) {
		}
		return result;
	}
	
	/**
	 * Wait for element present
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void WaitForElementPresent(String locator, int timeout) {
		for (int i = 0; i < timeout; i++) {
			if (isElementPresent(locator)) {
				break;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Perform click operation on given locator.
	 * 
	 * @param locator
	 */
	public void clickOn(String locator){
		WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		WebElement el = getDriver().findElement(ByLocator(locator));
		el.click();
	}
	
	/**
	 * Enter text into given text field locator.
	 * 
	 * @param locator
	 * @param text
	 */
	public void sendKeys(String locator, String text) {
		WaitForElementPresent(locator, 30);
		Assert.assertTrue(isElementPresent(locator), "Element Locator :"
				+ locator + " Not found");
		WebElement el = getDriver().findElement(ByLocator(locator));
		el.clear();
		el.sendKeys(text);
	}
	
	/**
	 * Store text from a locator
	 * 
	 * @param locator
	 * @return
	 */
	public String getText(String locator) {
		String text="";
		try {
			WaitForElementPresent(locator, 8);
			if (isElementPresent(locator)) {
				text = getDriver().findElement(ByLocator(locator)).getText();
			}else{
				test.fail("Element Locator :" + locator + " Not found",
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("locator_notPresent")).build());
				//Assert.assertTrue(isElementPresent(locator), "Element Locator :" + locator + " Not found");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	 * Capture screenshot and return the screenshot path.
	 * 
	 * @param str_screenshotName
	 * @return
	 */
	public String getScreenShotPath_path(String str_screenshotName){
		String locationScreenShot=null;
		String screenShotName = null;
		String screenShotPath=null;
		try {
			TakesScreenshot ts=(TakesScreenshot)getDriver();
			File source=ts.getScreenshotAs(OutputType.FILE);
			screenShotName = str_screenshotName+getCurrentTimeUsingCalendar().replaceAll(":", "_");
			locationScreenShot = System.getProperty("user.dir")+"/Reports/"+screenShotName+".jpg";
			screenShotPath = ""+screenShotName+".jpg";
			File screenshotLocation =new File (locationScreenShot);
			FileUtils.copyFile(source, screenshotLocation);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return screenShotPath;
	}
	
	public String getCurrentTimeUsingCalendar() {
	    Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    String formattedDate=dateFormat.format(date);
	    return formattedDate;
	}
	
	/**
	 * Vertical scrolling till given text is visible.
	 * 
	 * @param element_id
	 * @param str_text_till_scroll
	 */
	public void scrollVertically_tillElement(String element_id, String str_text_till_scroll){
		try {
			MobileElement scroll_element = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\""+element_id+"\")).scrollIntoView("
					+ "new UiSelector().text(\""+str_text_till_scroll+"\"))"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
