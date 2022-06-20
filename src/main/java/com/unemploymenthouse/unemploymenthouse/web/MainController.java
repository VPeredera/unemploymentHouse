package com.unemploymenthouse.unemploymenthouse.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }
}
