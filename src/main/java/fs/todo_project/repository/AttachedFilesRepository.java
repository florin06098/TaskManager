package fs.todo_project.repository;

import fs.todo_project.entity.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachedFilesRepository extends JpaRepository<AttachedFile, Integer> {
}
