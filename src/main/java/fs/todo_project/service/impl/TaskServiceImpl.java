package fs.todo_project.service.impl;

import fs.todo_project.handler.TaskNotFoundException;
import fs.todo_project.model.TaskPriority;
import fs.todo_project.model.TaskStatus;
import fs.todo_project.model.User;
import fs.todo_project.repository.TaskRepository;
import fs.todo_project.model.Task;
import fs.todo_project.repository.UserRepository;
import fs.todo_project.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Task getTask(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new TaskNotFoundException("The task with id: " + id + " does not exist"));
    }

    @Override
    @Transactional
    public List<Task> getUserTasks(Integer id) {
        return taskRepository.findUserTasks(id);
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
    @Transactional
    public void deleteTask(Integer id, User user) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No task found with id: " + id));

        for (User theUser : task.getUsers()) {
            theUser.getTasks().remove(task);
        }
        taskRepository.save(task);
        taskRepository.delete(task);
    }

    @Override
    public Task createTask(Task task, User user) {
        task.setTaskStatus(TaskStatus.TODO);
        task.setTaskPriority(TaskPriority.HIGH);
        user.getTasks().add(task);
        userRepository.save(user);
        return task;
    }
}
