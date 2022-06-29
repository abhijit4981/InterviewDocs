package com.cosmos.cameldemo.route;

import com.cosmos.cameldemo.model.company.CompanyModel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class ConsumeRestCallRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        rest()
                .get("https://www.nseindia.com/api/quote-equity?symbol=HCLTECH")
                .type(CompanyModel.class)
                .consumes("application/json")
                .bindingMode(RestBindingMode.json)
                .produces("application/json")
                .to("direct:order");

        from("direct:order")
                .log("Incoming Body is ${body}")
                .log("Incoming Body after unmarshal is ${body}");
    }
}
