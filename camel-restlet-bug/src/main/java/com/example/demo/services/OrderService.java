package com.example.demo.services;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.http.common.cookie.ExchangeCookieHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("orderService")
public class OrderService {

    @Autowired
    ExchangeCookieHandler exchangeCookieHandler;

    // This method will be invoked for reach GET request to /orders/{id}
    public Order getOrder(@Headers Map headers, @ExchangeProperties Map properties,Exchange exchange){

        List<String> cookieList = new ArrayList<>();
        cookieList.add("Key90001=Value90001;Path=/;HttpOnly=true;session-token=test;Expires=Thu, 06-Jan-2022 00:00:00 GMT;");
        cookieList.add("Key90002=Value90002;Path=/;HttpOnly=true;session-token=test;Expires=Thu, 06-Jan-2022 00:00:00 GMT;");

        // Sets the two cookies on the browser
        headers.put("Set-Cookie",cookieList); /// this will return an error saying  java.io.IOException: Empty cookie name detected. Please check your cookies
        //But the code above works just fine with Jetty and Undertow.


        //Set with one cookie on the browser works just fine
        //headers.put("Set-Cookie","Key6001=Value6001;Path=/;HttpOnly=true;session-token=test;Expires=Thu, 06-Jan-2022 00:00:00 GMT;");


        //// This is the object that returns to the browser.
        String idd = (String)headers.get("id");
        int value = Integer.valueOf(idd);
        Order order = new Order("Ipad", value,700,false);
        return order;
    }

}
