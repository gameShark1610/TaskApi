package com.ApiRestTasks.ApiRestTasks.mapper;

import com.ApiRestTasks.ApiRestTasks.dto.request.TaskRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.TaskResponseDTO;
import com.ApiRestTasks.ApiRestTasks.model.Task;

import java.util.Date;
/*
Mapper
Converts between Entities and DTOs (both RequestDTO and ResponseDTO).
- Helps keep Controllers and Services clean by centralizing conversion logic.
- Can be manual or automated using libraries like MapStruct or ModelMapper.
- Typical mappings:
- RequestDTO → Entity (when creating or updating data)
- Entity → ResponseDTO (when sending data to the client)
*/
public class TaskMapper {

    public static TaskResponseDTO toResponseDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        dto.setCreatedAt(task.getCreatedAt());
        return dto;
    }

    public static Task toEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        task.setCreatedAt(new Date()); // o puedes manejar esto en el repositorio
        return task;
    }

}
