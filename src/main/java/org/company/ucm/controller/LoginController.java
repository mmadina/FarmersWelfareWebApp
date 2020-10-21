package org.company.ucm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/register")
    public String register() { return "registration"; }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}