$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("PetStatus.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#/**"
    },
    {
      "line": 2,
      "value": "#Author: Sudhakar Madamala"
    },
    {
      "line": 3,
      "value": "#Date:04/03/2020"
    },
    {
      "line": 4,
      "value": "#History: Updated background and pet count data"
    },
    {
      "line": 5,
      "value": "#/"
    }
  ],
  "line": 6,
  "name": "",
  "description": "  To perform a get request on the PetStore API based on the Pet Status\n  validate the response payload for the status and elements for the data provided",
  "id": "",
  "keyword": "Feature"
});
formatter.background({
  "line": 10,
  "name": "Setup API service URL based on the HTTP status available",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 11,
  "name": "The Pet Store API service is available",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "I set the appropriate URL for the service",
  "keyword": "Then "
});
formatter.match({
  "location": "PetScenarioSteps.the_Swagger_Petstore_API_service_is_available()"
});
formatter.result({
  "duration": 3143410058,
  "status": "passed"
});
formatter.match({
  "location": "PetScenarioSteps.ascertainTheAppropriatePetStatusURL()"
});
formatter.result({
  "duration": 537524,
  "status": "passed"
});
formatter.scenario({
  "line": 14,
  "name": "Verify PetStore API response for the status and pets name",
  "description": "",
  "id": ";verify-petstore-api-response-for-the-status-and-pets-name",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 15,
  "name": "I request PetStore application for the status data below",
  "rows": [
    {
      "cells": [
        "Status"
      ],
      "line": 16
    },
    {
      "cells": [
        "available"
      ],
      "line": 17
    }
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "I verify the response for the Status , Pet name and Pet count for the data below",
  "rows": [
    {
      "cells": [
        "Status",
        "PetName",
        "PetCount"
      ],
      "line": 19
    },
    {
      "cells": [
        "available",
        "doggie",
        "3135"
      ],
      "line": 20
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "PetScenarioSteps.iRequestPetStoreApplicationForTheStatusDataBelow(DataTable)"
});
formatter.result({
  "duration": 632075897,
  "status": "passed"
});
formatter.match({
  "location": "PetScenarioSteps.iVerifyTheResponseForTheStatusPetNameAndPetCountForTheDataBelow(DataTable)"
});
formatter.result({
  "duration": 1121098327,
  "error_message": "java.lang.AssertionError: **** Pet count not matched... expected:\u003c3135\u003e but was:\u003c3132\u003e\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat org.junit.Assert.failNotEquals(Assert.java:834)\n\tat org.junit.Assert.assertEquals(Assert.java:645)\n\tat steps.PetScenarioSteps.iVerifyTheResponseForTheStatusPetNameAndPetCountForTheDataBelow(PetScenarioSteps.java:55)\n\tat ✽.Then I verify the response for the Status , Pet name and Pet count for the data below(PetStatus.feature:18)\n",
  "status": "failed"
});
});