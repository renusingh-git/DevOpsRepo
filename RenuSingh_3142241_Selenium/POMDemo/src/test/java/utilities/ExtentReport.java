package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport extends BaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("on test start");
		test = report.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName() + " test is started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("on test success");
		test.log(LogStatus.PASS, result.getMethod().getMethodName() + " test is passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("on test failure");
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + " test is failed");
		// test.log(LogStatus.INFO, "FAIL",
		// test.addScreenCapture(BaseClass.failedSnapshotFolderPath));
		test.log(LogStatus.FAIL, "Test Result-FAILED");
//		String screenshotPath;
//		try {
//			screenshotPath = ScreenShots.getScreenhot(driver, result.getName());
//			// To add it in the extent report
//			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		extent.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("on test skipped");
		test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "  test is skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("on test sucess within percentage");
	}

	@SuppressWarnings("unused")
	public void onStart(ITestContext context) {
		String dateTimestamp = new SimpleDateFormat("dd-MMM-YYYY_hh.mm.ss").format(new Date());
		System.out.println("on start");
		report = new ExtentReports("C:\\Users\\renu2143\\Desktop\\SeleniumWorkspace\\POMDemo\\extent-reports\\"
				+ "reports_" + dateTimestamp + ".html");
	}

	public void onFinish(ITestContext context) {
		System.out.println("on finish");
		report.endTest(test);
		report.flush();
	}
}
