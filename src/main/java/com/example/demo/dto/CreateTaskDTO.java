package com.example.demo.dto;

import lombok.Builder;

@Builder
public record CreateTaskDTO(String title,
                      String message) {
}