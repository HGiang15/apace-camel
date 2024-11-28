package com.app.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProductRoutes extends RouteBuilder {

        @Override
        public void configure() {
                log.info("Starting Camel Route Configuration...");
                restConfiguration()
                                .host("localhost")
                                .contextPath("/api")
                                .port("8080")
                                .enableCORS(true)
                                .component("servlet");
                rest("/products")
                                .get("/all")
                                .to("direct:getAllProducts");

                from("direct:getAllProducts")
                                .bean("productService", "getAllProducts")
                                .log("${body}")
                                .setHeader("Content-Type", constant("application/json"))
                                .marshal().json();

                from("direct:errorContent")
                                .transform().constant("Có lỗi xảy ra , xin lỗi!");

                log.info("Camel Routes Configured Successfully!");
        }
}
