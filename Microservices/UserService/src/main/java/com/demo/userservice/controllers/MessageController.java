/*
package com.demo.userservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
public class MessageController {
      @Value("${spring.boot.message}")
    private String message;

    //http://localhost:8080/actuator/refresh
    @GetMapping("/users/message")
    public String getMessage(){
        return message;
    }
}
*/
