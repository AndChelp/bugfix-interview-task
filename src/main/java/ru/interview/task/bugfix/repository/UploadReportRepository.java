package ru.interview.task.bugfix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.interview.task.bugfix.model.entity.FileUploadReport;

@Repository
public interface UploadReportRepository extends JpaRepository<FileUploadReport, UUID> {

}
