package org.example.Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITesting013_Assertion {

    @Test
    public void test_hardAssertExample() {
        System.out.println("Start of the program");
        Assert.assertTrue(true);
        System.out.println("End of the program");

        Assert.assertEquals("Pramod","Pramod"); //T
        Assert.assertEquals("Pramod","pramod"); //F
    }

    @Test
    public void test_softAssertExample() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false); // This will not stop execution.
        System.out.println("This line will be executed.");
        softAssert.assertAll(); // This will report all collected errors.
    }
}
