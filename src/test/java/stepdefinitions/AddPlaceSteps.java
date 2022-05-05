package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;
import pojo.LocationPojo;
import pojo.SerializePojo;

public class AddPlaceSteps {
	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	RequestSpecification res;
	
	@Given("Add place payload")
	public void add_place_payload() {
		SerializePojo s = new SerializePojo();
		s.setAccuracy(50);
		s.setName("Frontline house");
		s.setPhone_number("(+91) 983 893 3937");
		s.setAddress("29, side layout, cohen 09");
		s.setWebsite("http://google.com");
		s.setLanguage("French-IN");
		
		LocationPojo l = new LocationPojo();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		s.setLocation(l);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		s.setTypes(myList);
		
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
											.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		res = given().spec(req).body(s);
		
		//String responseString = response.asString();
		
		//System.out.println(responseString);
	}

	@When("User calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
	}

	@Then("The response call is success with status code {int}")
	public void the_response_call_is_success_with_status_code(Integer int1) {
	   assertEquals(response.getStatusCode(), 200);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(),expectedValue);
	}

}
