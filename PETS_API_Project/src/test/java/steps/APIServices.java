///**
// Author: Sudhakar Madamala
// Date:01/03/2020,
// History: API Get service call
//*
package steps;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class APIServices {
    public Response getMethod(String url) {
        Response response=when().get(url);
        return response;
    }
}
