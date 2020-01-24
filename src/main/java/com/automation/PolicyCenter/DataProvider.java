package com.automation.PolicyCenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class DataProvider {
	ReadExcel readExcel = new ReadExcel();

	public String getPropertyval(String key){

		String value=getPropertyvalue("Config",key);
		return value;


	}

	protected String getPropertyvalue(String fileName, String key){

		FileInputStream fis;
		String value=null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\"+fileName+".properties");

			Properties prop = new Properties();
			prop.load(fis);
			value=prop.get(key).toString();
			if(value==null || value ==""){
				System.out.println("Set "+key);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	} 

	public int getPropertysize(String fileName){

		FileInputStream fis;
		int value=0;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\"+fileName+".properties");

			Properties prop = new Properties();
			prop.load(fis);
			value=prop.size();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	List<Object[]> commondpLogic(String filename,String sheetName,String scriptid,int iteration) throws Exception{
		String fileName=System.getProperty("user.dir") +"\\DataTable\\"+filename+".xlsx";

		//   readExcel readExcel = new readExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\Test_Data.xlsx");

		HashMap<String, Integer> countDetails=readExcel.getCount(fileName, sheetName);
		int rowcount = countDetails.get("RowCount");
		int colcount = countDetails.get("ColumnCount");
		//               List to store Object array which inturn store Map
		List<Object[]> dataDetails = new ArrayList<Object[]>();
		//               row iterations
		for(int iRow =1;iRow<=rowcount ;iRow++){
			String getvalue=readExcel.getCellValue(fileName,sheetName,iRow,0);
			if(getvalue.equals(scriptid)){
				//                              map to store data
				Map<String, String> hashMap = new HashMap<String,String>();
				//                              column iteration
				for(int jCol = 1 ;jCol<colcount;jCol++){
					int rownowithiterartion=iRow+(iteration-1);
					if(scriptid.equals(readExcel.getCellValue(fileName,sheetName,rownowithiterartion,0))){
						String Key =readExcel.getCellValue(fileName,sheetName, 0, jCol);
						if(Key.equals("Quantity")){
							System.out.println(".......................");
						}
						String Val =readExcel.getCellValue(fileName,sheetName, rownowithiterartion, jCol);

						hashMap.put(Key, Val);
					}else{
						System.out.println("For Iteration no "+iteration+" : Data not Present");
					}


				}             //col loop

				Object[] x = new Object[1];
				x[0]=hashMap;
				dataDetails.add(x);
				break;
			} //end of conditional statement 

		}//row loop

		return dataDetails; 

	}

	String getDataLogic_old(String filename,String sheetName,String scriptid,int iteration,String columnName) throws Exception{
		String fileName=System.getProperty("user.dir") +"\\DataTable\\"+filename+".xlsx";

		//   readExcel readExcel = new readExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\Test_Data.xlsx");

		HashMap<String, Integer> countDetails=readExcel.getCount(fileName, sheetName);
		int rowcount = countDetails.get("RowCount");
		int colcount = countDetails.get("ColumnCount");
		//               row iterations
		//      map to store data
		String val = null;
		for(int iRow =1;iRow<=rowcount ;iRow++){
			String getvalue=readExcel.getCellValue(fileName,sheetName,iRow,0);
			if(getvalue.equals(scriptid)){

				//column iteration
				for(int jCol = 1 ;jCol<colcount;jCol++){
					int rownowithiterartion=iRow+(iteration-1);
					if(scriptid.equals(readExcel.getCellValue(fileName,sheetName,rownowithiterartion,0))){
						String Key =readExcel.getCellValue(fileName,sheetName, 0, jCol);
						if(Key.contentEquals(columnName)){
							val =readExcel.getCellValue(fileName,sheetName, rownowithiterartion, jCol);
							break;
						}
					}else{
						System.out.println("For Iteration no "+iteration+" : Data not Present");
					}


				}             //col loop

				break;
			} //end of conditional statement 

		}//row loop

		return val; 

	}

	List<Object[]> getRunControllerDetails_update() {
		String fileName=System.getProperty("user.dir") +"\\RunController.xlsx";
		String sheetName=getPropertyval("RunContollerSheetName");
		HashMap<String, Integer> countDetails=readExcel.getCount(fileName,sheetName);
		int rowcount = countDetails.get("RowCount");
		int colcount = countDetails.get("ColumnCount");

		//      List to store Object array which inturn store Map
		List<Object[]> dataDetails = new ArrayList<Object[]>();
		//    row iterations
		for(int iRow =1;iRow<=rowcount ;iRow++){
			String getvalue=readExcel.getCellValue(fileName,sheetName,iRow,4);
			if(getvalue.equalsIgnoreCase("Yes")){
				//                   map to store data
				HashMap<String, String> hashMap = new HashMap<String,String>();
				//                   column iteration
				for(int jCol = 0 ;jCol<colcount;jCol++){

					String Key =readExcel.getCellValue(fileName,sheetName, 0, jCol);
					String Val =readExcel.getCellValue(fileName,sheetName, iRow, jCol);

					hashMap.put(Key, Val);


				}             //col loop

				Object[] x = new Object[1];
				x[0]=hashMap;
				dataDetails.add(x);
				//dataDetails.add(hashMap);
			} //end of conditional statement 
		}//row loop

		return dataDetails; 

	}

	List<HashMap<String, String>> getRunControllerDetails() {
		String fileName=System.getProperty("user.dir") +"\\RunController.xlsx";
		String sheetName=getPropertyval("RunContollerSheetName");
		HashMap<String, Integer> countDetails=readExcel.getCount(fileName,sheetName);
		int rowcount = countDetails.get("RowCount");
		int colcount = countDetails.get("ColumnCount");

		//      List to store Object array which inturn store Map
		List<HashMap<String, String>> dataDetails = new ArrayList<HashMap<String, String>>();
		//    row iterations
		for(int iRow =1;iRow<=rowcount ;iRow++){
			String getvalue=readExcel.getCellValue(fileName,sheetName,iRow,4);
			if(getvalue.equalsIgnoreCase("Yes")){
				//                   map to store data
				HashMap<String, String> hashMap = new HashMap<String,String>();
				//                   column iteration
				for(int jCol = 0 ;jCol<colcount;jCol++){

					String Key =readExcel.getCellValue(fileName,sheetName, 0, jCol);
					String Val =readExcel.getCellValue(fileName,sheetName, iRow, jCol);

					hashMap.put(Key, Val);


				}             //col loop
				dataDetails.add(hashMap);
			} //end of conditional statement 
		}//row loop

		return dataDetails; 

	}

	List<String> gettestCaseDetails(String filename,String scriptid){
		String fileName=System.getProperty("user.dir") +"\\DataTable\\"+filename+".xlsx";

		//   readExcel readExcel = new readExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\Test_Data.xlsx");

		HashMap<String, Integer> countDetails=readExcel.getCount(fileName, "Business Components");
		int rowcount = countDetails.get("RowCount");
		//    List to store Object array which inturn store Map
		List<String> componentDetails = new ArrayList<String>();
		//    row iterations
		int flag=0;
		for(int iRow =1;iRow<=rowcount ;iRow++){

			int colcount = readExcel.getphysicalCount(fileName, "Business Components", iRow);
			String getvalue=readExcel.getCellValue(fileName,"Business Components",iRow,0);
			if(getvalue.equals(scriptid)){
				flag=1;
				//                   column iteration
				for(int jCol = 1 ;jCol<colcount;jCol++){

					String Val =readExcel.getCellValue(fileName,"Business Components", iRow, jCol);

					componentDetails.add(Val);


				}             //col loop

				break;
			} //end of conditional statement 
		}
		//row loop
		if(flag==0){
			System.out.println("Test Case business componet is not available");
		}
		return componentDetails; 

	}


	String getDataLogic(String filename,String sheetName,String scriptid,int iteration,String columnName) throws Exception{
		String val = null;
		String fileName = null;
		String filePath=null;
		try{
			if(filename.contains("_")){
				String fil[] =filename.split("_");
				for(int i=0;i<fil.length-1;i++){
					filePath=System.getProperty("user.dir") +"\\DataTable\\";
					filePath=filePath+fil[i]+"\\";
				}
				filePath = filePath+filename+".xlsx";
				File f2 = new File(filePath);
				if(f2.exists() && !f2.isDirectory()) {
					fileName=filePath;
				}
			}else{
				filePath=System.getProperty("user.dir") +"\\DataTable\\"+filename+".xlsx";
				File f = new File(filePath);
				if(f.exists() && !f.isDirectory()) {
					fileName=filePath;
				}
				else{
					File file = new File(System.getProperty("user.dir") +"\\DataTable");
					String[] names = file.list();

					for(String name : names)
					{
						if (new File(System.getProperty("user.dir") +"\\DataTable").isDirectory())
						{
//							System.out.println(name);
							filePath =System.getProperty("user.dir") +"\\DataTable\\"+name+"\\"+filename+".xlsx";
							File f1 = new File(filePath);
							if(f1.exists() && !f1.isDirectory()) { 
								fileName = filePath;
								break;
							}
						}
					}
				}
			}
			HashMap<String, Integer> countDetails=readExcel.getCount(fileName, sheetName);
			int rowcount = countDetails.get("RowCount");
			int colcount = countDetails.get("ColumnCount");
			boolean testCaseFound =false;
			for(int iRow =1;iRow<=rowcount ;iRow++){
				String getvalue=readExcel.getCellValue(fileName,sheetName,iRow,0);
				if(getvalue.equals(scriptid)){
					boolean colmnFound = false;
					for(int jCol = 1 ;jCol<colcount;jCol++){
						int rownowithiterartion=iRow+(iteration-1);
						if(scriptid.equals(readExcel.getCellValue(fileName,sheetName,rownowithiterartion,0))){
							testCaseFound =true;
							String Key =readExcel.getCellValue(fileName,sheetName, 0, jCol);
							if(Key.contentEquals(columnName)){
								colmnFound = true;
								val =readExcel.getCellValue(fileName,sheetName, rownowithiterartion, jCol);
								break;
							}
						}else{
							LogFileControl.logFail("Iteration No: "+iteration, "For this iteration Data not Present");
//							System.out.println("For Iteration no "+iteration+" : Data not Present");
						}
					} 
					if(colmnFound==false){
						LogFileControl.logFail("Column Name: "+columnName, columnName+" Column is not available under the Excel Sheet");
					}
					//col loop
					break;
				} //end of conditional statement 

			}//row loop
			if(testCaseFound==false){
				LogFileControl.logFail("Test Case ID: "+scriptid, "Test case not found under the excel sheet");
			}
		}catch(Exception e){}


		return val; 

	}

//	public static void main(String args[]) throws Exception{
//		Date date = new Date();
//		long sTime = System.nanoTime();
//		System.out.println("Start Time.......... "+new SimpleDateFormat("yy-MM-dd hh:mm:ss:SSSS").format(date).toString()+"  "+ System.nanoTime());
//		DataProvider dataProvider = new DataProvider();
//		String val = dataProvider.getDataLogic("GeoTree_create","GeoTreeAssignment","geoTreeAssignment_ValidateBreadcrumbs",1,"UserName");
//		System.out.println(val);
//		long ttime = System.nanoTime() - sTime;
//		System.out.println("End Time.......... "+new SimpleDateFormat("yy-MM-dd hh:mm:ss:SSSS").format(date).toString()+"   " +ttime);
//	}

	HashMap<String, String> getDataLogic(String filename,String sheetName,String scriptid,int iteration) throws Exception{
		HashMap<String, String> vals = new HashMap<String, String>();
		String val = null;
		String fileName = null;
		String filePath=null;
		try{
			if(filename.contains("_")){
				String fil[] =filename.split("_");
				for(int i=0;i<fil.length-1;i++){
					filePath=System.getProperty("user.dir") +"\\DataTable\\";
					filePath=filePath+fil[i]+"\\";
				}
				filePath = filePath+filename+".xlsx";
				File f2 = new File(filePath);
				if(f2.exists() && !f2.isDirectory()) {
					fileName=filePath;
				}
			}else{
				filePath=System.getProperty("user.dir") +"\\DataTable\\"+filename+".xlsx";
				File f = new File(filePath);
				if(f.exists() && !f.isDirectory()) {
					fileName=filePath;
				}
				else{
					File file = new File(System.getProperty("user.dir") +"\\DataTable");
					String[] names = file.list();

					for(String name : names)
					{
						if (new File(System.getProperty("user.dir") +"\\DataTable").isDirectory())
						{
//							System.out.println(name);
							filePath =System.getProperty("user.dir") +"\\DataTable\\"+name+"\\"+filename+".xlsx";
							File f1 = new File(filePath);
							if(f1.exists() && !f1.isDirectory()) { 
								fileName = filePath;
								break;
							}
						}
					}
				}
			}
			HashMap<String, Integer> countDetails=readExcel.getCount(fileName, sheetName);
			int rowcount = countDetails.get("RowCount");
			int colcount = countDetails.get("ColumnCount");
			for(int iRow =1;iRow<=rowcount ;iRow++){
				String getvalue=readExcel.getCellValue(fileName,sheetName,iRow,0);
				if(getvalue.equals(scriptid)){
					for(int jCol = 1 ;jCol<colcount;jCol++){
						int rownowithiterartion=iRow+(iteration-1);
						if(scriptid.equals(readExcel.getCellValue(fileName,sheetName,rownowithiterartion,0))){
							String Key =readExcel.getCellValue(fileName,sheetName, 0, jCol);
//							if(Key.contentEquals(columnName)){
								val =readExcel.getCellValue(fileName,sheetName, rownowithiterartion, jCol);
								vals.put(Key, val);
//								break;
//							}
						}else{
							System.out.println("For Iteration no "+iteration+" : Data not Present");
						}
					}             //col loop
					break;
				} //end of conditional statement 

			}//row loop

		}catch(Exception e){}


		return vals; 

	}
}
