package fs.todo_project.service.impl;

import fs.todo_project.handler.TaskNotFoundException;
import fs.todo_project.repository.TaskRepository;
import fs.todo_project.model.Task;
import fs.todo_project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task getTask(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new TaskNotFoundException("The task with id: " + id + " does not exist"));
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        Optional<Task> theTask = taskRepository.findById(task.getId());
        return theTask.map(taskElm -> taskRepository.save(task))
                .orElseThrow(() -> new TaskNotFoundException("Could not update the task: " + theTask.map(Task::getName).orElse("") + " because it does not exist"));
    }

    @Override
    public void deleteTask(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(taskRepository::delete);
    }
}
