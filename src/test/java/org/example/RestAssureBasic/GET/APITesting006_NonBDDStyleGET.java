package org.example.RestAssureBasic.GET;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting006_NonBDDStyleGET {

    RequestSpecification r = RestAssured.given();

    @Severity(SeverityLevel.BLOCKER)
    @Description("TC1 NonBDDStyle_GET_positive" )
    @Test
    public void test_NonBDDStyle_GET_positive()
    {
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/342001");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("TC2 NonBDDStyle_GET_negative" )
    @Test
    public void test_NonBDDStyle_GET_negative()
    {
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(404);
    }
}
