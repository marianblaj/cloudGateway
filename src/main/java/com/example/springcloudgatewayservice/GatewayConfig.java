package com.example.springcloudgatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableHystrix
@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {

        return

                builder.routes()
                        .route(p -> p
                                .path("/all")
                                .filters(f ->
                                        f.addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                                                .addRequestHeader("x-rapidapi-key", "3a39345bbemsh08c625b14290a8fp14ef27jsne55e49c7fbd2")
                                                .hystrix(config -> config.setName("countries-service")
                                                        .setFallbackUri("forward:/countriesfallback")
                                                ))
                                .uri("https://restcountries-v1.p.rapidapi.com"))

                        .route(p -> p
                                .path("/v1/joke")
                                .filters(f ->
                                        f.addRequestHeader("x-rapidapi-host", "joke3.p.rapidapi.com/v1/joke")
                                                .addRequestHeader("x-rapidapi-key", "3a39345bbemsh08c625b14290a8fp14ef27jsne55e49c7fbd2")
                                                .hystrix(config -> config.setName("joke-service")
                                                        .setFallbackUri("forward:/jokefallback")
                                                ))
                                .uri("https://joke3.p.rapidapi.com"))



                .route(p -> p
                        .path("/search")

                .filters(f -> f
                                .addRequestHeader("x-rapidapi-host", "deezerdevs-deezer.p.rapidapi.com/search")
                                .addRequestHeader("x-rapidapi-key", "3a39345bbemsh08c625b14290a8fp14ef27jsne55e49c7fbd2")

                              //  .addRequestParameter("{a}, {b})
                                .addRequestParameter("q", GatewayController.band)

                )

                        .uri("https://deezerdevs-deezer.p.rapidapi.com"))

                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p
                        .host("*.hystrix.com")
                        .filters(f -> f.hystrix(config -> config.setName("mycmd")))
                        .uri("http://httpbin.org:80")).
                        build();
    }
}

