package fs.todo_project.handler;

import fs.todo_project.entity.TaskErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskRestExceptionHandler {

    /*
        Handles only the exceptions that were thrown by me.
    */
    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleException(TaskNotFoundException tnfe){
        TaskErrorResponse errorResponse = buildTaskErrorResponse(tnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    /*
        Handles all the other exceptions that are happening.
    */
    @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleException(Exception ex){
        TaskErrorResponse errorResponse = buildTaskErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private TaskErrorResponse buildTaskErrorResponse(String message){
        TaskErrorResponse errorResponse = new TaskErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return errorResponse;
    }
}
