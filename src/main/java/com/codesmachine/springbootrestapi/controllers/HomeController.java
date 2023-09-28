package com.codesmachine.springbootrestapi.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/test-api")
    public String home(){
        return "Testing APIs";
    }
}
