package com.rest.webservice.thetodoapprestapi.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthController {

    @GetMapping(path = "/basic-auth")
    public Authentication helloWorld() {
        return new Authentication("You are authenticated");
    }
}
