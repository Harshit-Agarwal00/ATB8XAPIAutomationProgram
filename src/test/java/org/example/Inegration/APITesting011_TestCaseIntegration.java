package org.example.Inegration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class APITesting011_TestCaseIntegration  {

    RequestSpecification r = RestAssured.given();;
    Response response;
    ValidatableResponse validatableResponse;

    String tokens;
    String bookingid ;

    public String getToken() {
        String Payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON).log().all();
        r.body(Payload);

        response = r.when().post();
        validatableResponse = response.then();
        validatableResponse.statusCode(200);

        tokens = response.jsonPath().getString("token");
        System.out.println(tokens);
        assertThat(tokens).isNotEmpty().isNotNull();
        return tokens;
    }

    public String getID() {
        String Payload_POST = "{\n" +
                "    \"bookingid\": 9090,\n" +
                "    \"booking\": {\n" +
                "        \"firstname\": \"Harshita\",\n" +
                "        \"lastname\": \"Agarwal\",\n" +
                "        \"totalprice\": 111,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2018-01-01\",\n" +
                "            \"checkout\": \"2019-01-01\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "    }\n" +
                "}";

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking");
        r.contentType(ContentType.JSON).log().all();
        r.body(Payload_POST);

        response = r.when().post();
        validatableResponse = response.then();
        validatableResponse.statusCode(200);

        bookingid = response.jsonPath().getString("bookingid");
        System.out.println(bookingid);
        assertThat(bookingid).isNotNull().isNotEmpty().isNotBlank();
        return bookingid;
    }

    @Test(priority = 1)
    public void test_update_request_put()
    {
        String token = getToken();
        System.out.println(token);
        String IDs = getID();
        System.out.println(IDs);

        String Payload_PUT = "{\n" +
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

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/" + bookingid);
        r.contentType(ContentType.JSON).log().all();
        r.body(Payload_PUT);

        response = r.when().put();

        validatableResponse = response.then();
        validatableResponse.statusCode(200);
    }

    @Test(priority = 2)
    public void test_update_request_get()
    {
        System.out.println(bookingid);

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/" + bookingid);
        response = r.when().log().all().get();
        r.then().log().all().statusCode(200);

        String firstName = response.jsonPath().getString("firstname");
        Assert.assertEquals(firstName, "Harshit");
    }

    @Test(priority = 3)
    public void test_delete_booking()
    {
        System.out.println(tokens);
        System.out.println(bookingid);

        r.baseUri("https://restful-booker.herokuapp.com/");
        r.basePath("/booking/" + bookingid);
        r.contentType(ContentType.JSON).log().all();
        r.cookie("token", tokens);

        response = r.when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201); //#TODO Bug 1

    }

    @Test(priority = 4)
    public void test_after_delete_request_get()
    {
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/" + bookingid);
        response = r.when().log().all().get();
        r.then().log().all().statusCode(404);
    }
}
