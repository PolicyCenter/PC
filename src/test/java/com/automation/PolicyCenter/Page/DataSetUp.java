package com.automation.PolicyCenter.Page;

import java.util.ArrayList;
import java.util.List;


import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.automation.PolicyCenter.Base;
import com.automation.PolicyCenter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class DataSetUp extends Base{

	List<String> components = new ArrayList<String>();

	@Test (enabled =true,priority = 0,groups = {"DMSSETUP"})
	public void addHQUser() {
		try{
			components.clear();
			components.add("login");
			runTestSteps(components);
			                            
		}catch(Throwable e){
			ExtentTestManager.getlogger().log(LogStatus.FAIL, "Test Case Failure", "Failed Due To "+e);
		}
	}



}
