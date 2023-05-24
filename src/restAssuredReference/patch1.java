package restAssuredReference;

import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class patch1 
{

	public static void main(String[] args) 
	{
		//Step 1 : Declare Base Url
				String	baseurl="https://reqres.in/";
				RestAssured.baseURI=baseurl;
				
				//step 2.1 : save request body in local variable
				String requestBody="{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}";
				
				// step 2.2 : extract request body parameters
				JsonPath jspRequest=new JsonPath(requestBody);
				String reqName=jspRequest.getString("name");
				String reqJob=jspRequest.getString("job");

				//step 2.3 : set expected date
				String expDate=LocalDateTime.now().toString().substring(0, 10);
				
				//Step 3.1 : Configure Request Body
				int statusCode=given().header("Content-Type","application/json").body(requestBody).when().patch("/api/users/2").then().extract().statusCode();
				System.out.println(statusCode);

				String responseBody=given().header("Content-Type",
						"application/json").body(requestBody).when().patch("/api/users/2").then().extract().response().asString();
				System.out.println(responseBody);
				
				//Step 3.2 : Parse the response Body
				JsonPath jsp = new JsonPath(responseBody);
				String resName=jsp.getString("name"); 
				String resJob=jsp.getString("job");
				String resUpdatedAt=jsp.getString("updatedAt");
				String actDate=resUpdatedAt.substring(0,10);	
							
				//Step 3.3 : Validate the response body parameters
				
				Assert.assertEquals(resName, reqName);
				Assert.assertEquals(statusCode, 200);
				Assert.assertEquals(resJob, reqJob);
				Assert.assertEquals(actDate, expDate);
	}

}
