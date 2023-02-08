package stepDefinitions;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        // Write a code that gives you place id
        // And execute this method when place id is null in  DeletePlace Scenario


        if (PlaceValidationStepDef.placeId == null) {
            PlaceValidationStepDef stepDef = new PlaceValidationStepDef();
            stepDef.add_place_payload("kekeHouse", "German", "Russia");
            stepDef.user_calls_with_http_request("AddPlaceApi", "post");
            stepDef.verify_place_id_created_maps_to_using("kekeHouse", "getPlaceApi");
        }

    }
}
