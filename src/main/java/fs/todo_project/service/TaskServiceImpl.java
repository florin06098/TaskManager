package fs.todo_project.service;

import fs.todo_project.dao.TaskRepository;
import fs.todo_project.entity.Task;
import fs.todo_project.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public Optional<Task> getTask(int theId) {
        return taskRepository.findById(theId);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task todo) {
        taskRepository.save(todo);
        return todo;
    }

    @Override
    public void deleteTask(int id) {
        Optional<Task> task = getTask(id);
        task.ifPresent(value -> taskRepository.delete(value));
    }
}
