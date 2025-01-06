package org.example.SampleCheck;

public class APITest004_BuilderPatternDesignPattern {

    public APITest004_BuilderPatternDesignPattern step1(){
        System.out.println("Step 1 is Started");
        System.out.println("Step 1 Done");
        return this;
    }

    public APITest004_BuilderPatternDesignPattern step2(){
        System.out.println("Step 2 is Started");
        System.out.println("Step 2 Done");
        return this;
    }

    public APITest004_BuilderPatternDesignPattern step3(String name){
        System.out.println("Step 3 is Started");
        System.out.println("Step 3 Done " + name);
        return this;
    }
    public static void main(String[] args) {
        APITest004_BuilderPatternDesignPattern bp = new APITest004_BuilderPatternDesignPattern();
        bp.step1().step2().step3("Pramod");
    }
}
