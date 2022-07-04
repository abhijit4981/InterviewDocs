package com.cosmos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LambdaExpressionPOCTest {
    @Test
    public void helloTest(){
        System.out.println("Hello From Junit5");
    }
    @Test
    public void minusTest(){
        //Subtract subtract =
        LambdaExpressionPOC lambdaExpressionPOC = new LambdaExpressionPOC();
        int expected = 15;
        int actual = lambdaExpressionPOC.subtraction(17,2);
        assertEquals(expected,actual);
    }

}