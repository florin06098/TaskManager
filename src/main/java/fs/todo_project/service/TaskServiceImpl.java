package fs.todo_project.service;

import fs.todo_project.repository.TaskRepository;
import fs.todo_project.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Optional<Task> getTask(int theId) {
        return taskRepository.findById(theId);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        taskRepository.save(task);
        return task;
    }

    @Override
    public void deleteTask(int id) {
        Optional<Task> task = getTask(id);
        task.ifPresent(value -> taskRepository.delete(value));
    }
}
