package pageHelper;



import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import locators.LocatorReader;
import utils.CommonFunctions;

public class ScriptsPageHelper extends CommonFunctions{
	
	private LocatorReader elementLocator;
	public static String added_item_name="";
	
	public ScriptsPageHelper(AndroidDriver<MobileElement> driver) {
		super(driver);
		elementLocator= new LocatorReader("ElementsLocator.xml");
	}
	
	/**
	 * Generic function to tap on button by button name.
	 * 
	 * @param str_buttonName
	 * @throws IOException
	 */
	public void tapOnButton_byName(String str_buttonName) throws IOException{
		try {
				waitForElementsToVisible(elementLocator.getLocator("homeScreen.countryButton_list"), 50);
				
				if (isElementPresent(elementLocator.getLocator("homeScreen.countryButton_list"))) {
					
					if (str_buttonName.equalsIgnoreCase("Done")) {
						test.pass("'Country' gets selected.", 
								MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("countrySelected")).build());
					}
					
					List<MobileElement> button_List = getDriver().findElements(ByLocator(elementLocator.getLocator("homeScreen.countryButton_list")));
					for (MobileElement ele_button : button_List) {
						if (ele_button.getText().trim().contains(str_buttonName)) {
							ele_button.click();
							test.pass("Tap On '"+str_buttonName+"' button");
							break;
						}
					}
					
					
				} else {
					test.fail("'"+str_buttonName+"' button is not present.",
							MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("countryButtonNotPresent")).build());
					Assert.assertTrue(isElementPresent(elementLocator.getLocator("homeScreen.countryButton_list")), "'Country' button is not present.");
				}
				
			} catch (Exception e) {
				test.fail("Unable to tap on 'country' button due to error: "+e.getMessage(), 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
				Assert.assertTrue(false);
			}
		} 
	
	/**
	 * Generic step function to enter phone number into field.
	 * 
	 * @param str_phoneNumber
	 * @throws IOException
	 */
	public void enterPhoneNumber(String str_phoneNumber) throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("launcherScreens.emailField"), 50);
			
			if (isElementPresent(elementLocator.getLocator("launcherScreens.emailField"))) {
				
				test.pass("'Phone Number' field is present.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("emailField")).build());
				
				sendKeys(elementLocator.getLocator("launcherScreens.emailField"),str_phoneNumber);
				test.pass(str_phoneNumber+" phone number entered.");
				
			} else {
				test.fail("'Phone Number' field is not present.",
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("signInButtonNotPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("launcherScreens.emailField")), "'Phone Number' field is not present.");
			}
			
		} catch (Exception e) {
			test.fail("Unable to enter 'Phone' number due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	
	/**
	 * Function to un-check show password check box.
	 * 
	 * @throws IOException
	 */
	public void unCheck_showPasswordCheckBox() throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("launcherScreens.showPasswordCheckBox"), 50);
			
			if (isElementPresent(elementLocator.getLocator("launcherScreens.showPasswordCheckBox"))) {
				
				test.pass("'Show Password' checkbox is present.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("checkBox")).build());
				
				clickOn(elementLocator.getLocator("launcherScreens.showPasswordCheckBox"));
				test.pass("Click On 'Show Password' checkbox.");
				
			} else {
				test.fail("'Show Password' checkbox is not present.",
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("checkBoxNotPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("launcherScreens.showPasswordCheckBox")), "'Show Password' checkbox is not present.");
			}
			
		} catch (Exception e) {
			test.fail("Unable to click on 'check box' due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Generic function to enter password into field.
	 * 
	 * @param str_password
	 * @throws IOException
	 */
	public void enterPassword(String str_password) throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("launcherScreens.passwordField"), 50);
			
			if (isElementPresent(elementLocator.getLocator("launcherScreens.passwordField"))) {
				
				test.pass("'Password' field is present.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("password")).build());
				
				sendKeys(elementLocator.getLocator("launcherScreens.passwordField"),str_password);
				test.pass(str_password+" as password entered.");
				
			} else {
				test.fail("'Password' field is not present.",
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("passwordFieldNotPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("launcherScreens.passwordField")), "'Password' field is not present.");
			}
			
		} catch (Exception e) {
			test.fail("Unable to enter 'Password' due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Function to verify the 'Home Screen' navigation.
	 * 
	 * @throws IOException
	 */
	public void verifyHomeScreen() throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("homeScreen.hamburgerIcon"), 40);
			
			if (isElementPresent(elementLocator.getLocator("homeScreen.hamburgerIcon"))) {
				
				test.pass("Successfully navigated to 'Home' screen.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("homeScreen")).build());
				
			} else {
				test.fail("Unable to navigate 'Home' screen.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("homeScreen_notPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("homeScreen.hamburgerIcon")), "Unable to navigate 'Home' screen.");
			}
		} catch (Exception e) {
			test.fail("Unable to verify 'Home' screen due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Generic step function to tap on hamburger icon on all screens.
	 * 
	 * @throws IOException
	 */
	public void tapOnHamburgerIcon() throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("homeScreen.hamburgerIcon"), 40);
			
			if (isElementPresent(elementLocator.getLocator("homeScreen.hamburgerIcon"))) {
				
				clickOn(elementLocator.getLocator("homeScreen.hamburgerIcon"));
				test.pass("Tap on 'Hamburger' icon.");
				
			} else {
				test.fail("'Hamburger' icon is not present.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("hamburger_notPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("homeScreen.hamburgerIcon")), "'Hamburger' icon is not present.");
			}
		} catch (Exception e) {
			test.fail("Unable to tap on 'Hamburger' icon due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Generic function to tap on Hamburger link by link name.
	 * 
	 * @param str_linkName
	 * @throws IOException
	 */
	public void tapOnHamburgerList_byName(String str_linkName) throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("homeScreen.hamburgerList"), 40);
			
			if (isElementPresent(elementLocator.getLocator("homeScreen.hamburgerList"))) {
				
				List<MobileElement> hamburger_list = getDriver().findElements(ByLocator(elementLocator.getLocator("homeScreen.hamburgerList")));
				for (MobileElement ele_link : hamburger_list) {
					if (ele_link.getText().trim().contains(str_linkName)) {
						ele_link.click();
						test.pass("Tap on '"+str_linkName+"' link.");
						break;
					}
				}
				
			} else {
				test.fail("'Hamburger lists' are not present.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("settings_notPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("homeScreen.hamburgerList")), "'Hamburger' icon is not present.");
			}
		} catch (Exception e) {
			test.fail("Unable to Tap on '"+str_linkName+"' link due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	
	/**
	 * Generic function to select country by country name.
	 * 
	 * @param str_countryName
	 * @throws IOException
	 */
	public void tapOnCountryLink_byName(String str_countryName) throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("homeScreen.country_list"), 40);
			
			if (isElementPresent(elementLocator.getLocator("homeScreen.country_list"))) {
				
				List<MobileElement> hamburger_list = getDriver().findElements(ByLocator(elementLocator.getLocator("homeScreen.country_list")));
				for (MobileElement ele_link : hamburger_list) {
					if (ele_link.getText().trim().contains(str_countryName)) {
						ele_link.click();
						test.pass("Tap on '"+str_countryName+"' link.");
						break;
					}
				}
				
			} else {
				test.fail("'Hamburger lists' are not present.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("settings_notPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("homeScreen.hamburgerIcon")), "'Hamburger' icon is not present.");
			}
		} catch (Exception e) {
			test.fail("Unable to Tap on '"+str_countryName+"' link due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Generic function to enter search keyword.
	 * 
	 * @param str_keyword
	 * @throws IOException
	 */
	public void enterSearchKeywordInToSearchField(String str_keyword) throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("homeScreen.searchField"), 50);
			
			if (isElementPresent(elementLocator.getLocator("homeScreen.searchField"))) {
				
				test.pass("'Search' field is present.");
				clickOn(elementLocator.getLocator("homeScreen.searchField"));
				sendKeys(elementLocator.getLocator("homeScreen.searchField"),str_keyword);
				test.pass(str_keyword+" entered as search keyword.",
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("search")).build());
				
			} else {
				test.fail("'Search' field is not present.",
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("searchFieldNotPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("homeScreen.searchField")), "'Search' field is not present.");
			}
			
		} catch (Exception e) {
			test.fail("Unable to enter 'Search Keyword' due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Function to select auto drop list.
	 * 
	 * @param str_option
	 * @throws IOException
	 */
	public void selectDropList(String str_option) throws IOException{
		try {
			
			Thread.sleep(1500);
			waitForElementsToVisible(elementLocator.getLocator("homeScreen.autoDropList"), 40);
			
			if (isElementPresent(elementLocator.getLocator("homeScreen.autoDropList"))) {
				
				List<MobileElement> hamburger_list = getDriver().findElements(ByLocator(elementLocator.getLocator("homeScreen.autoDropList")));
				hamburger_list.get(8).click();
				test.pass("Tap on 'Auto drop down' list.");
				
			} else {
				test.fail("'Auto drop lists' are not present.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("settings_notPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("homeScreen.autoDropList")), "'Auto drop lists' are not present.");
			}
			
		} catch (Exception e) {
			test.fail("Unable to Tap on '"+str_option+"' link due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Function to verify 'Search Result Screen' navigation.
	 * 
	 * @throws IOException
	 */
	public void verifySearchResultScreen() throws IOException{
		try {
			waitForElementsToVisible(elementLocator.getLocator("searchResultScreen.productList_1")+"7"+elementLocator.getLocator("searchResultScreen.productList_2"), 60);
			
			if (isElementPresent(elementLocator.getLocator("searchResultScreen.productList_1")+"7"+elementLocator.getLocator("searchResultScreen.productList_2"))) {
				
				test.pass("Successfully navigated to 'Search Result' screen.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("searchResult")).build());
				
			} else {
				test.fail("Unable to navigate 'Search Result' screen.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("search_notPresent")).build());
				Assert.assertTrue(isElementPresent(elementLocator.getLocator("searchResultScreen.productList_1")+"7"+elementLocator.getLocator("searchResultScreen.productList_2")), "Unable to navigate 'Search Result' screen.");
			}
		} catch (Exception e) {
			test.fail("Unable to verify 'Search Result' screen due to error: "+e.getMessage(), 
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("error_occurred")).build());
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Generic function to select item by brand name.
	 * 
	 * @param str_brandName
	 */
	public void selectItem_ByBrandName(String str_brandName){
		try {
			waitForElementsToVisible(elementLocator.getLocator("searchResultScreen.productList_1")+"7"+elementLocator.getLocator("searchResultScreen.productList_2"), 40);
			
			int index=7;
			String itemName="";
			for (int i = 0; i < index; i++) {
				itemName="";
				if (isElementPresent(elementLocator.getLocator("searchResultScreen.productList_1")+index+elementLocator.getLocator("searchResultScreen.productList_2"))){
					itemName=getText(elementLocator.getLocator("searchResultScreen.productList_1")+index+elementLocator.getLocator("searchResultScreen.productList_2"));
					if (itemName.contains(str_brandName)) {
						added_item_name=itemName;
						clickOn(elementLocator.getLocator("searchResultScreen.productList_1")+index+elementLocator.getLocator("searchResultScreen.productList_2"));
						test.pass("Tap on item '"+added_item_name+"' from the product list.");
						break;
						
					}
				} else {

				}
				
				index=index+5;
			}
			System.err.println("Added Item: "+added_item_name);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Function to verify 'Product display' screen navigation.
	 * 
	 */
	public void verifyPDP(){
		try {
			waitForElementsToVisible(elementLocator.getLocator("PDP.byingOption"), 40);
			
			if (isElementPresent(elementLocator.getLocator("PDP.byingOption"))) {
				
				test.pass("Successfully landed on PDP.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("PDP")).build());
				
			} else {
				test.fail("Unable to navigate PDP.", 
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("PDP_failed")).build());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Function to tap on cart icon at top.
	 * 
	 */
	public void tapOnCartIcon(){
		try {
			waitForElementsToVisible(elementLocator.getLocator("PDP.cartIcon"), 20);
			
			if (isElementPresent(elementLocator.getLocator("PDP.cartIcon"))) {
				clickOn(elementLocator.getLocator("PDP.cartIcon"));
				test.pass("Click on 'Cart Icon'");
			} else {
				test.fail("Unable to tap on 'Cart Icon'",
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("cartIcon_notPresent")).build());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * Generic function to verify added item at cart screen.
	 * 
	 * @param str_itemName
	 */
	public void verifyItemIntoCart(String str_itemName){
		try {
			waitForElementsToVisible(elementLocator.getLocator("homeScreen.countryButton_list"), 40);
			
			if (isElementPresent(elementLocator.getLocator("homeScreen.countryButton_list"))) {
				
				
				List<MobileElement> list_element = getDriver().findElements(ByLocator(elementLocator.getLocator("cartScreen.parentDiv"))).get(3).
						findElements(ByLocator(elementLocator.getLocator("cartScreen.childElement")));
				System.err.println("Size: "+list_element.size());
				for (MobileElement ele_item : list_element) {
					System.err.println("Text:>>  "+ele_item.getText().trim());
					if (str_itemName.contains(ele_item.getText().trim())) {
						test.pass("Same item is added into cart.",
								MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("cartItem")).build());
						break;
					}
				}
				
				
			} else {
				test.fail("Unable to navigate cart screen.",
						MediaEntityBuilder.createScreenCaptureFromPath(getScreenShotPath_path("cartItem")).build());
				Assert.assertTrue(false);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
