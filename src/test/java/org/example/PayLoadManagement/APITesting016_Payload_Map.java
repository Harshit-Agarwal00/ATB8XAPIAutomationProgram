package org.example.PayLoadManagement;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class APITesting016_Payload_Map {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void test_post() {
//        String payload_POST = "{\n" +
//                "    \"firstname\" : \"Pramod\",\n" +
//                "    \"lastname\" : \"Dutta\",\n" +
//                "    \"totalprice\" : 111,\n" +
//                "    \"depositpaid\" : false,\n" +
//                "    \"bookingdates\" : {\n" +
//                "        \"checkin\" : \"2024-01-01\",\n" +
//                "        \"checkout\" : \"2024-01-01\"\n" +
//                "    },\n" +
//                "    \"additionalneeds\" : \"Lunch\"\n" +
//                "}";

        Map<String, Object> stringObjectsMap = new LinkedHashMap<>();
        stringObjectsMap.put("firstname", "Pramod");
        stringObjectsMap.put("lastname", "Dutta");
        stringObjectsMap.put("totalprice", 111);
        stringObjectsMap.put("depositpaid", false);

        Map<String, Object> bookingDateMap = new LinkedHashMap<>();
        bookingDateMap.put("checkin", "2024-01-01");
        bookingDateMap.put("checkout", "2024-01-01");

        stringObjectsMap.put("bookingdates", bookingDateMap);
        stringObjectsMap.put("additionalneeds", "Lunch");
        System.out.println(stringObjectsMap);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(stringObjectsMap).log().all();

        Response response = requestSpecification.when().post();

        Integer bookingId = response.then().extract().path("bookingid");
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println(bookingId);
    }
}
