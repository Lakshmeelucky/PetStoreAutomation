package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//UserEndPoints.java
//Created for perform Create, Read, Update, Delete requests the user API's

public class UserEndpoints2 {
	//method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //Load properties file  //name of the properties file
		return routes;
		
	}
	
	//Create
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		
		Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)
		
		.when()
		   .post(post_url);
		
		return response;
		
	}
	
	//Read(Retrive)
	
	public static Response readUser(String username)
	{
		String get_url=getURL().getString("get_url");
		
		Response response=given()
		   .pathParam("username", username)
		.when()
		   .get(get_url);
		
		return response;
		
	}
	
	//Update
	
	public static Response updateUser(String username, User payload)
	{ 
		String update_url=getURL().getString("update_url");
		
		Response response=given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .pathParam("username", username)
		   .body(payload)
		
		.when()
		   .put(update_url);
		
		return response;
		
	}
	
	//Delete
	
	public static Response deleteUser(String username)
	{
		String delete_url=getURL().getString("delete_url");
		
		Response response=given()
		   .pathParam("username", username)
		.when()
		   .delete(delete_url);
		
		return response;
		
	}
	
	
	

}
