package org.example.RestAssureBasic.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITesting005_BDDStyleGET {

    @Test
    public void test_Get_Request_POSITIVE()
    {
        String pin_code = "342001";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/" + pin_code)
                .when().log().all()
                .get()
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void test_Get_Request_NEGATIVE()
    {
        String pin_code = "-1";

        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/" + pin_code)
                .when().log().all()
                .get()
                .then().log().all()
                .statusCode(200);
    }
}
