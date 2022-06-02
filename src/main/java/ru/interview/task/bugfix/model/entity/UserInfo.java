package ru.interview.task.bugfix.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserInfo {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private String id;

    private Long userSystemId;

    private String fio;

    private Integer bonusesCount;

}
