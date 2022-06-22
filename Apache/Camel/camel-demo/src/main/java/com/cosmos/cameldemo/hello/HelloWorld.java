package com.cosmos.cameldemo.hello;

import com.cosmos.cameldemo.route.HelloWorldRuote;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
    @Autowired
    private HelloWorldRuote helloWorldRuote;
    public void executeCamelProgram(){
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(helloWorldRuote);
        } catch (Exception e) {
            System.out.println("Some Exception : "+e.getMessage());
        }
        camelContext.start();
    }
    public void executeCamelProgramForBuiltInClass(String message){
        System.out.println("Print this message "+message);
    }
}
