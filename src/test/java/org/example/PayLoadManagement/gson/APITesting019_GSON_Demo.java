package org.example.PayLoadManagement.gson;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

public class APITesting019_GSON_Demo {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void test_Positive()
    {
        Booking booking = new Booking();
        booking.setFirstname("Harshit");
        booking.setLastname("Agarwal");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-02-01");
        bookingDates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingDates);
        booking.setAdditionalneeds("Lunch");

        System.out.println(booking);
        Gson gson = new Gson();
        String GsonStringBooking = gson.toJson(booking);
        System.out.println(GsonStringBooking);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(GsonStringBooking).log().all();

        Response response = requestSpecification.when().post();
        String JsonResponseString =response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = gson.fromJson(JsonResponseString, BookingResponse.class);

        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Harshit").isNotNull().isNotEmpty();
    }
}