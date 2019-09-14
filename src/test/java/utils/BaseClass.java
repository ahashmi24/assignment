package utils;

import java.io.File;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import pageHelper.ScriptsPageHelper;



public abstract class BaseClass extends MyListener {

	
	public AndroidDriver<MobileElement> driver;
	/*public static AppiumDriverLocalService appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/usr/local/Cellar/node/11.1.0/bin/node"))
			//.withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
			.withAppiumJS(new File("/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js"))
			.withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
			.withArgument(Port)
			.withLogFile(new File(System.getProperty("user.dir")+"/AppiumLogs/app.logs")));*/
	public static String appiumServiceUrl;
	public static DesiredCapabilities capabilities;
	public ScriptsPageHelper pageHelper;
	public PropertyReader propertyReader;
	
	
	@BeforeClass
	public void setUp() {
		
		propertyReader = new PropertyReader("application");
		startServer(propertyReader.readApplicationFile("platformName"),propertyReader.readApplicationFile("deviceUDID"), propertyReader.readApplicationFile("version"),propertyReader.readApplicationFile("deviceName"),propertyReader.readApplicationFile("appName"));
		pageHelper = new ScriptsPageHelper(driver);
	}
	
	@AfterClass
	public void afterClass(){
		propertyReader=null;
		pageHelper=null;
		driver.quit();
	}
	
	@AfterTest
	public void afterTest(){
		extent.flush();
		ZipFileCreator.createZipFile();
	}
	
	public void startServer(String platFormName,String deviceUDID,String platformVersion,String deviceName,String appName_with_apk_extension) {
		try {	
			
			/*appiumService = AppiumDriverLocalService.buildDefaultService();
			appiumService.start();
			appiumServiceUrl = appiumService.getUrl().toString();*/
			//System.out.println("Appium Service Address : - "+ appiumServiceUrl);
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/Apps/Android");
			File app = new File (appDir, appName_with_apk_extension);
			        
	//Set Capabilities
			 capabilities = new DesiredCapabilities();
			 capabilities.setCapability("noReset", "false");
			 capabilities.setCapability(MobileCapabilityType.UDID, deviceUDID);
			 capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			 capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			 capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			 capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
			 capabilities.setCapability(AndroidMobileCapabilityType.NATIVE_WEB_SCREENSHOT, Boolean.TRUE);
			 capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
			 capabilities.setCapability("appWaitActivity", "SplashActivity, SplashActivity,OtherActivity, *, *.SplashActivity");
			 capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			 driver=new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
			 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception occurred");
			stopServer();
			e.printStackTrace();
		}
	}
	
	public void stopServer() {
    	//appiumService.stop();
	}
	

}
