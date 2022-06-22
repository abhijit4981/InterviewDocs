package com.cosmos.cameldemo.files;

import com.cosmos.cameldemo.route.FileCopyRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileCopy {
    @Autowired
    private FileCopyRoute fileCopyRoute;
    public void copyFile(){
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(fileCopyRoute);
        } catch (Exception e) {
            System.out.println("Some Exception : "+e.getMessage());
        }
        while (true)
            camelContext.start();
    }
}
