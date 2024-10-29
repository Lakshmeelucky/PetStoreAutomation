package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//UserEndPoints.java
//Created for perform Create, Read, Update, Delete requests the user API's

public class UserEndpoints {
	
	//Create
	
	public static Response createUser(User payload)
	{
		Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		
		.when()
		   .post(Routes.Post_url);
		
		return response;
		
	}
	
	//Read(Retrive)
	
	public static Response readUser(String username)
	{
		Response response=given()
		   .pathParam("username", username)
		.when()
		   .get(Routes.Get_url);
		
		return response;
		
	}
	
	//Update
	
	public static Response updateUser(String username, User payload)
	{
		Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .pathParam("username", username)
		   .body(payload)
		
		.when()
		   .put(Routes.Update_url);
		
		return response;
		
	}
	
	//Delete
	
	public static Response deleteUser(String username)
	{
		Response response=given()
		   .pathParam("username", username)
		.when()
		   .delete(Routes.Delete_url);
		
		return response;
		
	}
	
	
	

}
