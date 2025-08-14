package com.ApiRestTasks.ApiRestTasks.controller;

import com.ApiRestTasks.ApiRestTasks.dto.request.TaskRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.TaskResponseDTO;
import com.ApiRestTasks.ApiRestTasks.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        //Using jackson-datatype-jsr310
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void getTasks_ShouldReturnList() throws Exception {
        TaskResponseDTO task = new TaskResponseDTO(1, "Test Task", "Desc", LocalDate.now());
        when(taskService.getAllTasks()).thenReturn(List.of(task));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Task"));

        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void getTaskById_ShouldReturnTask() throws Exception {
        TaskResponseDTO task = new TaskResponseDTO(1, "Test Task", "Desc", LocalDate.now());
        when(taskService.getTaskById(1)).thenReturn(task);

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));

        verify(taskService).getTaskById(1);
    }

    @Test
    void createTask_ShouldReturnCreated() throws Exception {
        TaskRequestDTO request = new TaskRequestDTO("New Task", "Desc", LocalDate.now());
        TaskResponseDTO response = new TaskResponseDTO(1, "New Task", "Desc", LocalDate.now());

        when(taskService.createTask(any(TaskRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Task"));

        verify(taskService).createTask(any(TaskRequestDTO.class));
    }

    @Test
    void updateTask_ShouldReturnUpdated() throws Exception {
        TaskRequestDTO request = new TaskRequestDTO("Updated Task", "Desc", LocalDate.now());
        TaskResponseDTO response = new TaskResponseDTO(1, "Updated Task", "Desc", LocalDate.now());

        when(taskService.updateTask(eq(1), any(TaskRequestDTO.class))).thenReturn(response);

        mockMvc.perform(put("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Task"));

        verify(taskService).updateTask(eq(1), any(TaskRequestDTO.class));
    }

    @Test
    void deleteTask_ShouldReturnNoContent() throws Exception {
        doNothing().when(taskService).deleteTask(1);

        mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isNoContent());

        verify(taskService).deleteTask(1);
    }
}
