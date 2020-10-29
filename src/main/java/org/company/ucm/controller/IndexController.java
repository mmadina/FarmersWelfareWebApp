package org.company.ucm.controller;

import lombok.extern.slf4j.Slf4j;
import org.company.ucm.client.FarmersWelfareServicesClient;
import org.company.ucm.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class IndexController {

    private FarmersWelfareServicesClient farmersWelfareServicesClient;

    public IndexController(FarmersWelfareServicesClient farmersWelfareServicesClient) {
        this.farmersWelfareServicesClient = farmersWelfareServicesClient;
    }

    @GetMapping("/register")
    public String register() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(value="/register/user",
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody User user) {
        try {
            farmersWelfareServicesClient.createUser(user);
            log.info("Successfully created user.");
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception ex) {
            log.error("Exception happened while creating user: {}", ex.getMessage());
            return new ResponseEntity("Internal server error occurred while creating new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}