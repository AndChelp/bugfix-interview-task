package ru.interview.task.bugfix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.interview.task.bugfix.model.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findByUserSystemId(Long userSystemId);
}
