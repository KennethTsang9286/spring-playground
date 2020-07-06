package com.example.consumingrest.Controller;

import com.example.consumingrest.Object.Greeting;
import com.example.consumingrest.Object.ShortGreeting;

import java.util.concurrent.atomic.AtomicLong;

// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong greetingCounter = new AtomicLong();
    private final AtomicLong greetingCorsCounter = new AtomicLong();

    // @RequestMapping(method=GET)
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(greetingCounter.incrementAndGet(), String.format(TEMPLATE, name));
    }

    // @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/greeting/cors")
    public Greeting greetingCors(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(greetingCorsCounter.incrementAndGet(), String.format(TEMPLATE, name));
    }

    @RequestMapping("/shortGreeting")
    public HttpEntity<ShortGreeting> shortGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        ShortGreeting greeting = new ShortGreeting(String.format(TEMPLATE, name));
        // var _class = GreetingController.class;
        // var _methodOn = methodOn(_class);
        // var _methodOnGreeting = _methodOn.greeting(name);
        // var _linkTo = linkTo(_methodOnGreeting);
        // var _linkToWith = _linkTo.withSelfRel();
        // var greetingAdd = greeting.add(_linkToWith);
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format(TEMPLATE, name);
    }

}