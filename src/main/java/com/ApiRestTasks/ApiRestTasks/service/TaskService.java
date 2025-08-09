package com.ApiRestTasks.ApiRestTasks.service;

import com.ApiRestTasks.ApiRestTasks.model.Task;

import java.util.List;

//I thought in add a class but a good practice is use interfaces and create a carpet called imcp(is short for implementation)
public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Integer id);
    Task createTask(Task task);
    Task updateTask(Integer id, Task taskDetails);
    void deleteTask(Integer id);
}
