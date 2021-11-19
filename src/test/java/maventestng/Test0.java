package maventestng;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.aventstack.extentreports.ExtentTest.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Test0 {


	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;


	@BeforeMethod
	void setup()
	{
		htmlReporter = new ExtentHtmlReporter("NewReport.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Test Result");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.setSystemInfo("Impetus", "Insurance Application Tests");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);
		RestAssured.baseURI = "http://localhost:8067";
	}


	@Test
	void  test_01() throws IOException
	{

		String excelPath = "./data/Testdata.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
		XSSFSheet sheet =workbook.getSheet("sheet1");
		DataFormatter formatter = new DataFormatter();

		for(int i =1;i<=15;i++) {
			Object value0 = formatter.formatCellValue(sheet.getRow(i).getCell(0));
			Object value1 = formatter.formatCellValue(sheet.getRow(i).getCell(1));
			Object value2 = formatter.formatCellValue(sheet.getRow(i).getCell(2));
			Object value3 = formatter.formatCellValue(sheet.getRow(i).getCell(3));

			//ExtentTest test1 = extent.createTest("SignUp Test of Already Registered People", "This is a test to Check if a user is already present in Database then is should not signup Again with Same Email");
			RequestSpecification request = RestAssured.given();
			request.header("content-Type" , "application/json");
			JSONObject json = new JSONObject();

			json.put("firstName",value0 );
			json.put("lastName", value1);
			json.put("emailId" ,value2);
			json.put("password",value3);
			request.body(json.toJSONString());
			Response response = request.post("/registeruser");
			
//			int code = response.statusCode();
//
//
//			if(code == 500) {
//				test1.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 500 Expected");
//				test1.info("This User cannot Sign-Up as it is Already Present in Our Database Please Login with this Email");
//				test1.log(Status.PASS, "User Already Existed Please Use Different Email Address For SignUp");
//
//				Assert.assertEquals(code, 500);
//			}
//
//			if(code != 500)
//			{
//
//				test1.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 500 Expected");
//				test1.info("This User Is Already Present in Database still how it is able to Signup");
//				test1.log(Status.FAIL, "The User Signed up Successfully it is supposed to Give error" + "User Already Existed");
//				Assert.assertEquals(code, 500);
//			}
		}
	}




	@AfterMethod
	void tearDown() {
		extent.flush();
	}

}
