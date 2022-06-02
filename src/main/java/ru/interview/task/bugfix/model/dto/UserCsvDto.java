package ru.interview.task.bugfix.model.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;


@Data
public class UserCsvDto {

    @CsvBindByName(column = "id")
    private Long userSystemId;

    @CsvBindByName
    private String fio;

    @CsvBindByName(column = "bonuses")
    private Integer bonusesCount;

    private String error;
}
