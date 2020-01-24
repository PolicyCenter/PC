package com.automation.PolicyCenter;

import java.util.List;

import com.automation.PolicyCenter.Locators.Locator;



public interface DMSUtils {

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param action (only pass "Accept" or "Dismiss")
	 * @description This method will allow to dismiss or accept the alert based on the input
	 */
	public abstract void actionAlert(String action);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param pageName
	 * @return the Alert message
	 * @description This method will get the alert message and accept the alert 
	 */
	public abstract String getAlertmessage(String pageName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param day (After how many days you want the date e.g 7 days)
	 * @return (Date in string format)
	 */
	public abstract String getDate(String format, int day);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param day (After how many days you want the date e.g 7 days)
	 * @return (Date in string format)
	 */
	public abstract String getDate(int day);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param expectedDate 
	 * @param actualDate
	 * @param pageName
	 * @description to verify expected date is only displaying as actual date. (expectedDate and actualDate should be in same format)
	 */
	public abstract void verifyDate(String actualDate, String expectedDate,
			String pageName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param expectedText 
	 * @param actualText
	 * @param pageName
	 * @description to verify expected Text is only displaying as actual Text. 
	 */
	public abstract void verifyText(String actualText, String expectedText,
			String pageName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param actual
	 * @param expected
	 * @param pageName
	 * @description to verify expected list of Text is only displaying as actual list of Text.
	 */
	public abstract void verifyList(List<String> actual, List<String> expected,
			String pageName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify the box is text box or not
	 */
	public abstract void isTextBox(Locator locatorType, String locatorValue,
			String labelName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify the box is dropdown or not
	 */
	public abstract void isDropDown(Locator locatorType, String locatorValue,
			String labelName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify webelement is disable or not
	 */
	public abstract boolean isDisabled(Locator locatorType, String locatorValue,
			String labelName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify webelement is disable or not
	 */
	public abstract void verifyWebElement(boolean actualStatus,
			boolean expectedStatus, String labelName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify the element is disappeared or not
	 */
	public abstract void doHide(Locator locatorType, String locatorValue,
			String labelName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param labelName - Field Name
	 * @param dateType (Provider as Future or Past)
	 * @param pageName
	 * @description To select date from future month or past month from Date picker
	 */
	public abstract void selectDate_DatePicker(String labelName,
			String dateType, String pageName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param tableName (For Reporting)
	 * @param sortingType (allows input as ASC and DESC)
	 * @description This will allow to verify the grid table is in ascending order or not
	 */
	public abstract void verifyGrid_Sorting(String tableName, String sortingType);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param breadCrumb
	 * @param pageName (For Reporting)
	 * @description To verify that Breadcrumb is displaying for the page is as expected
	 */
	public abstract void validateBreadcrumb(String breadCrumb, String pageName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param moduleNames (Dynamic parameter count, need to pass module names)
	 * @description To navigate different modules under DMS
	 */
	public abstract void navigateToModule(String... moduleNames);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param pagename (For Reporting)
	 * @description To verify that grid is displaying or not in the page
	 */
	public abstract void isGridDisplayed(String pagename);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param nodeName
	 * @param pageName
	 * @description To collapse the tree depends on the tree name
	 */
	public abstract void collapseTree(String nodeName, String pageName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param nodeName
	 * @param pageName
	 * @description To expand the tree depends on the tree name
	 */
	public abstract void expandTree(String nodeName, String pageName);

	/**
	 * @version 1.01
	 * @author kiran.rameshrao.bahe 
	 * @method name - GetDatetime()
	 * @Method Description - This method will return date and time in this 'yyMMddhhmm' format 
	 */
	public abstract String getDatetime();

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @method name - getDatetime()
	 * @Method Description - This method will return date and time in this 'yyMMddhhmm' format 
	 */
	public abstract String getDatetimeinSec();

	/**
	 * @version 1.01
	 * @author kiran.rameshrao.bahe 
	 * @method name - deleteRecord()
	 * @Method Description - This method use for delete record .
	 */
	public abstract void deleteRecord(String recordID, String pageName);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param digitNo
	 * @return
	 */
	public abstract int randomGenerator(int digitNo);

	/**
	 * @version 1.01
	 * @author uma.sasmal
	 * @param low
	 * @param high
	 * @return
	 */
	public abstract int randomGenerator(int low, int high);

}