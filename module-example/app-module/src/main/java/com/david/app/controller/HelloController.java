package com.david.app.controller;

import com.david.library.util.Greeter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        Greeter greeter = new Greeter("World");
        return greeter.greet();
    }
}
