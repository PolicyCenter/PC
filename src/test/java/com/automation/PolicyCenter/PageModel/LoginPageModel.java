package com.automation.PolicyCenter.PageModel;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.automation.PolicyCenter.Base;
import com.automation.PolicyCenter.DriverManager;
import com.automation.PolicyCenter.Locators.Locator;




public class LoginPageModel extends Base {
	public static final String userid_xpath = "//input[@id='Login:LoginScreen:LoginDV:username-inputEl']";
	public static final String password_xpath = "//input[@id='Login:LoginScreen:LoginDV:password-inputEl']";
	public static final String login_id = "Login:LoginScreen:LoginDV:submit-btnInnerEl";


	//********************************** Modular Method *****************************

//	public void login(){
//		try {
//			login("HQ");
//		} catch (Exception e1) {
//			login();
//		}
//	}

	

	//********************************** parameterized Method *****************************


	public void login(String userName, String Password){
		try{
			DriverManager.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(userid_xpath)));
			seleniumUtils.SendKeys(Locator.XPATH, userid_xpath, userName, "Login", "User ID");
			seleniumUtils.SendKeys(Locator.XPATH, password_xpath, Password, "Login", "Password");
			seleniumUtils.Click(Locator.ID, login_id, "Login", "Login");

		}catch(Exception e){}
	}

	
}
