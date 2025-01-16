package org.example.TestNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting016_TestNG_DependOnMethod {

    @Test
    public void serverStartedOk() {
        System.out.println("I will run first");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOk")
    public void method1() {
        System.out.println("method1");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOk")
    public void method2() {
        System.out.println("method1");
        Assert.assertTrue(true);
    }
}
