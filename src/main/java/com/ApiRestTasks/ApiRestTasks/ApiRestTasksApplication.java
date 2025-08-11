package com.ApiRestTasks.ApiRestTasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
Application Layer
- The entry point of the Spring Boot application (annotated with @SpringBootApplication).
- Responsible for bootstrapping and configuring the entire application.
- May contain application-wide configurations, dependency injection setup, and bean definitions.
- Typically only has the main() method and should not contain business logic.
*/
@SpringBootApplication
public class ApiRestTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestTasksApplication.class, args);
	}

}
