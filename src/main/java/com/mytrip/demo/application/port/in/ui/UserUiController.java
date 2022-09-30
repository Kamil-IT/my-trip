package com.mytrip.demo.application.port.in.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserUiController {

    @GetMapping("/user")
    public String getUsers(){
        return "user";
    }
}
