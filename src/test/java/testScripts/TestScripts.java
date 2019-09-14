package testScripts;

import org.testng.annotations.Test;

import pageHelper.ScriptsPageHelper;
import utils.BaseClass;

public class TestScripts extends BaseClass{

	
	@Test
	public void loginToApplication(){
		try {
			
			pageHelper.tapOnButton_byName(propertyReader.readApplicationFile("signInButton"));
			
			pageHelper.enterPhoneNumber(propertyReader.readApplicationFile("phoneNumber"));
			
			pageHelper.tapOnButton_byName(propertyReader.readApplicationFile("continueButton"));
			
			pageHelper.unCheck_showPasswordCheckBox();
			
			pageHelper.enterPassword(propertyReader.readApplicationFile("password"));
			
			pageHelper.tapOnButton_byName(propertyReader.readApplicationFile("loginButton"));
			
			pageHelper.verifyHomeScreen();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void changeCountry(){
		try {
			pageHelper.tapOnHamburgerIcon();
			
			pageHelper.tapOnHamburgerList_byName(propertyReader.readApplicationFile("hamburgerLink_L1"));
			
			pageHelper.tapOnHamburgerList_byName(propertyReader.readApplicationFile("hamburgerLink_L2"));
			
			pageHelper.tapOnButton_byName(propertyReader.readApplicationFile("buttonName_1"));
			
			pageHelper.tapOnCountryLink_byName(propertyReader.readApplicationFile("country"));
			
			pageHelper.tapOnButton_byName(propertyReader.readApplicationFile("buttonName_2"));
			
			pageHelper.verifyHomeScreen();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void searchForAnItem(){
		try {
			pageHelper.enterSearchKeywordInToSearchField(propertyReader.readApplicationFile("searchKeyword"));
			
			pageHelper.selectDropList(propertyReader.readApplicationFile("selectItem"));
			
			pageHelper.verifySearchResultScreen();
			
			pageHelper.selectItem_ByBrandName(propertyReader.readApplicationFile("tvBrand"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void addToCart(){
		try {
			pageHelper.verifyPDP();
			
			pageHelper.tapOnButton_byName(propertyReader.readApplicationFile("PDPButton"));
			
			pageHelper.tapOnButton_byName(propertyReader.readApplicationFile("AddToCartButton"));
			
			pageHelper.tapOnCartIcon();
			
			pageHelper.verifyItemIntoCart(ScriptsPageHelper.added_item_name);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
