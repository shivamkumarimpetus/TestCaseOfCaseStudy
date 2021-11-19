package utils;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeSuite
	void setup()
	{   htmlReporter = new ExtentHtmlReporter("NewReport1.html");
	extent = new ExtentReports();
	extent.attachReporter(htmlReporter);   
	RestAssured.baseURI = "http://localhost:8067";
	}






	@Test(priority =1,dataProvider = "test1data")
	void test(String firstName , String lastName , String  Email,  String Password ) {



		ExtentTest test1 = extent.createTest("SignUp Test of Already Registered User", "This is a test to Check if a user is already present in Database then is should not signup Again with Same Email");
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


		if(code == 500) {
			test1.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 500 Expected");
			test1.info("This User cannot Sign-Up as it is Already Present in Our Database Please Login with this Email");
			test1.log(Status.INFO , "User with The Email"+ Email );
			test1.log(Status.PASS, "User Already Existed Please Use Different Email Address For SignUp");

			Assert.assertEquals(code, 500);
		}

		if(code != 500)
		{

			test1.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 500 Expected");
			test1.info("This User Is Already Present in Database still how it is able to Signup");
			test1.log(Status.FAIL, "The User Signed up Successfully it is supposed to Give error" + "User Already Existed");
			Assert.assertEquals(code, 500);
		}
	}
	@Test(priority =2,dataProvider = "test2data")
	void test2(String  Email,  String Password ) {

		ExtentTest test2 = extent.createTest("Login Test of Already Registered User", "This is a test to Check if a user is already present in Database then it should Login");
		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();

		json.put("emailId" ,Email);
		json.put("password",Password);
		request.body(json.toJSONString());
		Response response = request.post("/login");
		int code = response.statusCode();
		if(code == 200) {
			test2.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 200 Expected");
			test2.log(Status.INFO , "User with The Email  "+ Email );
			test2.info("This credential's of the user is Being Validating");
			test2.log(Status.PASS, "User Sign In Successful");

			Assert.assertEquals(code, 200);
		}

		if(code != 200)
		{

			test2.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 200 Expected");
			test2.log(Status.INFO , "User with The Email  "+ Email );
			test2.info("This credential's of the user is Being Validating");
			test2.log(Status.FAIL, "This User is Not present in Our Database or Bad Credential's");
			Assert.assertEquals(code, 200);
		}
	}



	@Test(priority = 3, dataProvider = "test3data")
	void test3(String fullName , String Email ,String writerId ,String Password) 
	{
		ExtentTest test3 = extent.createTest("Underwriter Creation Test", "This is a test to create New Admin By Generating New Underwriter User name and password");

		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();


		json.put("fullName" ,fullName);
		json.put("emailId" ,Email);
		json.put("writerId" ,writerId);
		json.put("password",Password);
		request.body(json.toJSONString());
		Response response = request.post("/createUW");
		int code = response.statusCode();


		if(code == 200) {

			test3.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 200 Expected");

			test3.info("The New Underwriter Details Are being Validating.");

			test3.log(Status.INFO, "The Email of the UnderWriter is  " + Email);
			test3.log(Status.INFO, "The User Id of Underwriter is  " + writerId);
			test3.info("The Entered Underwriter do not already exist.");
			test3.log(Status.PASS, "Underwriter Created Successfully");
			Assert.assertEquals(code, 200);
		}

		if(code != 200)
		{

			test3.info("This Underwriter with these credentials Already Present in Database still how it is able to Signup");
			test3.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 200 Expected");
			test3.log(Status.INFO, "The Email of the UnderWriter is  " + Email);
			test3.log(Status.INFO, "The User Id of Underwriter is  " + writerId);
			test3.log(Status.FAIL, "The Underwriter Already Exist");
			Assert.assertEquals(code, 200);
		}

	}




	@Test(priority = 4,dataProvider = "test4data")
	void test4(String fullName , String Email , String adminId , String password) 
	{
		ExtentTest test3 = extent.createTest("Admin Creation Test", "This is a test to Check if a user is already present in Database then is should not signup Again with Same Email");

		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();


		json.put("fullName" ,fullName);
		json.put("emailId" ,Email);
		json.put("adminId" ,adminId);
		json.put("password",password);
		request.body(json.toJSONString());
		Response response = request.post("/createadmin");

		int code = response.statusCode();


		if(code == 200) {

			test3.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 200 Expected");
			test3.log(Status.INFO, "The Email of the UnderWriter is  " + Email);
			test3.log(Status.INFO, "The User Id of Underwriter is  " + adminId);
			test3.info("the Credentials of the new Admin is being validating");
			test3.log(Status.PASS, "Admin Created Successfully");
			Assert.assertEquals(code, 200);
		}
		if(code != 200)
		{

			test3.info("This Admin with these credentials Already Present in Database still how it is able to Signup");
			test3.log(Status.INFO, "The Email of the UnderWriter is  " + Email);
			test3.log(Status.INFO, "The User Id of Underwriter is  " + adminId);
			test3.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 200 Expected");
			test3.log(Status.FAIL, "The Admin Already Exist" + "Underwriter Already Existed");
			Assert.assertEquals(code, 200);
		}

	}






	@DataProvider(name = "test1data")
	public static Object getData() {
		String excelPath = "D:\\Selenium Scripts for Testing\\maven-testng\\data\\Testdata.xlsx";
		Object data[][] = testData(excelPath , "sheet1");
		return data;

	}


	@DataProvider(name = "test2data")
	public static Object getData1() {
		String excelPath = "D:\\Selenium Scripts for Testing\\maven-testng\\data\\Testdata.xlsx";
		Object data[][] = testData1(excelPath , "userLoginSheet");
		return data;
	}


	@DataProvider(name = "test3data")
	public static Object getData2() {
		String excelPath = "D:\\Selenium Scripts for Testing\\maven-testng\\data\\Testdata.xlsx";
		Object data[][] = testData2(excelPath , "createUnderWriterSheet");
		return data;
	}
	@DataProvider(name = "test4data")
	public static Object getData3() {
		String excelPath = "D:\\Selenium Scripts for Testing\\maven-testng\\data\\Testdata.xlsx";
		Object data[][] = testData3(excelPath , "createAdminSheet");
		return data;
	}
	




	







	private static Object[][] testData3(String excelPath, String sheetName) {
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






	private static Object[][] testData2(String excelPath, String sheetName) {
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


	@AfterSuite
	void tearDown() {
		extent.flush();
	}







}
