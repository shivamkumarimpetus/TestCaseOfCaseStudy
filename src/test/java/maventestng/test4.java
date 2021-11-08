package maventestng;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class test4 {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	
	 @BeforeMethod
		void setup()
		{
	     htmlReporter = new ExtentHtmlReporter("Admin_Creatin.html");
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
		ExtentTest test3 = extent.createTest("Admin Creation Test", "This is a test to Check if a user is already present in Database then is should not signup Again with Same Email");
		
		   RequestSpecification request = RestAssured.given();
	          request.header("content-Type" , "application/json");
			JSONObject json = new JSONObject();
			
			
			json.put("fullName" ,"vaishali");
			json.put("emailId" ,"vaishali@gmail.com");
			json.put("adminId" ,"Admin3");
			json.put("password","123456000");
			request.body(json.toJSONString());
			Response response = request.post("/createadmin");
			
			int code = response.statusCode();
			
			
			if(code == 200) {
		    
		    test3.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 200 Expected");
		    test3.info("the Credentials of the new Admin is being validating");
		    test3.log(Status.PASS, "Admin Created Successfully");
		    Assert.assertEquals(code, 200);
			}
			if(code != 200)
			{
			
		    test3.info("This Admin with these credentials Already Present in Database still how it is able to Signup");
		    test3.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 200 Expected");
		    test3.log(Status.FAIL, "The Admin Already Exist" + "Underwriter Already Existed");
			Assert.assertEquals(code, 200);
			}
		
	}
	
	
	@Test
	void test_04() 
	{
		ExtentTest test3 = extent.createTest("Admin Creation Test", "This is a test to Check if a user is already present in Database then is should not signup Again with Same Email");
		
		   RequestSpecification request = RestAssured.given();
	          request.header("content-Type" , "application/json");
			JSONObject json = new JSONObject();
			
			
			json.put("fullName" ,"vaishali");
			json.put("emailId" ,"vaishali@gmail.com");
			json.put("adminId" ,"Admin3");
			json.put("password","123456000");
			request.body(json.toJSONString());
			Response response = request.post("/createadmin");
			
			int code = response.statusCode();
			
			
			if(code == 200) {
		    
		    test3.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 200 Expected");
		    test3.info("the Credentials of the new Admin is being validating");
		    test3.log(Status.PASS, "Admin Created Successfully");
		    Assert.assertEquals(code, 200);
			}
			if(code != 200)
			{
			
		    test3.info("This Admin with these credentials Already Present in Database still how it is able to Signup");
		    test3.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 200 Expected");
		    test3.log(Status.FAIL, "The Admin Already Exist" + "Underwriter Already Existed");
			Assert.assertEquals(code, 200);
			}
		
	}
	
	
	
	
	 @AfterMethod
		void tearDown() {
			extent.flush();
		}
	

}



