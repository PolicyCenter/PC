package com.automation.PolicyCenter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DriverManager {

	private static ThreadLocal<WebDriver> ThreadDriver=new ThreadLocal<WebDriver>() ;
	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<WebDriverWait>();
	static DataProvider data = new DataProvider();
	//                                            static DatabaseUtils databaseUtils = new DatabaseUtils();
	public static String browserType;
	public static String browserName;
	public static WebDriver getDriver(){
		WebDriver driver= DriverManager.ThreadDriver.get();
		try{
			if (driver==null){
				switch(browserType){
				case "Chrome":
					String path=System.getProperty("user.dir") +"\\Drivers\\chromedriver.exe";
					System.setProperty("webdriver.chrome.driver",path); 
					driver = new EventFiringWebDriver(new ChromeDriver());
					break;
				case "HtmlUnit":
					driver = new EventFiringWebDriver(new HtmlUnitDriver(true));
					break;
				case "ChromeHeadless":
					//supports chrome 60.0
					path=System.getProperty("user.dir") +"Drivers\\chromedriver.exe";
					System.setProperty("webdriver.chrome.driver",path); 
					// Add options to Google Chrome. The window-size is important for responsive sites
					ChromeOptions options = new ChromeOptions();
					options.addArguments("headless");
					//options.addArguments("window-size=1200x600");
					driver = new ChromeDriver(options);
					break;
				case "PhantomJS":
					//supports chrome 60.0
					path=System.getProperty("user.dir") +"\\Drivers\\phantomjs.exe";
					System.setProperty("phantomjs.binary.path",path); 
					driver = new EventFiringWebDriver(new PhantomJSDriver());
					break;
				
				}
			}
			driver.manage().window().maximize();
			ThreadDriver.set(driver);
			wait.set(new WebDriverWait(driver, 30));
//			System.out.println(driver.getTitle());
		}catch (Exception e) {
			// TODO: handle exception
		}
		return driver;
	}

	public static WebDriverWait getWait(){
		return wait.get();
	}


	public static void setupDriver(String browser){
		browserType=browser;
	}		

	private static final ThreadLocal<String> userName = new ThreadLocal<String>();

	public static String getUserCredentials() {
		return (userName.get());
	}

	public static void setUserCredentials(String value) {
		userName.set(new String(value));
	}
	
	public static void quitDriver(){
		//			System.out.println("Killing driver...............   "+getDriver());
		getDriver().quit();
//		String srNo = databaseUtils.getDBvalues("select top 1 serial_no from FW_AUDIT_RECORD where EVENT_ID='200' and USER_ID='"+DriverManager.getUserName()+"' order by RECORD_DT desc ").get("serial_no");
//		databaseUtils.dbUpdate("update FW_AUDIT_RECORD set CATEGORY = 'Logout' where serial_no = '"+srNo+"'");
		DriverManager.ThreadDriver.set(null);
	}

}



