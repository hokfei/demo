package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/abc")
public class TestController {
    @GetMapping("/def")
    public String testReturn() {
        return "aoeuaoeuaoeu";
    }
}
