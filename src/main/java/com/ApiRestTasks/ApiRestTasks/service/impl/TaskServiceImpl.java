package com.ApiRestTasks.ApiRestTasks.service.impl;

import com.ApiRestTasks.ApiRestTasks.dto.request.TaskRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.TaskResponseDTO;
import com.ApiRestTasks.ApiRestTasks.mapper.TaskMapper;
import com.ApiRestTasks.ApiRestTasks.model.Task;
import com.ApiRestTasks.ApiRestTasks.repository.TaskRepository;
import com.ApiRestTasks.ApiRestTasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
Service Implementation
- Contains the actual business logic of the application.
- Implements the methods defined in the Service Interface.
- Interacts with the Repository Layer to retrieve and store data.
- Acts as the middle layer between Controllers and Repositories.
*/
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO getTaskById(Integer id) {
        Task task=taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
        return TaskMapper.toResponseDTO(task);
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task createdTask=TaskMapper.toEntity(taskRequestDTO);
        Task savedTask=taskRepository.save(createdTask);
        return TaskMapper.toResponseDTO(savedTask);
    }

    @Override
    public TaskResponseDTO updateTask(Integer id, TaskRequestDTO taskRequestDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setDueDate(taskRequestDTO.getDueDate());
        Task updatedTask = taskRepository.save(task);
        return TaskMapper.toResponseDTO(updatedTask);
    }

    @Override
    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
        taskRepository.delete(task);
    }
}
