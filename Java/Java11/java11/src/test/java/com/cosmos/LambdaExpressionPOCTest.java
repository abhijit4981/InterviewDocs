package com.cosmos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LambdaExpressionPOCTest {
    private LambdaExpressionPOC lambdaExpressionPOC;

    @BeforeAll
    public static void initBeforeAll(){
        System.out.println("Runs before all i.e even the Class initialization so is a static");
    }
    @BeforeEach
    public void init(){
        lambdaExpressionPOC = new LambdaExpressionPOC();
    }
    @AfterEach
    public void destroy(){
        System.out.println("Cleaning up the objects..");
    }
    @Test
    public void helloTest(){
        System.out.println("Hello From Junit5");
    }
    @Test
    public void minusTest(){
        int expected = 15;
        int actual = lambdaExpressionPOC.subtraction(17,2);
        assertEquals(expected,actual);
    }
    @Test
    public void devideTest(){
        assertThrows(ArithmeticException.class,()->lambdaExpressionPOC.devide(7,0),"U must not devide something with zero");
        //assertThrows(Exception.class,()->lambdaExpressionPOC.devide(7,0));
        //assertThrows(NullPointerException.class,()->lambdaExpressionPOC.devide(7,0));
    }

}