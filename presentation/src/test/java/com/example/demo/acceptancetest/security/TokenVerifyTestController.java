package com.example.demo.acceptancetest.security;

import com.example.demo.security.TokenVerify;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenVerifyTestController {

    @TokenVerify
    @GetMapping("/verify")
    public ResponseEntity<Void> verify() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
