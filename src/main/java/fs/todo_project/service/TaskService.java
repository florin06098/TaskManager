package fs.todo_project.service;

import fs.todo_project.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Task> getTask(int id);
    List<Task> getTasks();
    Task save(Task task);
    void deleteTask(int id);
}
