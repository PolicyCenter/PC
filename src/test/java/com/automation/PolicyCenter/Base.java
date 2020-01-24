package com.automation.PolicyCenter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Base {

	public static Utils seleniumUtils = new SeleniumUtils();
	public static DataProvider dataProvider = new DataProvider();
	public static DriverManager driverManager = new DriverManager();
	public static ReadCSV readCSV = new ReadCSV();
	public static SuppotLibrary suppotLibrary = new SuppotLibrary();
	public static WriteConfig writeConfig = new WriteConfig();
	public static DMSReusableFunctionUtils dmsReusableFunctionUtils = new DMSReusableFunctionUtils();
	public static ThreadLocal<String> threadscriptId=new ThreadLocal<String>() ;
	public static ThreadLocal<String> threadmethodName=new ThreadLocal<String>() ;
	//	private final char PKG_SEPARATOR = '.',DIR_SEPARATOR = '/';
	//	private final String CLASS_FILE_SUFFIX = ".class",BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";
	private final char PKG_SEPARATOR = dataProvider.getPropertyval("PKG_SEPARATOR").charAt(0),DIR_SEPARATOR = dataProvider.getPropertyval("DIR_SEPARATOR").charAt(0);
	private final String CLASS_FILE_SUFFIX = dataProvider.getPropertyval("CLASS_FILE_SUFFIX"),BAD_PACKAGE_ERROR = dataProvider.getPropertyval("BAD_PACKAGE_ERROR");
	static Base base=new Base();
	public static String url=null , browser = null, environment = null, group = null,parallelCount =null;
	//	public static ExtentReports extentReports;

	public static String getScriptId(){
		return (threadscriptId.get());
	}
	public static void setScriptId(String value) {
		threadscriptId.set(new String(value));
		LogFileControl.setScriptId_report(value);
	}
	private static final ThreadLocal<List<String>> myComponents = new ThreadLocal<List<String>>();

	public static List<String> getComponents() {
		return (myComponents.get());
	}

	public static void setComponents(List<String> value) {
		myComponents.set(new ArrayList<String>(value));
	}

	private static final ThreadLocal<String> excelFileName = new ThreadLocal<String>();

	public static String getExcelName() {
		return (excelFileName.get());
	}

	public void setExcelName(String value) {
		excelFileName.set(new String(value));
	}

	private static final ThreadLocal<String> csvFileName = new ThreadLocal<String>();

	public static String getCSVFileName() {
		return (csvFileName.get());
	}

	public void setCSVFileName(String value) {
		csvFileName.set(new String(value));
	}

	private static final ThreadLocal<String> excelSheetName = new ThreadLocal<String>();

	public static String getexcelSheet() {
		return (excelSheetName.get());
	}

	public void setExcelSheet(String value) {
		excelSheetName.set(new String(value));
	}

	private static final ThreadLocal<String> mymethods = new ThreadLocal<String>();

	public static String getMethodName() {
		return (mymethods.get());
	}

	public static void setMethodName(String value) {
		mymethods.set(new String(value));
	}

	private static final ThreadLocal<Integer> iteration = new ThreadLocal<Integer>();

	public static int getIterarionCount() {
		return (iteration.get());
	}

	public static void setIterarionCount(int value) {
		iteration.set(new Integer(value));
	}
	
	private static final ThreadLocal<Integer> invocetion = new ThreadLocal<Integer>();

	public static int getInvocationCount() {
		return (invocetion.get());
	}

	public static void setInvocationCount(int value) {
		invocetion.set(new Integer(value));
	}
	
	private static final ThreadLocal<Integer> threadID = new ThreadLocal<Integer>();

	public static int getthreadID() {
		return (threadID.get());
	}

	public static void setthreadID(int value) {
		threadID.set(new Integer(value));
	}

	@SuppressWarnings("deprecation")
	@BeforeSuite(alwaysRun=true)
	public void extentSetup(ITestContext context) {
		try {
			ExtentManager.setOutputDirectory(context);
			ExtentTestManager.extent = ExtentManager.getInstance();
			org.apache.log4j.PropertyConfigurator.configure(System.getProperty("user.dir") + "\\Log4j.properties");
			LogFileControl.logInfo(context.getSuite().getName()+" Suite Started");
			group=System.getProperty("groupToRun");
			System.out.println("Group is " +group);
			String group1=System.getProperty("groupToRun1");
			System.out.println("Group is " +group1);
			url=System.getProperty("url");
			System.out.println("URL is " +url);
			browser = System.getProperty("browser");
			environment = System.getProperty("environment");
			parallelCount=System.getProperty("threadCount");
//			parallelCount="3";
			if(parallelCount==null){
				context.getSuite().getXmlSuite().setParallel(System.getProperty("parallel", "false"));
			}else{
				context.getSuite().getXmlSuite().setParallel("classes");
				if(Integer.valueOf(parallelCount)>10){
					context.getSuite().getXmlSuite().setThreadCount(10);
				}else{
					context.getSuite().getXmlSuite().setThreadCount(Integer.valueOf(parallelCount));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@BeforeClass(alwaysRun=true)
	protected void testClassStart(ITestContext context){
		try{
			setthreadID((int) Thread.currentThread().getId());
			Thread.sleep(getthreadID()*100);
			if(browser!=null){
				DriverManager.setupDriver(browser);
			}else{
				DriverManager.setupDriver("Chrome");
			}
			setthreadID((int) Thread.currentThread().getId());
			if(url!=null){
				DriverManager.getDriver().get(url);
			}else{
				DriverManager.getDriver().get(dataProvider.getPropertyval("URL"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@BeforeMethod (alwaysRun=true)
	protected void testMethodStart(Method method){
		try{
			LogFileControl.logInfo("test method........  "+this.getClass().getName()+"  ............  "+method.getName()+"  ............  "+DriverManager.getDriver());
//			System.out.println("test method........  "+this.getClass().getName()+"  ............  "+method.getName()+"  ............  "+DriverManager.getDriver()+".........."+Thread.currentThread().getId());
			setScriptId(method.getName());
			ExtentTestManager.setlogger(ExtentTestManager.startTest(getScriptId()));
			System.out.println("............................");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun=true)
	public void testMethodEnd(ITestResult result){
		try{
			if(result.getStatus() == ITestResult.FAILURE){
				System.out.println("TEST******");
			}
			ExtentTestManager.endTest();  // new
			ExtentTestManager.extent.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun=true)
	public void tearDown(){
		try{
			String userCredentials = DriverManager.getUserCredentials();
			if(userCredentials.length()>1){
			String credentials[] = userCredentials.split(" ");
			writeConfig.updateUser(credentials[0]+"Users", credentials[1], credentials[2]);
			}
		}catch(Exception e){
//			e.printStackTrace();
		}
		try {
			DriverManager.quitDriver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterSuite(ITestContext context) {
		try{
			ExtentTestManager.extent.close();
			LogFileControl.logInfo(context.getSuite().getName()+" Suite Ended");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private List<Class<?>> find(String scannedPackage) {
		List<Class<?>> classes = null;
		String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
		for(int i=0;i<Thread.activeCount();i++){
			URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
			if (scannedUrl == null) {
				throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
			}
			File scannedDir = new File(scannedUrl.getFile());
			classes = new ArrayList<Class<?>>();
			for (File file : scannedDir.listFiles()) {
				classes.addAll(find(file, scannedPackage));
			}
		}
		return classes;
	}

	private List<Class<?>> find(File file, String scannedPackage) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		String resource = scannedPackage + PKG_SEPARATOR + file.getName();
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				classes.addAll(find(child, resource));
			}
		} else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
			int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
			String className=resource.substring(0, endIndex);
			try {
				classes.add(Class.forName(className));
			} catch (ClassNotFoundException ignore) {
			}
		}
		return classes;
	}

	public void runTestSteps(List<String> components){
		setComponents(components);
		int methodCount = 0;
		for(int j=0;j<getComponents().size();j++){
			setMethodName(getComponents().get(j));
			methodCount++;
			int iteration=1;
			setIterarionCount(iteration);
			boolean flag=false;
			if(methodCount>1){
				for(int k=0; k<=methodCount-1;k++){

					if(getMethodName().contentEquals(getComponents().get(k))){
						setIterarionCount(iteration++);
					}
				}
				if(getIterarionCount()>1){
					LogFileControl.logInfo(getMethodName(), "Iteration No "+getIterarionCount()+" Started");
				}
			}
			List<Class<?>> classes = find("com.Automation.NewsPage.PageModel");
			Class<?> className;
			int size=classes.size();
			for(int cla=0;cla<size;cla++){
				className=classes.get(cla);
				try {
					Object obj = className.newInstance();
					Method method = className.getMethod(getMethodName());
					flag =true;
					method.invoke(obj);
					break;
				}catch(NoSuchMethodException e){

				} catch (IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					//					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (InstantiationException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(flag == false){
				LogFileControl.logError(getMethodName()+" Method is not available under any class");
				//				ExtentTestManager.getlogger().log(LogStatus.ERROR, getMethodName()+" Method is not available under any class");
			}

		}
	}

//	public String getExcelData(String colmnName) throws Exception{
//		String data = null;
//		try{
//			data = dataProvider.getDataLogic(Base.getExcelName(),Base.getexcelSheet(),Base.getScriptId(),Base.getIterarionCount(),colmnName);
//		}catch(Exception e){}
//		return data;
//	}

	public String getCSVData(String colmnName) throws Exception{
		String data = null;
		try{

			data = readCSV.getCSVValue(Base.getCSVFileName(),Base.getScriptId(),colmnName,Base.getIterarionCount());
		}catch(Exception e){}
		return data;
	}

	public String getPropertyData(String key){

		String value=dataProvider.getPropertyvalue("DMSDataManager",key);
		return value;

	}
	
	public String getPromotionData(String key){

		String value=dataProvider.getPropertyvalue("PromotionData",key);
		return value;

	}

	public String getSetUpData(String key){

		String value=dataProvider.getPropertyvalue("DMSSetUpData",key);
		return value;

	}
	public String getUser(String userType,String key){
		String value = null;
		if(userType.equalsIgnoreCase("HQ")){
			value=dataProvider.getPropertyvalue("HQUsers",key);
		}else{
			value=dataProvider.getPropertyvalue("DISTUsers",key);
		}

		return value;

	}

	public int getUsersize(String userType){
		int value =0;
		if(userType.equalsIgnoreCase("HQ")){
			value=dataProvider.getPropertysize("HQUsers");
		}else{
			value=dataProvider.getPropertysize("DISTUsers");
		}
		return value;

	}
	
	public String getInvalidData(String type){
		String value = null;
		value=dataProvider.getPropertyvalue("InvalidData", type);
		return value;
	}
	
	public String getLogMessage(String type){
		String value = null;
		value=dataProvider.getPropertyvalue("LogMessage", type);
		return value;
	}
	
}


	









