package com.example.springcloudgatewayservice;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping

public class GatewayController {
    public static String band="metalica";

    @RequestMapping("/countriesfallback")
    public Mono<String> countries(){
        return Mono.just("Countries API is taking too long to respond or is down, please try again later");
    }

    @RequestMapping("/jokefallback")
    public Mono<String>  joke(){
        return Mono.just("Jokes API is taking too long to respond or is down, please try again later");
    }

    @RequestMapping(value="/band/{band}")
    public String musicBand(@PathVariable("band") String band) {

        this.band=band;
        return "redirect:/search";

    }



//
//    @GetMapping("/second")
//    public String test(@RequestHeader("X-second-Header") String headerValue){
//        return headerValue;
//    }
}
