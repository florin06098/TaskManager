package fs.todo_project.repository;

import fs.todo_project.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Integer> {
}
