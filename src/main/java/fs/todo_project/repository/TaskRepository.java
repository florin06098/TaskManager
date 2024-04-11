package fs.todo_project.repository;

import fs.todo_project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Tasks t JOIN t.users u WHERE u.id = :userId")
    List<Task> findUserTasks(@Param("userId") Integer userId);
}
