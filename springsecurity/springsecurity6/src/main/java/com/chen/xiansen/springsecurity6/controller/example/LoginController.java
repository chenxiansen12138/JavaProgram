package com.chen.xiansen.springsecurity6.controller.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
//    @PreAuthorize("hasRole('ADMIN') and authentication.name == 'admin'")
    //@PreAuthorize("hasAuthority('USER_GET')")
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
