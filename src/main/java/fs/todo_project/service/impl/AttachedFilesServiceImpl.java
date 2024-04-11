package fs.todo_project.service.impl;

import fs.todo_project.handler.TaskNotFoundException;
import fs.todo_project.model.AttachedFile;
import fs.todo_project.model.Task;
import fs.todo_project.repository.AttachedFilesRepository;
import fs.todo_project.repository.TaskRepository;
import fs.todo_project.service.AttachedFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

@Service
public class AttachedFilesServiceImpl implements AttachedFilesService {

    @Autowired
    private AttachedFilesRepository attachedFilesRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public AttachedFile uploadFile(MultipartFile attachedFile, Integer taskId) throws IOException {
        Task theTask = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("No task was found"));

        if (attachedFile.isEmpty()) {
            throw new IllegalArgumentException("The file is invalid");
        }

        AttachedFile theAttachment = new AttachedFile();
        theAttachment.setId(0);
        theAttachment.setFileName(attachedFile.getOriginalFilename());
        theAttachment.setFileData(attachedFile.getInputStream().readAllBytes());
        theAttachment.setTask(theTask);

        theTask.getFiles().add(theAttachment);
        return attachedFilesRepository.save(theAttachment);
    }

    @Override
    public void deleteFile(Integer fileId) {
        AttachedFile fileToDelete = attachedFilesRepository.findById(fileId).orElseThrow(() -> new IllegalArgumentException("No file was found"));
        Task task = fileToDelete.getTask();
        task.getFiles().remove(fileToDelete);
        taskRepository.save(task);
        attachedFilesRepository.deleteById(fileId);
    }

    @Override
    public AttachedFile getFile(Integer id) {
        return attachedFilesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No file was found"));
    }
}
