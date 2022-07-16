package com.cosmos.cameldemo.process;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumeRestCall {
    @Value("${nse.cookie}")
    private String nseCookie;
    public void callRestClassRoute(){
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:start")
                            .to("class:com.cosmos.cameldemo.hello.HelloWorld?method=executeCamelProgramForBuiltInClass");
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        camelContext.start();
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start","Hello Everyone");
    }
    public void callRestClassRoute01(){
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("timer://simpleTimer?period=1000")
                            .setBody(simple("Hello from timer at ${header.firedTime}"))
                            .to("stream:out");
                }
            });
            camelContext.start();
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            camelContext.stop();
        }
    }
    public void callRestClassRoute02(){
        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("timer://simpleTimer?period=1000")
                            .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                            .setHeader(Exchange.COOKIE_HANDLER, constant(nseCookie))
                            .to("https://www.nseindia.com/api/quote-equity?symbol=AXISBANK")
                            .process(new MyProcess());
                }
            });
            camelContext.start();
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            camelContext.stop();
        }
    }
}
