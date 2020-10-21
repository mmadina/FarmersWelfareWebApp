package org.company.ucm.controller;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ApplicationController {

    @ModelAttribute("uname")
    public String setUsername() {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping("/home")
    public String home() { return "home"; }

    @GetMapping("/create")
    public String create() { return "create"; }

    @GetMapping("/search")
    public String search() { return "search"; }
}