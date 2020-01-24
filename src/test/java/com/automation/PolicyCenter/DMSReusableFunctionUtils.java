package com.automation.PolicyCenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.PolicyCenter.Locators.Locator;
import com.automation.PolicyCenter.Locators.Selector;
import com.relevantcodes.extentreports.LogStatus;

public class DMSReusableFunctionUtils extends Base implements DMSUtils{


	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#actionAlert(java.lang.String)
	 */
	@Override
	public  void actionAlert(String action){
		try{
			//WebDriver driver = DriverFactory.getInstance().getDriver();
			DriverManager.getWait().until(ExpectedConditions.alertIsPresent());
			if (action.equalsIgnoreCase("accept")){
				DriverManager.getDriver().switchTo().alert().accept();
				LogFileControl.logInfo("Alert accepted", "");
				//				ExtentTestManager.getlogger().log(LogStatus.INFO, "Alert accepted");
			}
			if (action.equalsIgnoreCase("dismiss")){
				DriverManager.getDriver().switchTo().alert().dismiss();
				LogFileControl.logInfo("Alert dismissed", "");
				//				ExtentTestManager.getlogger().log(LogStatus.INFO, "Alert dismissed");
			}

		}catch(Exception e){}
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#getAlertmessage(java.lang.String)
	 */
	@Override
	public String getAlertmessage(String pageName){
		String text=null;
		//WebDriver driver = DriverFactory.getInstance().getDriver();
		try{
			DriverManager.getWait().until(ExpectedConditions.alertIsPresent());
			text=DriverManager.getDriver().switchTo().alert().getText();
			DriverManager.getDriver().switchTo().alert().accept();
			LogFileControl.logInfo(pageName,getLogMessage("Alert Message")+text);

		}catch(Exception e){
			try{
				if(text ==null || text.length()==0 || text.contains("")){
					DriverManager.getWait().until(ExpectedConditions.alertIsPresent());
					text=DriverManager.getDriver().switchTo().alert().getText();
					DriverManager.getDriver().switchTo().alert().accept();
				}
			}catch(NoAlertPresentException e1){}
		}catch(Throwable e1){
			try{
				if(text ==null || text.length()==0 || text.contains("")){
					DriverManager.getWait().until(ExpectedConditions.alertIsPresent());
					text=DriverManager.getDriver().switchTo().alert().getText();
					DriverManager.getDriver().switchTo().alert().accept();
				}
			}catch(NoAlertPresentException e){

			}
		}
		return text;

	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#getDate(java.lang.String, int)
	 */
	@Override
	public String getDate(String format,int day){
		String output = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); 
			c.add(Calendar.DATE, day); 
			output = sdf.format(c.getTime());
			System.out.println(output);

		}catch(Exception e){

		}
		return output;

	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#getDate(int)
	 */
	@Override
	public String getDate(int day){
		String output = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); 
			c.add(Calendar.DATE, day); 
			output = sdf.format(c.getTime());
			System.out.println(output);

		}catch(Exception e){

		}
		return output;

	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#verifyDate(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void verifyDate( String actualDate,String expectedDate, String pageName){
		try{
			if(expectedDate.equals(actualDate)){
				LogFileControl.logPass(pageName, getLogMessage("Date Pass")+actualDate);
				//				ExtentTestManager.getlogger().log(LogStatus.PASS, pageName, "Date is displaying correctly as "+actualDate);
			}else{
				LogFileControl.logPass(pageName, getLogMessage("Date Fail")+actualDate);
				//				ExtentTestManager.getlogger().log(LogStatus.FAIL, pageName, "Date is not displaying correctly as "+actualDate);
			}
		}catch (Exception e){}
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#verifyText(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void verifyText( String actualText,String expectedText, String pageName){
		try{
			if(actualText.trim().equals(expectedText.trim())){
				LogFileControl.logPass(pageName, getLogMessage("Text Pass")+expectedText);
				//				ExtentTestManager.getlogger().log(LogStatus.PASS, pageName, "Text is displaying correctly as "+expectedText);
			}else{
				LogFileControl.logFailwithScreenCapture(pageName, getLogMessage("Text Fail")+expectedText);
				//				ExtentTestManager.getlogger().log(LogStatus.FAIL, pageName, "Text is not displaying correctly as "+expectedText+ExtentTestManager.getlogger().addScreenCapture(suppotLibrary.screenCapture(DriverManager.getDriver(),getScriptId())) );
			}
		}catch(Exception e){}
	} 

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#verifyList(java.util.List, java.util.List, java.lang.String)
	 */
	@Override
	public void verifyList(List<String> actual, List<String> expected,String pageName){
		try{
			List<String> sourceList = new ArrayList<String>(actual);
			List<String> destinationList = new ArrayList<String>(expected);
			if(Math.abs(actual.size()-expected.size())<=1){
			sourceList.removeAll( expected );
			destinationList.removeAll( actual );
			if(sourceList.size()==0 && destinationList.size()==0){
				LogFileControl.logPass(pageName, getLogMessage("List Pass")+expected);
				//				ExtentTestManager.getlogger().log(LogStatus.PASS, pageName, "List of Text is displaying correctly as "+expected);
			}else{
				if(sourceList.size()==1 || destinationList.size()==1){
					if(sourceList.get(0).contentEquals("") || actual.get(0).contentEquals("")){
						LogFileControl.logPass(pageName, getLogMessage("List Pass")+expected);
						//						ExtentTestManager.getlogger().log(LogStatus.PASS, pageName, "List of Text is displaying correctly as "+expected);
					}
				}else{
					LogFileControl.logFail(pageName, getLogMessage("List Fail")+expected);
					//					ExtentTestManager.getlogger().log(LogStatus.FAIL, pageName, "List of Text is not displaying correctly as "+expected);
				}
			}
		}else{
			LogFileControl.logFail(pageName, getLogMessage("List Fail")+expected);
			//					ExtentTestManager.getlogger().log(LogStatus.FAIL, pageName, "List of Text is not displaying correctly as "+expected);
		}
		}catch(Exception e){}
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#isTextBox(com.Automation.NewsPage.Locators.Locator, java.lang.String, java.lang.String)
	 */
	@Override
	public void isTextBox(Locator locatorType, String locatorValue, String labelName){
		try{
			String tagName=seleniumUtils.GetTagName(locatorType, locatorValue);
			String type = seleniumUtils.GetAttribute(locatorType, locatorValue, "type");
			if(tagName.equals("input")&& type.contentEquals("text")){
				LogFileControl.logPass(labelName, labelName+" "+getLogMessage("Textbox Pass"));
				//				ExtentTestManager.getlogger().log(LogStatus.PASS, labelName, labelName+" is displayed as Text Box");
			}else{
				LogFileControl.logFail(labelName, labelName+" "+getLogMessage("Textbox Fail"));
				//				ExtentTestManager.getlogger().log(LogStatus.FAIL, labelName, labelName+" is not displayed as Text Box");
			}
		}catch(Exception e){}
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#isDropDown(com.Automation.NewsPage.Locators.Locator, java.lang.String, java.lang.String)
	 */
	@Override
	public void isDropDown(Locator locatorType, String locatorValue, String labelName){
		try{
			String tagName=seleniumUtils.GetTagName(locatorType, locatorValue);
			if(tagName.equals("select")){
				LogFileControl.logPass(labelName, labelName+" "+getLogMessage("Dropdown Pass"));
			}else{
				LogFileControl.logFail(labelName, labelName+" "+getLogMessage("Dropdown Fail"));
			}
		}catch(Exception e){}
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#isDisabled(com.Automation.NewsPage.Locators.Locator, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isDisabled(Locator locatorType, String locatorValue, String labelName){
		boolean flag = false;
		try{
			String classStatus = null,otherStatus = null;
			try{
				classStatus = seleniumUtils.GetAttribute(locatorType, locatorValue, "class").toLowerCase();
			}catch(Exception e){}
			try{
				otherStatus = seleniumUtils.GetAttribute(locatorType, locatorValue, "disabled").toLowerCase();
			}catch(Exception e){}
			if(classStatus.contains("disable") || otherStatus.contains("disable")||otherStatus.contains("true")){
				LogFileControl.logPass(labelName, labelName+" "+getLogMessage("Disabled Pass"));
				//                      ExtentTestManager.getlogger().log(LogStatus.PASS, labelName, labelName+" is disable");
			}else{
				LogFileControl.logFail(labelName, labelName+" "+getLogMessage("Disabled Fail"));
				//                      ExtentTestManager.getlogger().log(LogStatus.FAIL, labelName, labelName+" is not disable");
			}
		}catch(Exception e){}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#verifyWebElement(boolean, boolean, java.lang.String)
	 */
	@Override
	public void verifyWebElement(boolean actualStatus, boolean expectedStatus, String labelName){
		try{
			if(actualStatus==expectedStatus){
				LogFileControl.logPass(labelName, labelName+getLogMessage("WebElement Pass"));
				//				ExtentTestManager.getlogger().log(LogStatus.PASS, labelName, labelName+" Element presence is as expected");
			}else{
				LogFileControl.logFail(labelName, labelName+getLogMessage("WebElement Fail"));
				//				ExtentTestManager.getlogger().log(LogStatus.FAIL, labelName, labelName+" Element presence is not as expected");
			}
		}catch(Exception e){}
	}


	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#doHide(com.Automation.NewsPage.Locators.Locator, java.lang.String, java.lang.String)
	 */
	@Override
	public void doHide(Locator locatorType, String locatorValue, String labelName){
		try{
			if(seleniumUtils.Size(locatorType, locatorValue)>0){
				LogFileControl.logFail(labelName, labelName+" "+getLogMessage("Disappear Fail"));
				//				ExtentTestManager.getlogger().log(LogStatus.FAIL, labelName, labelName+" is not Disappear");
			}else{
				LogFileControl.logPass(labelName, labelName+" "+getLogMessage("Disappear Pass"));
				//				ExtentTestManager.getlogger().log(LogStatus.PASS, labelName, labelName+" is Disappeared");
			}
		}catch(Exception e){}
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#selectDate_DatePicker(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void selectDate_DatePicker(String labelName,String dateType,String pageName){
		try{
			seleniumUtils.Click(Locator.XPATH, "//*[text()='"+labelName+"']/following::input[contains(@src,'DatePicker')][1]", pageName, labelName+" Calendar");
			seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//*[text()='"+labelName+"']/following::div[contains(@id,'CalendarExtender_nextArrow')][1]");
			if(dateType.equalsIgnoreCase("Future")){
				seleniumUtils.Click(Locator.XPATH, "//*[text()='"+labelName+"']/following::div[contains(@id,'CalendarExtender_nextArrow')][1]", pageName, "Next Month Arrow");
			}
			if(dateType.equalsIgnoreCase("Past")){
				seleniumUtils.Click(Locator.XPATH, "//*[text()='"+labelName+"']/following::div[contains(@id,'CalendarExtender_prevArrow')][1]", pageName, "Previous Month Arrow");
			}
			seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[text()='24' and @class='ajax__npcalendar_day']");
			seleniumUtils.Click(Locator.XPATH, "//div[text()='24' and @class='ajax__npcalendar_day']", "Calendar", "24");
			Thread.sleep(500);
		}catch(Exception e){}

	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#verifyGrid_Sorting(java.lang.String, java.lang.String)
	 */
	@Override
	public void verifyGrid_Sorting(String tableName,String sortingType){
		try{
			int totalcolcount = seleniumUtils.Size(Locator.XPATH, "//tr[@class='GridView_HeaderStyle']/th");
			int columnCount = seleniumUtils.Size(Locator.XPATH, "//tr[@class='GridView_HeaderStyle']/th/table/tbody/tr[1]/td/a");
			int unUsedCount =0;
			if(totalcolcount>columnCount)
				unUsedCount = totalcolcount-columnCount;
			for(int s=1;s<=columnCount;s++){
				try{
					seleniumUtils.MoveToElement(Locator.XPATH, "(//tr[@class='GridView_HeaderStyle']/th/table/tbody/tr[1]/td/a)["+s+"]");
					String cName=seleniumUtils.GetText(Locator.XPATH, "(//tr[@class='GridView_HeaderStyle']/th/table/tbody/tr[1]/td/a)["+s+"]");
					try{
						seleniumUtils.waitForPageLoad();
						seleniumUtils.Click(Locator.XPATH, "(//tr[@class='GridView_HeaderStyle']/th/table/tbody/tr[1]/td/a)["+s+"]", tableName, cName);
					}catch(Exception e){
						seleniumUtils.waitForPageLoad();
						seleniumUtils.Click(Locator.XPATH, "(//tr[@class='GridView_HeaderStyle']/th/table/tbody/tr[1]/td/a)["+s+"]", tableName, cName);
					}
					seleniumUtils.waitForPageLoad();
					String oType=seleniumUtils.GetAttribute(Locator.XPATH, "//tr[@class='GridView_HeaderStyle']/th/table/tbody/tr[1]/td/img", "src");
					//Verifying that desire sorting type and sorted in UI is same or not if not then again click on column name
					if(!oType.contains(sortingType)){
						try{
							seleniumUtils.Click(Locator.XPATH, "(//tr[@class='GridView_HeaderStyle']/th/table/tbody/tr[1]/td/a)["+s+"]", tableName, cName);
							seleniumUtils.waitForPageLoad();
						}catch(Exception e){
							seleniumUtils.waitForPageLoad();
							seleniumUtils.Click(Locator.XPATH, "(//tr[@class='GridView_HeaderStyle']/th/table/tbody/tr[1]/td/a)["+s+"]", tableName, cName);
						}
					}
					int tableSize=seleniumUtils.Size(Locator.XPATH, "//table[@class='GridView']/tbody/tr");
					int a=0;
					String xpath = null;
					if(seleniumUtils.Size(Locator.XPATH, "//table[@class='GridView']/tbody/tr[2]/td["+(s+unUsedCount)+"]/span")>0){
						xpath = "span";
					}else{
						if(seleniumUtils.Size(Locator.XPATH, "//table[@class='GridView']/tbody/tr[2]/td["+(s+unUsedCount)+"]/a")>0){
							xpath = "a";
						}else{
							if(seleniumUtils.Size(Locator.XPATH, "//table[@class='GridView']/tbody/tr[3]/td["+(s+unUsedCount)+"]/table/tbody/tr/td/table/tbody/tr/td/span")>0){
								xpath = "/table/tbody/tr/td/table/tbody/tr/td/span";
							}else{
								if(seleniumUtils.Size(Locator.XPATH, "//table[@class='GridView']/tbody/tr[2]/td["+(s+unUsedCount)+"]/table/tbody/tr/td/table/tbody/tr/td/a")>0){
									xpath = "/table/tbody/tr/td/table/tbody/tr/td/a";
								}
							}
						}
					}
					String currentText = null,nextText = null;
					for(int i=2;i<tableSize;i++){

						try{
							//storing the n th and n+1 th value of the particular column and checking n th and n+1 th value is in order or not 
							if(xpath.contentEquals("span")){
								currentText=seleniumUtils.GetText(Locator.XPATH, "//table[@class='GridView']/tbody/tr["+i+"]/td["+(s+unUsedCount)+"]/span");
								nextText=seleniumUtils.GetText(Locator.XPATH, "//table[@class='GridView']/tbody/tr["+(i+1)+"]/td["+(s+unUsedCount)+"]/span");
							}else{
								if(xpath.contentEquals("a")){
									currentText=seleniumUtils.GetText(Locator.XPATH, "//table[@class='GridView']/tbody/tr["+i+"]/td["+(s+unUsedCount)+"]/a");
									nextText=seleniumUtils.GetText(Locator.XPATH, "//table[@class='GridView']/tbody/tr["+(i+1)+"]/td["+(s+unUsedCount)+"]/a");
								}else{
									if(xpath.contentEquals("/table/tbody/tr/td/table/tbody/tr/td/span")){
										currentText=seleniumUtils.GetText(Locator.XPATH, "//table[@class='GridView']/tbody/tr["+i+"]/td["+(s+unUsedCount)+"]/table/tbody/tr/td/table/tbody/tr/td/span");
										nextText=seleniumUtils.GetText(Locator.XPATH, "//table[@class='GridView']/tbody/tr["+(i+1)+"]/td["+(s+unUsedCount)+"]/table/tbody/tr/td/table/tbody/tr/td/span");
									}else{
										if(xpath.contentEquals("/table/tbody/tr/td/table/tbody/tr/td/a")){
											currentText=seleniumUtils.GetText(Locator.XPATH, "//table[@class='GridView']/tbody/tr["+i+"]/td["+(s+unUsedCount)+"]/table/tbody/tr/td/table/tbody/tr/td/a");
											nextText=seleniumUtils.GetText(Locator.XPATH, "//table[@class='GridView']/tbody/tr["+(i+1)+"]/td["+(s+unUsedCount)+"]/table/tbody/tr/td/table/tbody/tr/td/a");
										}
									}
								}
							}

						}catch(Exception e){

						}
						//Verifying the sort list is in Ascending order or not
						if(sortingType.contains("ASC")){
							if(nextText!=null){
								//if the column name is contains Date then it can not be sorted as String so changing the string to date format and checking the order other wise checking the order as string 
								if(cName.contains("Date")){
									Date currentDate=new SimpleDateFormat("dd/MM/yyyy").parse(currentText);
									Date nextDate=new SimpleDateFormat("dd/MM/yyyy").parse(nextText);
									if(currentDate.after(nextDate)){
										a=1;
										break;
									}
								}else{
									if(nextText.compareTo(currentText)<0){
										a=1;
										break;
									}
								}
							}
						}else{
							if(nextText!=null){
								if(cName.contains("Date")){
									Date currentDate=new SimpleDateFormat("dd/MM/yyyy").parse(currentText);
									Date nextDate=new SimpleDateFormat("dd/MM/yyyy").parse(nextText);
									if(currentDate.before(nextDate)){
										a=1;
										break;
									}
								}else{
									if(nextText.compareTo(currentText)>0){
										a=1;
										break;
									}
								}
							}
						}
					}
					if(a==1){
						LogFileControl.logFail("Verify "+tableName+" on "+sortingType+" order",cName+" Column is not sorted as "+sortingType+" Order between "+currentText+" and "+nextText);
					}else{
						LogFileControl.logPass("Verify "+tableName+" on "+sortingType+" order",cName+" Column is sorted as "+sortingType+" Order");
					}
				}catch(Exception e){
					seleniumUtils.waitForPageLoad();
				}
			}
		}catch(Exception e){
			seleniumUtils.waitForPageLoad();
		}

	}


	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#validateBreadcrumb(java.lang.String, java.lang.String)
	 */
	@Override
	public void validateBreadcrumb(String breadCrumb, String pageName)
	{
		try {
			seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH,"(//span[@class='Label_Value'])[1]" );
			String pagebreadcrumb=seleniumUtils.GetText(Locator.XPATH,"(//span[@class='Label_Value'])[1]" );
			dmsReusableFunctionUtils.verifyText(pagebreadcrumb, breadCrumb, pageName);
		}
		catch (Exception e) {

		}
	} 

	//Click on any option under mastermaintenance:
	//Uma Sasmal

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#navigateToModule(java.lang.String)
	 */
	@Override
	public void navigateToModule(String ... moduleNames) 
	{
		seleniumUtils.SendKeysSpecialChar(Locator.CSSSELECTOR, "body", Keys.chord(Keys.HOME));
		int size=moduleNames.length;
		if(size<1){
			try {
				//                        throw new NoSuchModuleException();
			} catch (Exception e) {
				LogFileControl.logFail("Navigate to Module","Enter atleat one module name to navigate");
			}

		}else{
			if(seleniumUtils.Size(Locator.LINKTEXT,moduleNames[0])>0){
				//				seleniumUtils.MoveToElement(Locator.LINKTEXT, moduleNames[0]);
				seleniumUtils.ClickwithScroll(Locator.LINKTEXT, moduleNames[0], "Home Page", moduleNames[0]);

			}else{
				seleniumUtils.MoveToElement(Locator.LINKTEXT, "More");
//				seleniumUtils.ClickwithScroll(Locator.LINKTEXT, "More", "Home Page", "More");
//				seleniumUtils.waitForPageLoad();
				 while(!seleniumUtils.IsDisplayed(Locator.LINKTEXT, moduleNames[0])){
					 int i=2;
					 seleniumUtils.Click(Locator.XPATH, "(//a[text()='More'])["+i+"]", "Home Page", "More");
					 i=i+1;
				 }
				seleniumUtils.MoveToElement(Locator.LINKTEXT, moduleNames[0]);
				seleniumUtils.ClickwithScroll(Locator.LINKTEXT, moduleNames[0], "Home Page", moduleNames[0]);
			}
			for(int i=1;i<size;i++){
				if(seleniumUtils.Size(Locator.LINKTEXT, moduleNames[i])>1){
					//					seleniumUtils.MoveToElement(Locator.XPATH, "//a[text()='"+moduleNames[i]+"']/following-sibling::ul/li/a[text()='"+moduleNames[i]+"']");
					seleniumUtils.ClickwithScroll(Locator.XPATH, "//a[text()='"+moduleNames[i]+"']/following-sibling::ul/li/a[text()='"+moduleNames[i]+"']", "", moduleNames[i]);
				}else{
					//					seleniumUtils.MoveToElement(Locator.LINKTEXT, moduleNames[i]);
					seleniumUtils.ClickwithScroll(Locator.LINKTEXT, moduleNames[i], "", moduleNames[i]);
				}

			}
			seleniumUtils.waitForPageLoad();
		}
	}



	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#isGridDisplayed(java.lang.String)
	 */
	@Override
	public void isGridDisplayed(String pagename)
	{
		try {
			
			int tablesize=seleniumUtils.Size(Locator.XPATH, "(//table[@class='GridView'])[1]/tbody/tr");
			String count;
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//span[contains(@id,'TotalRecords')])[3]")){
				String recordCountLabel = seleniumUtils.GetText(Locator.XPATH, "(//span[contains(@id,'TotalRecords')])[3]");
				String record[] = recordCountLabel.split(" ");
				count = record[2];
			}
			else{
				String recordcountlabel=seleniumUtils.GetText(Locator.XPATH, "//span[@class='GridView_LabelRecordCount']");
				String record[]=recordcountlabel.split(" ");
				count=record[4];
			}
			
			if ((tablesize-1)==Integer.valueOf(count)) {
				LogFileControl.logPass(pagename,getLogMessage("Grid Count Pass ")+count);
			} else {
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[contains(@id,'List_NoRecord_Label')]"))
				{
					LogFileControl.logPass(pagename,"No Record Availble");
				}
				else{
					LogFileControl.logFail(pagename,getLogMessage("Grid Count Fail"));
				}

			}
		} catch (Exception e) {

		}
	}  
	
	
	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#collapseNode(java.lang.String, java.lang.String)
	 */
	@Override
	public void collapseTree(String nodeName, String pageName){
		try{
			seleniumUtils.Click(Locator.XPATH, "//img[@alt='Collapse "+nodeName+"']", pageName, nodeName+" Collapse");
		}catch(Exception e){

		}
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#expandNode(java.lang.String, java.lang.String)
	 */
	@Override
	public void expandTree(String nodeName, String pageName){
		try{
			seleniumUtils.Click(Locator.XPATH, "//img[@alt='Expand "+nodeName+"']", pageName, nodeName+" Expand");
		}catch(Exception e){

		}	
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#GetDatetime()
	 */
	@Override
	public String getDatetime(){

		SimpleDateFormat DtFormat = new SimpleDateFormat("yyMMddhhmmaa");
		Date date = new Date();
		String DateAndTime=(DtFormat.format(date).toString());
		if(DateAndTime.substring(DateAndTime.length()-2).equalsIgnoreCase("pm")){
			int hr = Integer.valueOf(DateAndTime.substring(6,8))+12;
			DateAndTime = DateAndTime.substring(0,6)+hr+DateAndTime.substring(8,10);
		}else{
			DateAndTime = DateAndTime.substring(0,10);
		}
		return DateAndTime;
	}

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#GetDatetimeinSec()
	 */
	@Override
	public String getDatetimeinSec(){

		SimpleDateFormat DtFormat = new SimpleDateFormat("yyMMddhhmmssaa");
		Date date = new Date();
		String DateAndTime=(DtFormat.format(date).toString());
		if(DateAndTime.substring(DateAndTime.length()-2).equalsIgnoreCase("pm")){
			int hr = Integer.valueOf(DateAndTime.substring(6,8))+12;
			DateAndTime = DateAndTime.substring(0,6)+hr+DateAndTime.substring(8,12);
		}else{
			DateAndTime = DateAndTime.substring(0,12);
		}
		return DateAndTime;
	}
	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#deleteRecord(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteRecord(String recordID,String pageName)
	{
		try {

			seleniumUtils.SendKeys(Locator.XPATH, "//span[text()='Value']/following::input[1]", recordID, pageName, " Value Textbox");
			seleniumUtils.Click(Locator.XPATH, "//input[@value='Search']", pageName, "Search Button");
			int tablesize=seleniumUtils.Size(Locator.XPATH, "(//table[@class='GridView'])[1]/tbody/tr");
			if(tablesize>=2){
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//a[text()='"+recordID+"']/preceding::input[1]"))
				{
					seleniumUtils.Click(Locator.XPATH, "//a[text()='"+recordID+"']/preceding::input[1]", pageName, "Recode ID");
					seleniumUtils.waitForPageLoad();
					seleniumUtils.Click(Locator.XPATH, "//input[@value='Delete']", pageName, "Delete Button");
					getAlertmessage(pageName);   
					String[] msg=dmsReusableFunctionUtils.getAlertmessage(pageName).split(":");
					dmsReusableFunctionUtils.verifyText(msg[0], "Record deleted.", pageName);
					LogFileControl.logPass(pageName, "Record deleted.");
					ExtentTestManager.getlogger().log(LogStatus.PASS, pageName, "Record deleted.");
					seleniumUtils.waitForPageLoad();
				}
				else{
					LogFileControl.logFail(pageName, "Record can not be deleted.");
					//					ExtentTestManager.getlogger().log(LogStatus.FAIL, pageName, "Record can not be deleted.");
				}
			}
			else{
				LogFileControl.logFail( pageName, "No Record Available in Table to delete.");
				//				ExtentTestManager.getlogger().log(LogStatus.FAIL, pageName, "No Record Available in Table to delete.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#randomGenerator(int)
	 */
	@Override
	public int randomGenerator(int digitNo){
		int randomInt = 1;
		try {
			int nextInt=1;
			for(int i =1;i<=digitNo;i++){
				nextInt=nextInt*10;
			}
			System.out.println("nextint "+nextInt);
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(nextInt);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return randomInt;
	} 

	/* (non-Javadoc)
	 * @see com.Automation.NewsPage.DMSUtils#randomGenerator(int, int)
	 */
	@Override
	public int randomGenerator(int low, int high){
		int randomInt = 1;
		try {
			Random r = new Random();
			randomInt = r.nextInt(high-low) + low;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return randomInt;
	}
	

	public float taxSum(String distState, String custState, String hsnCode){
		try {
//			if(distState.equalsIgnoreCase(custState)){
//				String taxStructure = databaseUtils.getDBvalues("select ITAX_STRUCT_hsn.taxstruct_cd from ITAX_STRUCT_hdr "
//						+ " inner join ITAX_STRUCT_hsn on ITAX_STRUCT_hsn.taxstruct_cd = ITAX_STRUCT_hdr.taxstruct_cd "
//						+ "where ITAX_STRUCT_hsn.hsn_cd = '"+hsnCode+"' and ITAX_STRUCT_hdr.state_cd = '"+custState+"'").get("taxstruct_cd");
//				
//			}else{
//				String taxStructure=databaseUtils.getDBvalues("select ITAX_STRUCT_hsn.taxstruct_cd from ITAX_STRUCT_hdr "
//						+ " inner join ITAX_STRUCT_hsn on ITAX_STRUCT_hsn.taxstruct_cd = ITAX_STRUCT_hdr.taxstruct_cd "
//						+ "where ITAX_STRUCT_hsn.hsn_cd = '"+hsnCode+"' and ITAX_STRUCT_hdr.state_cd = '000'").get("taxstruct_cd");
//			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	public void validateSearchdropdown(String fieldnamelocatorValue,String operatorlocatorValue,String valuelocatorValue,List<String> operators, List<String> values)
	{
		try {
			List<String> options=seleniumUtils.getAllDropDownOptions(Locator.XPATH, fieldnamelocatorValue);
			int j=0;
			for(int i=0;i<options.size();i++){
				seleniumUtils.Select(Locator.XPATH, fieldnamelocatorValue, Selector.SELECTBYVISIBLTTEXT, options.get(i));
				seleniumUtils.waitForPageLoad();
				dmsReusableFunctionUtils.verifyText(seleniumUtils.getSelectedOption(Locator.XPATH, operatorlocatorValue), operators.get(i), "Listing Page");
				if(seleniumUtils.Size(Locator.XPATH, "//select"+valuelocatorValue)>0){
					dmsReusableFunctionUtils.verifyText(seleniumUtils.getSelectedOption(Locator.XPATH, "//select"+valuelocatorValue), values.get(j), "Listing Page");
					j++;
				}
				else{
					if(seleniumUtils.Size(Locator.XPATH, "//input"+valuelocatorValue)>0)
					{
						seleniumUtils.IsDisplayed(Locator.XPATH, "//input"+valuelocatorValue);
						
					}
					else{
						dmsReusableFunctionUtils.doHide(Locator.XPATH, "//input"+valuelocatorValue, "Value Textbox");	
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void verifyOperatorField(String operatorLoctorValue,String textboxLocatorValue,List<String> selectValue, String value,String pageName)
	{
		List<String> options=seleniumUtils.getAllDropDownOptions(Locator.XPATH, operatorLoctorValue);
		for(int i=0;i<options.size();i++){
			seleniumUtils.Select(Locator.XPATH, operatorLoctorValue, Selector.SELECTBYVISIBLTTEXT, selectValue.get(i));
			seleniumUtils.waitForPageLoad();
			seleniumUtils.SendKeys(Locator.XPATH, textboxLocatorValue, value, pageName, "Value textbox");
			seleniumUtils.Click(Locator.XPATH, "(//input[@value='Search'])", pageName, "Search Button");
			seleniumUtils.waitForPageLoad();
			dmsReusableFunctionUtils.isGridDisplayed(pageName);
			seleniumUtils.Clear(Locator.XPATH, textboxLocatorValue);
		}

	}
	
}
