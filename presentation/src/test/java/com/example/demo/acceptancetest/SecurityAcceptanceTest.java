package com.example.demo.acceptancetest;

import com.example.demo.acceptancetest.client.SecurityTestClient;
import com.example.demo.gen.springbootserver.model.SecurityDto;
import com.example.demo.util.DateTimeWrapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.example.demo.testconstant.SecurityDtoTestConstant.getSecurityDTO;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SecurityAcceptanceTest extends AbstractAcceptanceTest {

    @Test
    void getAccessToken() {
        DateTimeWrapper.setFixed(LocalDate.of(2020, 2, 2));
        SecurityTestClient securityTestClient = new SecurityTestClient(getMockMvc());

        SecurityDto securityDto = new SecurityDto();
        securityDto.setAccessToken("token");

        assertThat(securityTestClient.getAccessToken("admin", "admin"))
                .isEqualTo(getSecurityDTO("eyJraWQiOiI0NjZlZWQ4MS0yYmE4LTQ2OTMtODYxYi1lZDA2NGViZGU1MzQiLCJhbGciOiJSUzI1NiJ9.eyJuYW1lIjoiYWRtaW4iLCJleHAiOiJGZWIgMiwgMjAyMCwgMTI6MTA6MDAgQU0iLCJzY29wZSI6ImFwaSJ9.sWXAq7U9HkcRv9Y9hPtzU_Qbilf1nijQKiDZyW6C2cAbMyZPnuYndN9UuX3iBtu93lyZpGuHwZvyICBWnCucwhZUwDg5xkZSe0gREfrbtr9So7gnfBmsUA-_Ial0i2P_hFhsVzLywhNOATDu7k34aQiNmmv3yrA4_UrdhIcMKMA7IPoxjWrrUMscYuEEKDhWoC_-vHE3HkYX2M2zpDjRREq2dX7Mz1ulC4tF6xHW-czYs4NawHlrvPnqv7DuojDTpYwm8Ov6s_er4pH4QFHuGoynzSx9gpJfQJnV9PUT5Ldz8a7b282Ku_uopd42RR9C__DLhX4eUalyAOg2I3e09w"));

    }
}
