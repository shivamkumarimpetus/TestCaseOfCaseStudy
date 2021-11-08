package CaseStudyAllTestone;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class casestudytestcases {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;



	@BeforeSuite
	void setup()
	{
		htmlReporter = new ExtentHtmlReporter("extent11.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		RestAssured.baseURI = "http://localhost:8067";
	}

	@Test(priority=1)
	void test_01() throws IOException
	{ 


		ExtentTest test1 = extent.createTest("SignUp Test of Already Registered User", "This is a test to Check if a user is already present in Database then is should not signup Again with Same Email");
		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();

		json.put("firstName", "deepak");
		json.put("lastName", "shivhare");
		json.put("emailId" ,"daeepak@dafgs.com");
		json.put("password","123456000");
		request.body(json.toJSONString());
		Response response = request.post("/registeruser");
		int code = response.statusCode();


		if(code == 500) {
			test1.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 500 Expected");
			test1.info("This User cannot Sign-Up as it is Already Present in Our Database Please Login with this Email");
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


	@Test(priority=5)
	void test_03()
	{
		ExtentTest test3 = extent.createTest("User Form filling", "This is a test to Check if a user is able to fill life insurance form or not");
		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();

		json.put("firstName", "nitish ");
		json.put("middleName", "kumar ");
		json.put("lastName", " singh");
		json.put("aadhar", "123456789198");
		json.put("email" ,"shivamkumar@gmail.com");
		json.put("pan", "ASDER2345F");
		json.put("address", "noida ");
		json.put("zip", "323267");
		json.put("city", "noida");
		json.put("state", " uttar-pradesh");
		json.put("contact", "9877899870 ");
		json.put("dateOfBirth", "10/10/1991");
		json.put("occupation", " Engineer");
		json.put("income", "5");
		json.put("selectPlane", "2 Years");
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
		}

		if(code != 200)
		{
			test3.info("the Credentials of the the form is being Validating");
			test3.log(Status.FAIL, "User Form didn't Submitted Please Recheck it");
		}

	}

	@Test(priority=3)
	void test_04() 
	{

		ExtentTest test3 = extent.createTest("Underwriter Creation Test", "This is a test to create New Admin By Generating New Underwriter User name and password");

		RequestSpecification request = RestAssured.given();
		request.header("content-Type" , "application/json");
		JSONObject json = new JSONObject();


		json.put("fullName" ,"shivamkumarsingh");
		json.put("emailId" ,"shivamkumarkush@gmail.com");
		json.put("writerId" ,"uw6");
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


	@Test(priority=4)
	void test_05() 
	{
		ExtentTest test3 = extent.createTest("Admin Creation Test", "This is a test to Check if a user is already present in Database then is should not signup Again with Same Email");
		
		   RequestSpecification request = RestAssured.given();
	          request.header("content-Type" , "application/json");
			JSONObject json = new JSONObject();
			
			
			json.put("fullName" ,"vaishali");
			json.put("emailId" ,"vaishali.r.sharmaa@gmail.com");
			json.put("adminId" ,"Admin5");
			json.put("password","123456000");
			request.body(json.toJSONString());
			Response response = request.post("/createadmin");
			
			int code = response.statusCode();
			
			
			if(code == 200) {
		    
		    test3.log(Status.INFO, "The Actual Status Code found is "+" " + code +" "+ "and 200 Expected");
		    test3.info("the Credentials of the new Admin is being validating");
		    test3.log(Status.PASS, "Admin Created Successfully by adminId : Admin4");
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
	
	
	@Test(priority=2)
	void test_02() throws IOException
	{
		ExtentTest test1 = extent.createTest("SignUp Test of new User", "This is a test to Check if a new user SignUp");
		 RequestSpecification request = RestAssured.given();
	        request.header("content-Type" , "application/json");
			JSONObject json = new JSONObject();
			
			json.put("firstName", "payal");
			json.put("lastName", "kumari");
			json.put("emailId" ,"kmpayal1@gmail.com");
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
	
	@AfterSuite
	void tearDown() {
		extent.flush();
	}






}
