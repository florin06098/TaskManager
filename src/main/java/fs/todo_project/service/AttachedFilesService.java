package fs.todo_project.service;

import fs.todo_project.entity.AttachedFile;
import java.util.Optional;

public interface AttachedFilesService {
    Optional<AttachedFile> downloadFile(int id);
    AttachedFile save(AttachedFile file);
    void deleteFile(AttachedFile file);
    Optional<AttachedFile> getFile(int id);

}
