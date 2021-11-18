package utils;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExcelDataProvider {
	

	@BeforeMethod
	void setup()
	{
		RestAssured.baseURI = "http://localhost:8067";
	}

	
	@Test(priority =1,dataProvider = "test2data")
	void test1(String fullName , String email, String writerId, String password)
	{
		 RequestSpecification request = RestAssured.given();
         request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();
		
		
		json.put("fullName" ,fullName);
		json.put("emailId" ,email);
		json.put("writerId" ,writerId);
		json.put("password",password);
		request.body(json.toJSONString());
		Response response = request.post("/createUW");
		int code = response.statusCode();
		Assert.assertEquals(code, 200);
	}
	
	
	
	@Test(priority =2,dataProvider = "test1data")
	void test(String firstName , String lastName , String  Email,  String Password ) {


		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();

		json.put("firstName", firstName);
		json.put("lastName", lastName);
		json.put("emailId" ,Email);
		json.put("password",Password);
		request.body(json.toJSONString());
		Response response = request.post("/registeruser");
		int code = response.statusCode();
		Assert.assertEquals(code, 500);
	}
	@Test(dataProvider = "test1data")
	void test2(String  Email,  String Password ) {


		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();

		json.put("emailId" ,Email);
		json.put("password",Password);
		request.body(json.toJSONString());
		Response response = request.post("/login");
		int code = response.statusCode();
		Assert.assertEquals(code, 200);
	}
	
	
	
	


	@DataProvider(name = "test1data")
	public static Object getData() {
		String excelPath = "D:\\Selenium Scripts for Testing\\maven-testng\\data\\Testdata.xlsx";
		testData(excelPath , "sheet1");
		Object data[][] = testData(excelPath , "sheet1");
		return data;

	}

	
	
	
	
	
	@DataProvider(name = "test2data")
	public static Object getData2() {
		String excelPath = "D:\\Selenium Scripts for Testing\\maven-testng\\data\\Testdata.xlsx";
		testData1(excelPath , "userLoginSheet");
		Object data[][] = testData(excelPath , "userLoginSheet");
		return data;
	}

	
	

	private static Object[][] testData1(String excelPath, String sheetName) {
		ExcelUtils excel = new ExcelUtils(excelPath,sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		
		System.out.println(rowCount);
		System.out.println(colCount);

		Object data1[][] = new Object[rowCount-1][colCount];

		for(int i=1 ; i<= rowCount-1 ;i++) {
			for (int j =0;j<=colCount-1;j++) {
				String cellData = excel.getCellDataString(i, j);
				data1[i-1][j] =cellData;
			}
		}
		return data1;
	}
	
	
	

	
	
	public static Object[][] testData(String excelPath, String sheetName)
	{
		ExcelUtils excel = new ExcelUtils(excelPath,sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data[][] = new Object[rowCount-1][colCount];

		for(int i=1 ; i<= rowCount-1 ;i++) {
			for (int j =0;j<=colCount-1;j++) {
				String cellData = excel.getCellDataString(i, j);
				data[i-1][j] =cellData;
			}
		}
		return data;
	}



	
	
	

}
