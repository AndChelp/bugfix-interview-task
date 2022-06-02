package ru.interview.task.bugfix.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.interview.task.bugfix.model.dto.UserCsvDto;
import ru.interview.task.bugfix.model.entity.FileUploadReport;
import ru.interview.task.bugfix.model.entity.UserInfo;
import ru.interview.task.bugfix.repository.UploadReportRepository;
import ru.interview.task.bugfix.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class FileService {

    private final UserRepository userRepository;
    private final UploadReportRepository uploadReportRepository;

    public List<UserInfo> getUsers() {
        return userRepository.findAll();
    }

    public String accrualBonuses(InputStream fileData) {
        var csvUserBeans = new CsvToBeanBuilder<UserCsvDto>(new InputStreamReader(fileData))
                .withType(UserCsvDto.class)
                .build()
                .parse();
        var uploadUserResult = accrualBonuses(csvUserBeans);
        uploadReportRepository.save(new FileUploadReport(
                UUID.randomUUID().toString(),
                StringUtils.isNotEmpty(uploadUserResult),
                uploadUserResult,
                new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date())
        ));
        return uploadUserResult;
    }

    private String accrualBonuses(List<UserCsvDto> dataList) {
        var usersInfo = new ArrayList<UserInfo>();
        var errorFound = false;
        for (var userData : dataList) {
            try {
                var user = userRepository.findByUserSystemId(userData.getUserSystemId())
                        .orElseThrow(() -> new RuntimeException("Пользователь с таким id не найден"));
                user.setBonusesCount(user.getBonusesCount() != null
                        ? user.getBonusesCount() + userData.getBonusesCount()
                        : userData.getBonusesCount());
                usersInfo.add(user);
            } catch (RuntimeException e) {
                userData.setError(e.getMessage());
                errorFound = true;
            }
        }

        if (errorFound) {
            return dataList.stream()
                    .filter(it -> StringUtils.isNotEmpty(it.getError()))
                    .map(UserCsvDto::toString)
                    .collect(Collectors.joining("\n"));
        } else {
            userRepository.saveAll(usersInfo);
            return null;
        }
    }
}
