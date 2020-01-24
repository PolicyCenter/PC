package com.automation.PolicyCenter;


import org.testng.ITestContext;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	public static ExtentReports extent;
	@SuppressWarnings("unused")
	private static ITestContext context;
	static SuppotLibrary suppotLibrary = new SuppotLibrary();

	public synchronized static ExtentReports getInstance() {
		if (extent == null) {
//			File outputDirectory = new File(context.getOutputDirectory());
//			File resultDirectory = new File(outputDirectory.getParentFile(), "html");
			String reportPath=suppotLibrary.getReportPath();
			extent = new ExtentReports(reportPath,false,suppotLibrary.getnetWorkmode());
			// extent = new ExtentReports(resultDirectory + File.separator +  "Report.html", true);
			//            Reporter.log("Extent Report directory: " + resultDirectory, true);
		}

		return extent;
	}

	public static void setOutputDirectory(ITestContext context) {
		ExtentManager.context = context;
	}
}
