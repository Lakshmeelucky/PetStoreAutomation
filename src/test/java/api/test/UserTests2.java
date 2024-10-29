package api.test;

import java.util.logging.LogManager;

import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
	User userPayload;
	
	public Logger logger;  //for logs
    
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
		
	}
	
	// Create 
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("********* Creaing user ***********");
		Response response=UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	    
		logger.info("********* User is created  ***********");
	}
	
	//Get
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("********* Reading user info ***********");
		Response response=UserEndpoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********* user info is displayed ***********");
	}
	
	//Update
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("********* Updating User ***********");
		//Update data using Payload
		
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********* user is updated ***********");
		
		//Checking data after update
		
		Response responseAfterupdate=UserEndpoints.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
		}
	
	//Delete
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("********* Deleting user ***********");
		
		Response response=UserEndpoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********* user deleted***********");
	}
}
