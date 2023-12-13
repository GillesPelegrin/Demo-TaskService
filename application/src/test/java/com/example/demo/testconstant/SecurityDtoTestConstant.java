package com.example.demo.testconstant;

import com.example.demo.gen.springbootserver.model.SecurityDto;


public class SecurityDtoTestConstant {

    public static SecurityDto getSecurityDTO(String token) {
        SecurityDto securityDto = new SecurityDto();
        securityDto.setAccessToken(token);
        return securityDto;
    }
}
