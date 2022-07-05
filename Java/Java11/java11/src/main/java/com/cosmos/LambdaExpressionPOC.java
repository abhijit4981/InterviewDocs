package com.cosmos;

import org.springframework.stereotype.Component;

interface Calculator{
    void hello();
}
interface Add{
    void sum(int a,int b);
}
interface Subtract{
    int minus(int a,int b);
}
interface Multiply{
    int mul(int a,int b);
}
interface Devide{
    int dev(int a,int b);
}
@Component
public class LambdaExpressionPOC {
    public void AddMethodImpl(){
        System.out.println("Hello world for add method.");
        Calculator calculator = ()->{
            System.out.println("Hello world");
        };
        calculator.hello();
        Add add = (a,b)->{
            int c = a+b;
            System.out.println("Sum is : "+c);
        };
        add.sum(7,8);

    }
    public int subtraction(int val1,int val2){
        Subtract subtract = (a,b)->{
            return a-b;
        };
        return subtract.minus(val1,val2);
    }
    public int multiply(int val1,int val2){
        Multiply multiply = (a,b)->{
            return a*b;
        };
        return multiply.mul(val1,val2);
    }
    public int devide(int val1,int val2){
        Devide devide = (a,b)->{
            return a/b;
        };
        return devide.dev(val1,val2);
    }

}
