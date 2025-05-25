package com.alvirg.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.module.FindException;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
         return "index";
     }
}
