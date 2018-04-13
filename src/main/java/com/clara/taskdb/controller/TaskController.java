package com.clara.taskdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

//    Routing for the home page
    @GetMapping("/")
    public String homePage(){
        return "index.html";
    }


}
