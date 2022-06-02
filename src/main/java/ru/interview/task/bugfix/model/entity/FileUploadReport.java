package ru.interview.task.bugfix.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FileUploadReport {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;

    private boolean haveErrors;

    @Column(length = 4000)
    private String errors;

    private String fileUploadTime;

}
