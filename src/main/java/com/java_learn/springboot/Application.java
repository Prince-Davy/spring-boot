package com.java_learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public GreetResponse greet() {
        return new GreetResponse(
                "Hello",
                List.of("Java", "JavaScript", "C#"),
                new Person("John",
                        19,
                        350.0));
    }

    record GreetResponse(
            String greeting,
            List<String> favProgrammingLangages,
            Person person) {
    }

    record Person(String name, int age, double availableCash) {

    }
}
