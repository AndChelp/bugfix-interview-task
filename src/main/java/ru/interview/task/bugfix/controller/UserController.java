package ru.interview.task.bugfix.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import ru.interview.task.bugfix.model.entity.FileUploadReport;
import ru.interview.task.bugfix.model.entity.UserInfo;
import ru.interview.task.bugfix.repository.UploadReportRepository;
import ru.interview.task.bugfix.service.FileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UploadReportRepository repository;
    private final FileService fileService;

    @PutMapping("/accrual")
    public String uploadFileLocal(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.accrualBonuses(file.getInputStream());
    }

    @GetMapping("/report")
    public List<FileUploadReport> getUploadReports() {
        return repository.findAll();
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserInfo>> getUsers() {
        return ResponseEntity.ok(fileService.getUsers());
    }
}
