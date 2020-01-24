package com.automation.PolicyCenter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.automation.PolicyCenter.Locators.Locator;
import com.relevantcodes.extentreports.LogStatus;

public class LogFileControl
{
	public static int intErrorCount;
	public static String strErrorCollect;


	static DataProvider dataProvider = new DataProvider();
	static SuppotLibrary suppotLibrary = new SuppotLibrary();
	static Utils utils = new SeleniumUtils();
	
	public static ThreadLocal<String> threadscriptId=new ThreadLocal<String>() ;
	public static String getScriptId_report(){
		return (threadscriptId.get());
	}
	public static void setScriptId_report(String value) {
		threadscriptId.set(new String(value));
	}

	public static Logger log = Logger.getLogger(LogFileControl.class.getName());

	public static void main(String[] args)
	{
		//Configure logger
		BasicConfigurator.configure();
		log.debug("Hello World!");
	}
	
	public static void logInfo(String strMsg)
	{
		log.info(" "+strMsg + " ");
//		ExtentTestManager.getlogger().log(LogStatus.INFO, pageName, strMsg);
	}
	
	public static void logInfo_StopExecution(String strMsg)
	{
		log.info(" "+strMsg + " ");
//		DriverManager.getDriver().quit();
//		ExtentTestManager.getlogger().log(LogStatus.INFO, pageName, strMsg);
	}

	public static void logInfo(String pageName, String strMsg)
	{
		log.info(" " + pageName+ " "+strMsg + " ");
		ExtentTestManager.getlogger().log(LogStatus.INFO, pageName, strMsg);
	}
	
	public static void logError(String strMsg)
	{
		log.error( strMsg);
		ExtentTestManager.getlogger().log(LogStatus.ERROR, strMsg);
	}

	public static void logPass(String stepName, String strMsg)
	{
		log.info(" " + stepName+ " "+strMsg + " ");
		ExtentTestManager.getlogger().log(LogStatus.PASS, stepName, strMsg);
	}

	public static void logFail(String stepName, String msg)
	{
		String strMsg =  stepName+ " "+msg ;
		Boolean blnConsoleError = StringUtils.containsIgnoreCase(strMsg, "Console");

		intErrorCount = intErrorCount + 1;
		strErrorCollect = strErrorCollect + strMsg + " \n";

		if (blnConsoleError.equals(true))
		{
			log.info(strMsg + " ~ Termination Stop ");
			log.info("Termination Console Error detected : " + strMsg);

			log.error("Error FOUND: \n" + strMsg);
			ExtentTestManager.getlogger().log(LogStatus.FAIL, stepName, strMsg);
			System.exit(1);
		}
		else
		{	
			ExtentTestManager.getlogger().log(LogStatus.FAIL, stepName, strMsg);
			log.info(strMsg + " ~ Run Stop ");
			log.error("Error FOUND: \n" + strMsg);

			Assert.fail(strMsg);
		}

		//		try{
		//			DataAddress.driver.quit();
		//		}catch(Exception e){}
	}

	public static void logFailwithScreenCapture(String stepName, String msg) throws IOException
	{
		String strMsg =  stepName+ " "+msg ;
		Boolean blnConsoleError = StringUtils.containsIgnoreCase(strMsg, "Console");

		intErrorCount = intErrorCount + 1;
		strErrorCollect = strErrorCollect + strMsg + " \n";

		if (blnConsoleError.equals(true))
		{
			log.info(strMsg + " ~ Termination Stop ");
			log.info("Termination Console Error detected : " + strMsg);

			log.error("Error FOUND: \n" + strMsg);
			ExtentTestManager.getlogger().log(LogStatus.FAIL, stepName, strMsg +ExtentTestManager.getlogger().addScreenCapture(suppotLibrary.screenCapture(DriverManager.getDriver(),getScriptId_report())));
			utils.Click(Locator.ID, "btnLogout", "Home Page", "Log Out"); 
			DriverManager.getWait().until(ExpectedConditions.alertIsPresent());
			DriverManager.getDriver().switchTo().alert().accept();
			System.exit(1);
		}
		else
		{	
			log.info(strMsg + " ~ Run Stop ");
			log.error("Error FOUND: \n" + strMsg);
			ExtentTestManager.getlogger().log(LogStatus.FAIL, stepName, strMsg +ExtentTestManager.getlogger().addScreenCapture(suppotLibrary.screenCapture(DriverManager.getDriver(),getScriptId_report())));
			Assert.fail(strMsg);
		}
	}
}
