package maventestng;
import java.io.IOException;

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

public class Test3 {
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
	void test_06() 
	{
		ExtentTest test3 = extent.createTest("Underwriter Creation Test", "This is a test to create New Admin By Generating New Underwriter User name and password");
		
		   RequestSpecification request = RestAssured.given();
	          request.header("content-Type" , "application/json");
			JSONObject json = new JSONObject();
			
			
			json.put("fullName" ,"shivamkumarsingh");
			json.put("emailId" ,"deepika@gmail.com");
			json.put("writerId" ,"uw4");
			json.put("password","123456000");
			request.body(json.toJSONString());
			Response response = request.post("/createUW");
			int code = response.statusCode();
			
			
			if(code == 200) {
		    
		    test3.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 200 Expected");
		    
		    test3.info("The New Underwriter Details Are being Validating.");
		    test3.info("The Entered Underwriter do not already exist.");
		    test3.log(Status.PASS, "Underwriter Created Successfully");
		    Assert.assertEquals(code, 200);
			}
			
			if(code != 200)
			{
			
		    test3.info("This Underwriter with these credentials Already Present in Database still how it is able to Signup");
		    test3.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 200 Expected");
		    test3.log(Status.FAIL, "The Underwriter Already Exist" + "Underwriter Already Existed");
			Assert.assertEquals(code, 200);
			}
				
	}
	
	 @AfterMethod
		void tearDown() {
			extent.flush();
		}
	

}
