package fs.todo_project.dao;

import fs.todo_project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Integer> {
}
