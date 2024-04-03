package fs.todo_project.service;

import fs.todo_project.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    private List<Todo> todoList = new ArrayList<>();

    @Override
    public Optional<Todo> getTodo(int id) {
        Optional<Todo> todoOptional = Optional.empty();

        for(Todo todo : todoList){
            if(todo.getId() == id){
                todoOptional = Optional.of(todo);
            }
        }
        return todoOptional;
    }

    @Override
    public List<Todo> getTodos() {
        
    }

    @Override
    public void createTodo(Todo todo) {

    }

    @Override
    public void deleteTodo(int id) {

    }
}
