package fs.todo_project.controller;

import fs.todo_project.model.AttachedFile;
import fs.todo_project.service.AttachedFilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AttachedFilesController {
    private final AttachedFilesService attachedFilesService;

    @PostMapping("/uploadFile")
    public ResponseEntity<AttachedFile> addFileToTask(@RequestParam Integer taskId, @RequestParam("file") MultipartFile attachedFile) throws IOException {
        return new ResponseEntity<>(attachedFilesService.uploadFile(attachedFile, taskId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFile/{fileId}")
    public ResponseEntity<Void> deleteFileFromTask(@PathVariable Integer fileId) {
        attachedFilesService.deleteFile(fileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer fileId) {
        AttachedFile attachedFile = attachedFilesService.getFile(fileId);
        byte[] fileContent = attachedFile.getFileData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(attachedFile.getFileName()).build());

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}
