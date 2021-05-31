package com.pizza.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthChecker {

    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
    public ResponseEntity returnHealthCheckResponse() {
        return new ResponseEntity<>("Everything is OK", HttpStatus.OK);
    }
}