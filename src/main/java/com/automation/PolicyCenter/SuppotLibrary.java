package com.automation.PolicyCenter;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



import com.relevantcodes.extentreports.NetworkMode;

public class SuppotLibrary {

	static SuppotLibrary suppotLibrary = new SuppotLibrary();
	DataProvider dataProviderLogic = new DataProvider();
	public static String reportFolder=suppotLibrary.getReportFolder();

	public String timestamp(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		String reportdate = simpleDateFormat.format(date);
		return reportdate;
	}

	public void createFile(String path)
	{

		File theDir = new File(path);  // Defining Directory/Folder Name  
		try{   
			if (!theDir.exists()){  // Checks that Directory/Folder Doesn't Exists!  
				theDir.mkdir();    
			}  
		}catch(Exception e){  
			//JOptionPane.showMessageDialog(null, e);  
		}  
	}

	public NetworkMode getnetWorkmode(){
		NetworkMode accessType;
		if(dataProviderLogic.getPropertyval("NetworkMode").equalsIgnoreCase("offline")){
			accessType=NetworkMode.OFFLINE;
		}else{
			accessType=NetworkMode.ONLINE;
		}
		return accessType;
	}

	public String getReportFolder(){
		String path=null;
		if(reportFolder==null){
		path=System.getProperty("user.dir") +"\\Report\\"+ timestamp()+"\\";
		createFile(path);
		}
		return path; 
	}

	public String getReportPath(){

		//String path=reportFolder+scriptId+"-"+timestamp()+".html";
		String path=reportFolder+"Automation Execution Report.html";
		return path; 
	}

	public String getScreenshotPath(String scriptId){
		String path=reportFolder+scriptId+"-"+timestamp()+".png";
		return path; 
	}

	public String screenCapture(WebDriver driver,String scriptId) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = reportFolder+scriptId+"-"+timestamp()+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);        

		return dest;
	}
	
	public double getdecimalRounder(String decimalRound){
		int dr=Integer.valueOf(decimalRound);
		double rounder=1;
		for(int j=0;j<dr;j++){
			rounder=rounder*10.0;
		}
		return rounder;
	}
}
