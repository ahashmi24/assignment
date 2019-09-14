package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
	
	private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter;
    private static String filePath = "./Reports/AmazonAutomationTestReport"+getCurrentTime()+".html";
	
	public static ExtentReports GetExtent(){
        if (extent != null)
                   return extent; 
               extent = new ExtentReports();        
        extent.attachReporter(getHtmlReporter());
        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
               htmlReporter = new ExtentHtmlReporter(filePath);
               htmlReporter.loadConfig("./extent-config.xml");
               htmlReporter.config().setChartVisibilityOnOpen(true);
               htmlReporter.config().setDocumentTitle("Amazon Mobile Test Automation Report");
               htmlReporter.config().setReportName("Amazon Happy Path");
               extent.setSystemInfo("Environment", "stage");
               extent.setSystemInfo("User Name", "Ahmer Hashmi");
               return htmlReporter;
    }
    
    public static ExtentTest createTest(String name, String description){
        test = extent.createTest(name, description);
        return test;
    }
    
    public static String getCurrentTime() {
	    Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    String formattedDate=dateFormat.format(date);
	    return formattedDate;
	}

}
