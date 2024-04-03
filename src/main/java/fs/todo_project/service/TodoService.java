package fs.todo_project.service;

import fs.todo_project.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    Optional<Todo> getTodo(int id);
    List<Todo> getTodos();
    void createTodo(Todo todo);
    void deleteTodo(int id);
}
