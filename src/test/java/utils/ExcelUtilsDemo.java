package utils;

public class ExcelUtilsDemo {

	public static void main(String[] args) {
		//String projectPath = System.getProperty("user.dir");
		ExcelUtils excel = new ExcelUtils("D:\\Selenium Scripts for Testing\\maven-testng\\data\\Testdata.xlsx", "sheet1");
		
		excel.getRowCount();
		excel.getColCount();
		
		
		excel.getCellDataInt(1,3);
		excel.getCellDataString(1,1);

	}

}
