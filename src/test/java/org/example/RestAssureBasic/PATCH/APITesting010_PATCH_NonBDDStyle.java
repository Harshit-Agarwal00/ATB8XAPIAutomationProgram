package org.example.RestAssureBasic.PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting010_PATCH_NonBDDStyle {

    @Description("Verify Put Request")
    @Test
    public void test_Put_NonBDDStyle()
    {
        String Token = "d11512b55835c3f";
        String ID = "111";
        String Payload = "{\n" +
                "    \"firstname\": \"Harshit\",\n" +
                "    \"lastname\": \"Agarwal\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        //Request
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/" + ID);
        r.contentType(ContentType.JSON);
        r.cookie("token", Token);
        r.body(Payload).log().all();
        //When
        Response response = r.when().patch();
        //Then
        ValidatableResponse validatetableResponse = response.then().log().all();
        validatetableResponse.statusCode(200);
    }
}
