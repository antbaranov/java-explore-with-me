package ru.practicum.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User WHERE :ids is null or id in :ids")
    List<User> findByIdIn(List<Long> ids, PageRequest pageRequest);
}