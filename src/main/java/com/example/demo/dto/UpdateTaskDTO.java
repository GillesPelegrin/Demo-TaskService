package com.example.demo.dto;

import lombok.Builder;

@Builder
public record UpdateTaskDTO(Long id,
                            String title,
                            String message) {
}