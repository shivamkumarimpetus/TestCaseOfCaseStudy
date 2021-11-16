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

public class Test2 {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeMethod
	void setup()
	{
		htmlReporter = new ExtentHtmlReporter("UserForm_Filling.html");
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
	void test_03()
	{
		ExtentTest test3 = extent.createTest("User Form filling", "This is a test to Check if a user is able to fill life insurance form or not");
		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();

		json.put("firstName", "nitish ");
		json.put("middleName", "kumar ");
		json.put("lastName", " singh");
		json.put("aadhar", "123456654321");
		json.put("email" ,"shivdfam@gmail.com");
		json.put("pan", "ASDER2345A");
		json.put("address", "noida ");
		json.put("zip", "323267");
		json.put("city", "noida");
		json.put("state", " uttar-pradesh");
		json.put("contact", "9877899879");
		json.put("dateOfBirth", "10/10/1991 ");
		json.put("occupation", " Engineer");
		json.put("income", "5");
		json.put("selectPlane", "2 ");
		json.put("gender", "Male ");
		json.put("healthIssue", "No");
		json.put("cancellingInsurance", "No");
		json.put("groupInsurance", "No");
		json.put("tobacco", "No");
		json.put("hivIssue", "No");
		json.put("lungsIssue", "No");
		json.put("additionalComments", "No");
		json.put("member", "Individual");
		

		request.body(json.toJSONString());
		Response response = request.post("/registerlifeservice");

		int code = response.statusCode();
		if(code == 200)
		{
			test3.info("the Credentials of the the form is being Validating");
			test3.log(Status.PASS, "User Form Submitted Successfully");
			Assert.assertEquals(code, 200);
		}

		if(code != 200)
		{
			test3.info("the Credentials of the the form is being Validating");
			test3.log(Status.FAIL, "User Form didn't Submitted Please Recheck it");
			Assert.assertEquals(code, 200);
		}

	}



	@AfterMethod
	void tearDown() {
		extent.flush();
	}


}
