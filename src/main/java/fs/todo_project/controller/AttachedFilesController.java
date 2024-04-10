package fs.todo_project.controller;

import fs.todo_project.model.AttachedFile;
import fs.todo_project.model.Task;
import fs.todo_project.service.AttachedFilesService;
import fs.todo_project.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AttachedFilesController {
    private final AttachedFilesService attachedFilesService;
    private final TaskService taskService;

    @PostMapping("/uploadFile")
    public Task addFileToTask(@RequestParam Integer taskId, @RequestParam("file") MultipartFile attachedFile) throws IOException {
        Optional<Task> task = taskService.getTask(taskId);
        if (task.isEmpty()) {
            throw new IllegalArgumentException("No task was found");
        }
        if (attachedFile.isEmpty()) {
            throw new IllegalArgumentException("The file is invalid");
        }

        Task theTask = task.get();

        AttachedFile theAttachment = new AttachedFile();
        theAttachment.setId(0);
        theAttachment.setFileName(attachedFile.getOriginalFilename());
        theAttachment.setFileData(attachedFile.getInputStream().readAllBytes());
        theAttachment.setTask(theTask);


        theTask.getFiles().add(theAttachment);
        return taskService.save(theTask);
    }

    @DeleteMapping("/deleteFile/{fileId}")
    public String deleteFileFromTask(@PathVariable Integer fileId) {
        Optional<AttachedFile> optionalFile = attachedFilesService.getFile(fileId);
        if (optionalFile.isPresent()) {
            AttachedFile fileToDelete = optionalFile.get();
            Task task = fileToDelete.getTask();
            task.getFiles().remove(fileToDelete);
            taskService.save(task);
            attachedFilesService.deleteFile(fileId);
            return "Successfully deleted the file";
        } else{
            return "Could not file the requested file";
        }
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer fileId) {
        Optional<AttachedFile> file = attachedFilesService.getFile(fileId);
        if (file.isEmpty()) {
            throw new IllegalArgumentException("No file was found");
        }
        AttachedFile attachedFile = file.get();
        byte[] fileContent = attachedFile.getFileData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(attachedFile.getFileName()).build());

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}
