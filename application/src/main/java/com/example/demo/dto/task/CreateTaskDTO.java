package com.example.demo.dto.task;

import lombok.Builder;

@Builder
public record CreateTaskDTO(String title,
                      String message) {
}