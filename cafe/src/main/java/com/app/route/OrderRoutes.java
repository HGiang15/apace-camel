package com.app.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.app.model.order;

@Component
public class OrderRoutes extends RouteBuilder {
        @Override
        public void configure() {
                log.info("Starting Camel Route Order Configuration...");
                restConfiguration()
                                .host("localhost")
                                .contextPath("/api")
                                .port("8080")
                                .enableCORS(true)
                                .component("servlet");
                rest("/orders")
                                .get("/all")
                                .to("direct:getAllOrders");
                from("direct:getAllOrders")
                                .bean("orderService", "getAllOrders")
                                .log("${body}")
                                .setHeader("Content-Type", constant("application/json"))
                                .marshal().json();
                rest("/get/order/{id}")
                                .get()
                                .to("direct:getOneOrder");

                from("direct:getOneOrder")
                                .log("Received request for order ID: ${header.id}")
                                .bean("orderService", "getOneOrder(${header.id})")
                                .log("Retrieved order: ${body}")
                                .setHeader("Content-Type", constant("application/json"))
                                .marshal().json();

                rest("/add/order")
                                .post()
                                .type(order.class)
                                .to("direct:addOrder");

                from("direct:addOrder")
                                .log("Received order: ${body}")
                                .unmarshal().json(JsonLibrary.Jackson, order.class) // Giải mã JSON thành đối tượng
                                                                                    // Order
                                .process(exchange -> {
                                        order or = exchange.getIn().getBody(order.class);
                                        or.setId(String.valueOf(order.read().size()));
                                        System.out.println("Saving order: " + or);
                                        or.write();
                                        exchange.getIn().setBody(or.getId());
                                })
                                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)) // Trả về mã 200 OK
                                .setHeader(Exchange.CONTENT_TYPE, constant("application/json")); // Thiết lập
                                                                                                 // Content-Type
                from("servlet://cors-filter")
                                .process(exchange -> {
                                        exchange.getMessage().setHeader("Access-Control-Allow-Origin", "*");
                                        exchange.getMessage().setHeader("Access-Control-Allow-Methods",
                                                        "GET, POST, PUT, DELETE, OPTIONS");
                                        exchange.getMessage().setHeader("Access-Control-Allow-Headers",
                                                        "Content-Type, Authorization");
                                })
                                .to("log:cors-filter");
                log.info("Camel Routes Configured Successfully!");
        }
}
