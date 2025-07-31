package com.ApiRestTasks.ApiRestTasks.repository;

import com.ApiRestTasks.ApiRestTasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
