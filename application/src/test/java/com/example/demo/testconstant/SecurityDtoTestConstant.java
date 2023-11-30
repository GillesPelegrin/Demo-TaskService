package com.example.demo.testconstant;

import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.SecurityDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;

import static com.example.demo.testconstant.TaskTestConstant.TASK_ID;
import static com.example.demo.util.DateTimeWrapper.currentDateTime;


public class SecurityDtoTestConstant {

    public static SecurityDto getSecurityDTO(String token) {
        SecurityDto securityDto = new SecurityDto();
        securityDto.setAccessToken(token);
        return securityDto;
    }
}
