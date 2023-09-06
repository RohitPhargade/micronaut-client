package com.myclient.controller;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.server.netty.body.HttpBody;
import jakarta.inject.Inject;

import java.util.Optional;

@Controller("/")
public class HomeController {

//import io.micronaut.http.annotation.Controller;
//        import io.micronaut.http.annotation.Get;
//        import io.micronaut.http.client.annotation.Client;
//        import io.micronaut.http.client.RxHttpClient;
//        import io.micronaut.http.client.annotation.Client;
//    private final MyClient myClient;

    @Client
    @Inject
    HttpClient httpClient;

//    public HomeController(MyClient myClient) {
//        this.myClient = myClient;
//    }

    @Get("/getprofile")
    public Object callSecuredApi() {
        // Make an HTTP request to the secured API
        Optional<String> httpBody = Optional.empty();
        String errorMessage="";
        try {
             Optional<String> exchange = httpClient.toBlocking().exchange("http://localhost:6060/profile").getBody(String.class);
//            httpBody = exchange.getBody();
            httpBody = exchange;
        }catch (Exception e){
            errorMessage = e.getMessage();
        }
       
//        return httpBody.orElse("Response from anothre server is null, message: "+errorMessage);
        return httpBody.orElse("Response from anothre server is null, message: "+errorMessage);
    }
}
//@Client("http://localhost:4040")
//interface MyClient {
//    @Get("/profile")
//    String callSecuredApi();
//}


