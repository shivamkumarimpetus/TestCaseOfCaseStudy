package utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static void main(String[] args) {
		getRowCount();
	//	getCellData();
	}
	
	
	public static void getRowCount() {
		
		
		try {
			String excelPath = "./data/Testdata.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
			XSSFSheet sheet =workbook.getSheet("sheet1");
			DataFormatter formatter = new DataFormatter();
			Object value = formatter.formatCellValue(sheet.getRow(0).getCell(2));
			System.out.println(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
