package com.cosmos.cameldemo.process;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;

@Component
public class MakeRestCall {
    public void createGetMapping(){
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    restConfiguration()
                            .component("servlet")
                            .host("localhost")
                            .port("9191")
                            .bindingMode(RestBindingMode.off);
                            //.enableCORS(true)
                            //.dataFormatProperty("prettyPrint", "true")
                            //.endpointProperty("synchronous", "true");

                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
