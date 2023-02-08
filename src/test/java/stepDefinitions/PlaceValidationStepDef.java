package stepDefinitions;

import googleAddApiPojoClasses.AddPlace;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;


import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PlaceValidationStepDef extends Utils {

    RequestSpecification res;
    TestDataBuild testDataBuild = new TestDataBuild();
    Response response;
    ResponseSpecification responseSpecification;
    static String placeId;


    @Given("Add Place Payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        System.out.println("add place payload");

        AddPlace addPlace = testDataBuild.addPlaceData(name, language, address);
        res = given().spec(requestSpecification()).body(addPlace);

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        System.out.println("user call " + method + " request");

        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();


        String resourceApi = ApiResources.valueOf(resource).getResource();
        System.out.println(resourceApi);

        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceApi);
        } else if (method.equalsIgnoreCase("Get")) {
            response = res.when().get(resourceApi);
        } else if (method.equalsIgnoreCase("delete")) {
            response = res.when().delete(resourceApi);
        } else if (method.equalsIgnoreCase("put")) {
            response = res.when().put(resourceApi);
        } else {
            throw new RuntimeException("Unvalid http method call --> " + method);
        }


    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(int int1) {

        System.out.println("checking status code!");
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(), int1);
    }


    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {

        System.out.println("checking " + keyValue + " response");
        assertEquals(getJsonPath(response, keyValue), expectedValue);

    }


    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) throws IOException {

        // need to hit getApi code
        // requestSpecification

        placeId = getJsonPath(response, "place_id");

        res = RestAssured.given().spec(requestSpecification()).queryParam("place_id", placeId);
        user_calls_with_http_request(resourceName, "get");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);

    }


    @Given("DeletePlace payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayLoad(placeId));

    }


}
