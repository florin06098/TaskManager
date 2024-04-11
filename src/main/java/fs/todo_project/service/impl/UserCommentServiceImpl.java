package fs.todo_project.service.impl;

import fs.todo_project.model.Task;
import fs.todo_project.model.User;
import fs.todo_project.model.UserComment;
import fs.todo_project.repository.TaskRepository;
import fs.todo_project.repository.UserCommentRepository;
import fs.todo_project.service.UserCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCommentServiceImpl implements UserCommentService {
    private final UserCommentRepository userCommentRepository;
    private final TaskRepository taskRepository;

    @Override
    public UserComment addComment(User user, String userComment, Integer taskId) {
        UserComment theUserComment = new UserComment();
        theUserComment.setUsername(user.getName());
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("No task found with the id: " + taskId));
        theUserComment.setTask(task);
        theUserComment.setComment(userComment);

        return userCommentRepository.save(theUserComment);
    }

    @Override
    public void deleteComment(User user, Integer commentId) {
        UserComment userComment = userCommentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("No comment found"));
        if(userComment.getUsername().equals(user.getName())){
           userCommentRepository.delete(userComment);
        }
    }

    @Override
    public List<UserComment> getCommnents() {
        return userCommentRepository.findAll();
    }
}
