package fs.todo_project.repository;

import fs.todo_project.model.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentRepository extends JpaRepository<UserComment, Integer> {
}
