package com.cosmos.cameldemo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileCopyRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //file is a in build component
        //component:endpoint?option
        from("file:input_location?noop=true")
                .to("file:output_location");
    }
}
