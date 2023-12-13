package com.example.demo.controller;

import com.example.demo.SecurityApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Base64;

import static com.example.demo.testconstant.SecurityDtoTestConstant.getSecurityDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityControllerTest {

    @Mock
    private SecurityApplicationService securityApplicationService;

    @InjectMocks
    private SecurityController securityController;

    @Test
    void getAccessToken() {
        String usernamePassword = "username:password";
        String encodedString = Base64.getEncoder().encodeToString(usernamePassword.getBytes());

        when(securityApplicationService.getAccessToken("username", "password"))
                .thenReturn(getSecurityDTO("token"));

        assertThat(securityController.getAccessToken(encodedString))
                .isEqualTo(new ResponseEntity<>(getSecurityDTO("token"), HttpStatus.OK));
    }

    @Test
    void getAccessToken_withoutAuthorizationHeader() {
        assertThatThrownBy(() -> securityController.getAccessToken(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Could not decode basic authorization, please encode base64 'username:password'");
    }

    @Test
    void getAccessToken_withoutPassword() {
        String usernamePassword = "username:";
        String encodedString = Base64.getEncoder().encodeToString(usernamePassword.getBytes());

        assertThatThrownBy(() -> securityController.getAccessToken(encodedString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Could not decode basic authorization, please encode base64 'username:password'");
    }

    @Test
    void getAccessToken_onlyWithSemiColon() {
        String usernamePassword = ":";
        String encodedString = Base64.getEncoder().encodeToString(usernamePassword.getBytes());

        assertThatThrownBy(() -> securityController.getAccessToken(encodedString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Could not decode basic authorization, please encode base64 'username:password'");
    }
}