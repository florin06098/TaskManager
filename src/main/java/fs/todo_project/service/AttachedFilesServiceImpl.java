package fs.todo_project.service;

import fs.todo_project.model.AttachedFile;
import fs.todo_project.repository.AttachedFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;
@Service
public class AttachedFilesServiceImpl implements AttachedFilesService{

    @Autowired
    private AttachedFilesRepository attachedFilesRepository;

    @Override
    public AttachedFile save(AttachedFile file) {
        return attachedFilesRepository.save(file);
    }

    @Override
    public void deleteFile(Integer fileId) {
        attachedFilesRepository.deleteById(fileId);
    }

    @Override
    public Optional<AttachedFile> getFile(int id) {
        return attachedFilesRepository.findById(id);
    }
}
