package fs.todo_project.controller;

import fs.todo_project.config.UserDetailsExtension;
import fs.todo_project.model.User;
import fs.todo_project.model.UserComment;
import fs.todo_project.service.UserCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserCommentController {
    private final UserCommentService userCommentService;

    @PostMapping("/comment")
    public ResponseEntity<UserComment> addComment(@RequestParam String userComment, @RequestParam Integer taskId, Authentication authentication) throws IOException {
        UserDetailsExtension principal = (UserDetailsExtension) authentication.getPrincipal();
        User user = principal.getUser();
        return new ResponseEntity<>(userCommentService.addComment(user, userComment, taskId), HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId, Authentication authentication) {
        UserDetailsExtension principal = (UserDetailsExtension) authentication.getPrincipal();
        User user = principal.getUser();
        userCommentService.deleteComment(user, commentId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<UserComment>> getAllComments() {
        return new ResponseEntity<>(userCommentService.getCommnents(), HttpStatus.OK);
    }
}
