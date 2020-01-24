package com.automation.PolicyCenter;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public XSSFWorkbook getWorkBook(String fileName){

		XSSFWorkbook workbook = null;
		try{
			if(fileName!=null){
				FileInputStream inputStream = new FileInputStream(fileName);
				try {
					workbook = new XSSFWorkbook(inputStream);
				} catch (Exception e) {
					System.out.println("Enter Correct excel work book Name");
				}
				}else{
				System.out.println("File Name can't Be null");
			}
		}catch(Exception e){}

		return workbook;
	}
	
	public HashMap<String, Integer> getCount(String fileName,String sheetName){
		HashMap<String, Integer> countDetails=new HashMap<String, Integer>();
		int rowCount=0,colCount=0;
		XSSFWorkbook workBook=getWorkBook(fileName);
		try{
			XSSFSheet worksheet=workBook.getSheet(sheetName);
			rowCount=worksheet.getLastRowNum();
			Row header = worksheet.getRow(0);
			colCount = header.getLastCellNum();
			countDetails.put("RowCount", rowCount);
			countDetails.put("ColumnCount", colCount);
		}catch(Exception e){
			System.out.println("Enter Correct excel sheet Name");
		}
		return countDetails;
		
	}
	
	public int getphysicalCount(String fileName,String sheetName,int rowNo){
		int colCount=0;
		XSSFWorkbook workBook=getWorkBook(fileName);
		try{
			XSSFSheet worksheet=workBook.getSheet(sheetName);
			Row header = worksheet.getRow(rowNo);
			colCount = header.getPhysicalNumberOfCells();
			
		}catch(Exception e){
			System.out.println("Enter Correct excel sheet Name");
		}
		return colCount;
		
	}
	
	public String getCellValue(String fileName,String sheetName, int row,int col){
		String data=null;
		XSSFWorkbook workBook=getWorkBook(fileName);
		try{
			XSSFSheet worksheet=workBook.getSheet(sheetName);
			try{
			data=worksheet.getRow(row).getCell(col).toString();
			}catch(Exception e){
				//System.out.println("Cell Value is Null");
			}
		}catch(Exception e){
			System.out.println("Enter Correct excel sheet Name");
		}
		return data;
		
	}
}
