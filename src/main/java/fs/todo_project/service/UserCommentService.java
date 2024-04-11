package fs.todo_project.service;

import fs.todo_project.model.User;
import fs.todo_project.model.UserComment;

import java.util.List;

public interface UserCommentService {
    UserComment addComment(User user, String userComment, Integer taskId);
    void deleteComment(User user, Integer commentId);
    List<UserComment> getCommnents();
}
