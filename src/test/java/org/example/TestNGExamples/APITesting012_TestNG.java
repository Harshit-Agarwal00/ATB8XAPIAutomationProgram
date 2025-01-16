package org.example.TestNGExamples;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITesting012_TestNG {

    @BeforeTest
    public void get_Token()
    {
        System.out.println("1");
    }

    @BeforeTest
    public void get_BookingID()
    {
        System.out.println("2");
    }

    @Test
    public void test_PUT()
    {
        System.out.println("3");
    }
}
