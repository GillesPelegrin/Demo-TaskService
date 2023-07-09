package com.example.demo.dto.task;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskDTO(String id,
                      String title,
                      String message,
                      LocalDateTime creationDate,
                      LocalDateTime updateDate) {
}
