package com.automation.PolicyCenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadCSV {

	static ReadCSV ReadCSV = new ReadCSV();
	public static void main(String[] args) {

		System.out.println(ReadCSV.getCSVValue("PromoDeal_DiscountByPercentage", "PROMO001", "Buy Type", 1));

	}


	@SuppressWarnings("resource")
	public String getCSVValue(String fileName,String scriptID,String colmnName,int iteration){
		String testData;
		String value=null;
		try {
			String filename = null;
			String filePath=null;
				if(fileName.contains("_")){
					String fil[] =fileName.split("_");
					filePath=System.getProperty("user.dir") +"\\DataTable\\";
					for(int i=0;i<fil.length-1;i++){
						filePath=filePath+fil[i]+"\\";
					}
					filePath = filePath+fileName+".csv";
					File f2 = new File(filePath);
					if(f2.exists() && !f2.isDirectory()) {
						filename=filePath;
					}
				}else{
					filePath=System.getProperty("user.dir") +"\\DataTable\\"+fileName+".csv";
					File f = new File(filePath);
					if(f.exists() && !f.isDirectory()) {
						filename=filePath;
					}
					else{
						File file = new File(System.getProperty("user.dir") +"\\DataTable");
						String[] names = file.list();

						for(String name : names)
						{
							if (new File(System.getProperty("user.dir") +"\\DataTable").isDirectory())
							{
//								System.out.println(name);
								filePath =System.getProperty("user.dir") +"\\DataTable\\"+name+"\\"+fileName+".csv";
								File f1 = new File(filePath);
								if(f1.exists() && !f1.isDirectory()) { 
									filename = filePath;
									break;
								}
							}
						}
					}
				}
			
			BufferedReader lineBuffer = new BufferedReader(new FileReader(filename));
			boolean testCaseStatus = false;
			int colNo=getColmnNumber(lineBuffer.readLine(), colmnName);
			while ((testData = lineBuffer.readLine()) != null){
				String[] splitData = testData.split("\\s*,\\s*");
				if (!(splitData[0] == null) || !(splitData[0].length() == 0)) {
					if(splitData[0].trim().equals(scriptID)){
						testCaseStatus=true;
						if(iteration==1){
							try{
							value = splitData[colNo];
							}catch(Throwable e){
								System.out.println(e);
							}
							break;
						}else{
							for(int it=0;it<iteration-1;it++){
								try{
									testData = lineBuffer.readLine();
								}catch(NullPointerException e){
									testData=null;
									LogFileControl.logError("Iteration not present");
								}
							}
							if(testData!=null){
								splitData = testData.split("\\s*,\\s*");
								if (!(splitData[0] == null) || !(splitData[0].length() == 0)) {
									if(splitData[0].trim().equals(scriptID)){
										value = splitData[colNo];
									}else{
										LogFileControl.logError("Iteration not present");
									}
								}
							}else{
								LogFileControl.logError("Iteration not present");
							}
						}

						break;
					}
				}
			}

			if(testCaseStatus==false){
				LogFileControl.logError("Test case not present");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return value;

	}
	// Utility which converts CSV to ArrayList using Split Operation
	public int getColmnNumber(String crunchifyCSV, String colmnName) {
		int i =0;
		boolean colmnStatus=false;
		if (crunchifyCSV != null) {
			String[] splitData = crunchifyCSV.split("\\s*,\\s*");
			for (i = 0; i < splitData.length; i++) {
				if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
					if(splitData[i].trim().equals(colmnName)){
						colmnStatus=true;
						break;
					}
					//					crunchifyResult.add(splitData[i].trim());
				}
			}
			if(colmnStatus==false){
				System.out.println("Colmn Name not found");
			}
		}

		return i;
	}

}
