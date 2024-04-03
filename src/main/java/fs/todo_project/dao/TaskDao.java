package fs.todo_project.dao;

import fs.todo_project.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    Task getTask(int id);
    List<Task> getTasks();
    Task save(Task task);
    void deleteTask(int id);
}
