package restAssuredReference;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class delete1 
{

	public static void main(String[] args) 
	{
		 //Step 1 : Declare Base Url
		  RestAssured.baseURI="https://reqres.in/";
					
		  //Step 2 : Configure Request Body
		   int statusCode=given().header("Content-Type","application/json").when().delete("/api/users/2").then().extract().statusCode();
		   System.out.println(statusCode);
	}

}
