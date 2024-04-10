package fs.todo_project.service;

import fs.todo_project.model.AttachedFile;
import java.util.Optional;

public interface AttachedFilesService {
    AttachedFile save(AttachedFile file);
    void deleteFile(Integer fileId);
    Optional<AttachedFile> getFile(int id);

}
