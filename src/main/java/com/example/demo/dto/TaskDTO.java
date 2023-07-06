package com.example.demo.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskDTO(Long id,
                      String title,
                      String message,
                      LocalDateTime creationDate,
                      LocalDateTime updateDate) {
}
