package com.popwine.backend.module.auth.domain.repository;

import com.popwine.backend.module.auth.domain.entity.User;
import com.popwine.backend.module.auth.domain.vo.Username;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
     Optional<User> findById(Long id);
     User save(User user);
     void delete(User user);
     List<User> findAll();
     boolean existsByUsername(String username);
}
