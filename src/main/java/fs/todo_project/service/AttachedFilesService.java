package fs.todo_project.service;

import fs.todo_project.model.AttachedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface AttachedFilesService {
    AttachedFile uploadFile(MultipartFile file, Integer taskId) throws IOException;
    void deleteFile(Integer fileId);
    AttachedFile getFile(Integer id);
}
