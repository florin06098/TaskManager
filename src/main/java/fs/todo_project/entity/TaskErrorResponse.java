package fs.todo_project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskErrorResponse {
    private int status;
    private String message;
    private long timestamp;

}
