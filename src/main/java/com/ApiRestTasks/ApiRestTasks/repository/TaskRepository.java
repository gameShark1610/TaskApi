package com.ApiRestTasks.ApiRestTasks.repository;

import com.ApiRestTasks.ApiRestTasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
/*
Repository Layer
- Provides the interface for accessing and manipulating database records.
- Typically extends JpaRepository or CrudRepository in Spring Data JPA, offering built-in CRUD methods.
- Contains only data access logic — no business rules or validations.
- Should only be accessed by the Service Implementation, never by Controllers directly.
*/
public interface TaskRepository extends JpaRepository<Task,Integer> {
}
