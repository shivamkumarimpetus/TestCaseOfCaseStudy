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

public class test1 {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
       
	
	    @BeforeMethod
		void setup()
		{
        htmlReporter = new ExtentHtmlReporter("SignUp_of_new User.html");
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
		void test_02() throws IOException
		{
			ExtentTest test1 = extent.createTest("SignUp Test of new User", "This is a test to Check if a new user SignUp");
			 RequestSpecification request = RestAssured.given();
		        request.header("content-Type" , "application/json");
				JSONObject json = new JSONObject();
				
				json.put("firstName", "deepika");
				json.put("lastName", "kumari");
				json.put("emailId" ,"deepikaa@gmail.com");
				json.put("password","123456000");
				request.body(json.toJSONString());
				Response response = request.post("/registeruser");
				int code = response.statusCode();
				
				
				if(code == 200) {
			    
			    test1.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 200 Expected");
			    test1.info("The Info of the New User is Being Validating");
			    test1.log(Status.PASS, "User Signed Up Successfully");
			    Assert.assertEquals(code, 200);
				}
				
				if(code != 200)
				{
				
				test1.log(Status.INFO, "The Actual Status Code found is "+ ""+ code + " " +" but 200 Expected");
			    test1.info("User Already Existed Please Use Different Email Address For SignUp");
			    test1.log(Status.FAIL, "The User Signed failed because the user already existed");
				Assert.assertEquals(code, 200);
				}
				
				
		}
		
		@AfterMethod
		void tearDown() {
			extent.flush();
		}

}
