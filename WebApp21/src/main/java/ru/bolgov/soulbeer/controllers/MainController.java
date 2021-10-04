package ru.bolgov.soulbeer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Controller
@EnableWebMvc
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "index";
    }
}
