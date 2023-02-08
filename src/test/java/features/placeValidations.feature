
@AddAndDeletePlace
Feature: Validating Place API's


  @AddPlace
  Scenario Outline: Verify if Place is being succesfully added using AddPlaceAPI

    Given Add Place Payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceApi"

    Examples:
      | name        | language | address      |
      | Guzel Yemek | english  | texas,USA    |
      | borekci     | french   | France,Paris |




    @DeletePlace
    Scenario: Verify if Delete Place functionality is working

      Given DeletePlace payload
      When user calls "deletePlaceApi" with "delete" http request
      Then the API call got success with status code 200
      And "status" in response body is "OK"



