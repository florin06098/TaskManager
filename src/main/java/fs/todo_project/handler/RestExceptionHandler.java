package fs.todo_project.handler;

import fs.todo_project.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    /*
        Handles only the exceptions that were thrown by me.
    */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(TaskNotFoundException tnfe){
        ErrorResponse errorResponse = buildTaskErrorResponse(tnfe.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserAlreadyExists uex){
        ErrorResponse errorResponse = buildTaskErrorResponse(uex.getMessage(), HttpStatus.FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.FOUND);
    }

    /*
        Handles all the other exceptions that are happening.
    */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        ErrorResponse errorResponse = buildTaskErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse buildTaskErrorResponse(String message, HttpStatus status){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setStatus(status.value());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return errorResponse;
    }
}
