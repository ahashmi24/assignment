package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.remote.adapter.RemoteResultListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListener implements ITestListener {

	protected static ExtentReports extent;
	protected static ExtentTest test;
	
	@Override
	public void onFinish(ITestContext arg0) {
		//ExtentManager.GetExtent().flush();
		
	}

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentManager.GetExtent();
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		//test.log(Status.FAIL, arg0.getMethod().getMethodName()+" is failed");
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		//test.log(Status.SKIP, arg0.getMethod().getMethodName()+" is Skipped");
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		//test = ExtentManager.createTest(result.getMethod().getMethodName(), "test is started");
		test = ExtentManager.createTest("<b>"+result.getMethod().getMethodName()+"</b>", "<b>"+result.getMethod().getMethodName()+"</b>"+" test is started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test = test.log(Status.PASS, result.getMethod().getMethodName()+" is passed");
		
	}

}
