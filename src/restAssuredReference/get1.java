package restAssuredReference;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class get1 
{

	public static void main(String[] args) 
	{
		// declare base URI
		String baseuri="https://reqres.in/";
		RestAssured.baseURI=baseuri;
		
		// configure 
		int statusCode=given().header("Content-Type","application/json").when().get("api/users?page=2").then().extract().statusCode();
		System.out.println(statusCode);
		String responseBody=given().header("Content-Type","application/json").when().get("api/users?page=2").then().extract().response().asString();
		System.out.println(responseBody);
		
		// expected result 
		int id [] = {7, 8, 9, 10, 11, 12};
		String first_name []= {"Michael","Lindsay", "Tobias", "Byron", "George", "Rachel"};
		String last_name [] = {"Lawson","Ferguson","Funke", "Fields", "Edwards", "Howell"};
		String avatar []={"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg","https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
	    String email []= {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
		
		
		JsonPath jspResponse=new JsonPath(responseBody);
		 int count = jspResponse.getList("data").size();
		 int count1 = id.length;
		 System.out.println(count1);
		 //validate each object 
		 for(int i=0;i<count; i++)
		 {
			 // expected result
			 int exp_id = id[i];
		     String exp_first_name = first_name[i];
		     String exp_last_name = last_name[i];
		     String exp_email = email[i];
		     String exp_avatar = avatar[i];

			 String res_id=jspResponse.getString("data["+i+"].id");
			 int  res_int_id=Integer.parseInt(res_id);
			 String res_first_name=jspResponse.getString("data["+i+"].first_name");
			 String res_last_name=jspResponse.getString("data["+i+"].last_name");
			 String res_email=jspResponse.getString("data["+i+"].email");
			 String res_avatar=jspResponse.getString("data["+i+"].avatar");
			 
			 // validate
			 Assert.assertEquals(res_int_id, exp_id,"ID at index " + i);
	         Assert.assertEquals(res_first_name, exp_first_name, "first_name at index " + i);	
	         Assert.assertEquals(res_last_name, exp_last_name, "last_name at index " + i);	
	         Assert.assertEquals(res_email, exp_email, "email at index " + i);
	         Assert.assertEquals(res_avatar, exp_avatar, "avatar at index " + i);	
	      }
    }
}
