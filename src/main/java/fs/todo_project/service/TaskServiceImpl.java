package fs.todo_project.service;

import fs.todo_project.dao.TaskDao;
import fs.todo_project.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task getTask(int id) {
        return taskDao.getTask(id);
    }

    @Override
    public List<Task> getTasks() {
        return taskDao.getTasks();
    }

    @Override
    public Task save(Task todo) {
        taskDao.save(todo);
        return todo;
    }

    @Override
    public void deleteTask(int id) {
        taskDao.deleteTask(id);
    }
}
