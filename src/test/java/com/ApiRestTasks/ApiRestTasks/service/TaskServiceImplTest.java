package com.ApiRestTasks.ApiRestTasks.service;

import com.ApiRestTasks.ApiRestTasks.dto.request.TaskRequestDTO;
import com.ApiRestTasks.ApiRestTasks.dto.respone.TaskResponseDTO;
import com.ApiRestTasks.ApiRestTasks.mapper.TaskMapper;
import com.ApiRestTasks.ApiRestTasks.model.Task;
import com.ApiRestTasks.ApiRestTasks.repository.TaskRepository;
import com.ApiRestTasks.ApiRestTasks.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
The AAA (Arrange, Act, Assert) pattern is a common way of writing unit tests for a method under test.
- The Arrange section of a unit test method initializes objects and sets the value of the data that is passed to the method under test.
- The Act section invokes the method under test with the arranged parameters.
- The Assert section verifies that the action of the method under test behaves as expected.
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    //Inject the service
    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void getAllTasks_ShouldReturnList() {
        // Arrange (prepare fake data)
        Task task1 = new Task(1, "Title 1", "Description 1", LocalDate.now(), false);
        Task task2 = new Task(2, "Title 2", "Description 2", LocalDate.now(), false);
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        // Act (call the method we are testing)
        List<TaskResponseDTO> result = taskService.getAllTasks();

        // Assert (check the result)
        assertEquals(2, result.size()); // 2 tasks returned
        assertEquals("Title 1", result.get(0).getTitle()); // first task title matches
        verify(taskRepository, times(1)).findAll(); // repository was called once
    }

    /**
     * Test: getTaskById should return a TaskResponseDTO if task exists
     */
    @Test
    void getTaskById_ShouldReturnTask_WhenExists() {
        // Arrange
        Task task = new Task(1, "My Task", "Test Desc", LocalDate.now(),false);
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        // Act
        TaskResponseDTO result = taskService.getTaskById(1);

        // Assert
        assertNotNull(result);
        assertEquals("My Task", result.getTitle());
        verify(taskRepository).findById(1);
    }

    /**
     * Test: getTaskById should throw RuntimeException if task not found
     */
    @Test
    void getTaskById_ShouldThrow_WhenNotFound() {
        // Arrange
        when(taskRepository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> taskService.getTaskById(99));
        assertTrue(ex.getMessage().contains("Task not found"));
    }

    /**
     * Test: createTask should save and return the new TaskResponseDTO
     */
    @Test
    void createTask_ShouldSaveAndReturn() {
        // Arrange
        TaskRequestDTO request = new TaskRequestDTO("New Task", "Desc", LocalDate.now());
        Task taskEntity = TaskMapper.toEntity(request); // convert DTO to entity
        Task savedTask = new Task(1, "New Task", "Desc", LocalDate.now(),false);

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        // Act
        TaskResponseDTO result = taskService.createTask(request);

        // Assert
        assertNotNull(result);
        assertEquals("New Task", result.getTitle());
        verify(taskRepository).save(any(Task.class));
    }

    /**
     * Test: updateTask should change fields and save
     */
    @Test
    void updateTask_ShouldUpdateFields() {
        // Arrange
        Task existingTask = new Task(1, "Old Title", "Old Desc", LocalDate.now(),false);
        TaskRequestDTO updateRequest = new TaskRequestDTO("Updated Title", "Updated Desc", LocalDate.now());
        Task updatedTask = new Task(1, "Updated Title", "Updated Desc", LocalDate.now(),false);

        when(taskRepository.findById(1)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(updatedTask);

        // Act
        TaskResponseDTO result = taskService.updateTask(1, updateRequest);

        // Assert
        assertEquals("Updated Title", result.getTitle());
        verify(taskRepository).findById(1);
        verify(taskRepository).save(existingTask);
    }

    /**
     * Test: deleteTask should remove a task if it exists
     */
    @Test
    void deleteTask_ShouldCallDelete() {
        // Arrange
        Task task = new Task(1, "Title", "Desc", LocalDate.now(),false);
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        // Act
        taskService.deleteTask(1);

        // Assert
        verify(taskRepository).delete(task);
    }
}
