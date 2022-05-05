Feature: Validating Place API

  Scenario: Verify if Place is being successfully added using AddPlaceAPI
    Given Add place payload
    When User calls "AddPlaceAPI" with Post http request
    Then The response call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
