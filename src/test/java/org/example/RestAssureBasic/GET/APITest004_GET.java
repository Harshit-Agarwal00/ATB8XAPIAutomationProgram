package org.example.RestAssureBasic.GET;

public class APITest004_GET {
    public void step1(){
        System.out.println("Step 1");
    }

    public void step2(){
        System.out.println("Step 2");
    }

    public void step3(String pramod){
        System.out.println("Step 3");
    }

    public static void main(String[] args) {
        APITest004_GET np = new APITest004_GET();
        np.step1();
        np.step2();
        np.step3("Pramod");

        // np.step1().step2().step3("Pramod")  Not Possible
    }
}
