package com.example.demo.controller;

import com.example.demo.SecurityApplicationService;
import com.example.demo.gen.springbootserver.api.SecurityApi;
import com.example.demo.gen.springbootserver.model.SecurityDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@AllArgsConstructor
public class SecurityController implements SecurityApi {

    private final SecurityApplicationService securityApplicationService;

    @Override
    public ResponseEntity<SecurityDto> getAccessToken(String authorization) {
        String[] usernamePassword = decodeAuthorizationToUsernameAndPassword(authorization);

        return new ResponseEntity<>(securityApplicationService
                .getAccessToken(usernamePassword[0], usernamePassword[1]), HttpStatus.OK);
    }

    private String[] decodeAuthorizationToUsernameAndPassword(String authorization) {
        String base64String = authorization.replace("basic", "");
        base64String = authorization.replace("Basic", "");
        base64String = base64String.trim();

        String[] usernamePassword = new String(Base64.getDecoder().decode(base64String)).split(":");
        if (usernamePassword.length != 2) {
            throw new IllegalArgumentException("Could not decode basic authorization, please encode base64 'username:password'");
        }
        return usernamePassword;
    }
}
