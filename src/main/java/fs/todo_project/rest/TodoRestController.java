package fs.todo_project.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TodoRestController {



    @GetMapping("/todo")
    private String getTodo(int id){
        return "";
    }

}
