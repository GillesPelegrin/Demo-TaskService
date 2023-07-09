package com.example.demo.dto.task;

import com.example.demo.dto.PageableDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TasksDTO extends PageableDTO<TaskDTO> {

    @Builder
    public TasksDTO(Long totalAmount, List<TaskDTO> items, Long pageNumber) {
        super(totalAmount, items, pageNumber);
    }
}
