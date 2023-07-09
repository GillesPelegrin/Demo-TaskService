package com.example.demo.dto.task;

import lombok.Builder;

@Builder
public record UpdateTaskDTO(String id,
                            String title,
                            String message) {
}