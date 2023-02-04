package com.example.demo.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PropertySource("classpath:application.properties")
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public HttpEntity<Object> getHello(){

        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
