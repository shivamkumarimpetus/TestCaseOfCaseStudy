package utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	

	public ExcelUtils(String excelPath , String sheetName) {
	try {
		String projectPath = System.getProperty("user.dir");
		workbook = new XSSFWorkbook(excelPath);
		sheet =workbook.getSheet( sheetName);

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}

	public static void main(String[] args) {
		getRowCount();
		 getCellDataString(0,0);
		 getCellDataInt(0,3);

	}


	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	static int getRowCount() {
//		try {
//			projectPath = System.getProperty("user.dir");
//			workbook = new XSSFWorkbook(projectPath + "/data/Testdata.xlsx");
//			XSSFSheet sheet = workbook.getSheet("sheet1");

			int rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("Number of Rows"+ " "+rowCount);
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}

     return rowCount;
	}

	public static String getCellDataString(int rowNum , int colNum) {
		
		String cellData = null;
//		try {
//			projectPath = System.getProperty("user.dir");
//			workbook = new XSSFWorkbook(projectPath + "/data/Testdata.xlsx");
//			XSSFSheet sheet = workbook.getSheet("sheet1");
			
			 cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			System.out.println("Value in Coloumn"+ " "+cellData);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
			return cellData;

	}
	
	
	public static void getCellDataInt(int rowNum , int colNum) {
//		try {
//			projectPath = System.getProperty("user.dir");
//			workbook = new XSSFWorkbook(projectPath + "/data/Testdata.xlsx");
//			XSSFSheet sheet = workbook.getSheet("sheet1");
			
			double colData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println("Value in Cell"+ " "+colData);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}

	}

	
	static int getColCount() {
//		try {
//			projectPath = System.getProperty("user.dir");
//			workbook = new XSSFWorkbook(projectPath + "/data/Testdata.xlsx");
//			XSSFSheet sheet = workbook.getSheet("sheet1");

			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("Number of Columns"+ " "+colCount);
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}

        return colCount;
	}





	

}
