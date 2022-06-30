package com.cosmos.cameldemo.route;

import com.cosmos.cameldemo.model.company.CompanyModel;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class ConsumeRestCallRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test1?period=2000")
                .process(exchange -> exchange.getIn().setBody(new Order(UUID.randomUUID().toString(), ThreadLocalRandom.current().nextInt(1, 10))))
                .marshal().json(JsonLibrary.Gson)
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .setHeader(Exchange., constant("application/json"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("https://www.nseindia.com/api/quote-equity?symbol=HCLTECH")
                .process(exchange -> log.info("HTTP POST response is: {}", exchange.getIn().getBody(String.class)));

        headers.set("accept-language", "n-US,en;q=0.9");
        headers.set("accept-encoding", "zip,");
        headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        headers.set("cookie", nseCookie);
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
