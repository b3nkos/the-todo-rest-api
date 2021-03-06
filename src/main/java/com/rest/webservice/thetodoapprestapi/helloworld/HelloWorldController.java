package com.rest.webservice.thetodoapprestapi.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public Message helloWorld() {
        return new Message("hello world");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public Message helloWoldPathVariable(@PathVariable String name) {
        return new Message(String.format("Hello World, %s", name));
    }
}
