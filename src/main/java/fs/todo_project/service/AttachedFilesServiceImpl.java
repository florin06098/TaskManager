package fs.todo_project.service;

import fs.todo_project.entity.AttachedFile;
import fs.todo_project.repository.AttachedFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AttachedFilesServiceImpl implements AttachedFilesService{

    @Autowired
    private AttachedFilesRepository attachedFilesRepository;

    @Override
    public Optional<AttachedFile> downloadFile(int id) {
        return Optional.empty();
    }

    @Override
    public AttachedFile save(AttachedFile file) {
        return attachedFilesRepository.save(file);
    }

    @Override
    public void deleteFile(AttachedFile file) {
        attachedFilesRepository.delete(file);
    }

    @Override
    public Optional<AttachedFile> getFile(int id) {
        return attachedFilesRepository.findById(id);
    }
}
