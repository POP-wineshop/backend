package com.popwine.backend.module.auth.infrastructure;

import com.popwine.backend.module.auth.domain.entity.User;
import com.popwine.backend.module.auth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpa;

    @Override
    public Optional<User> findById(Long id) {
    return jpa.findById(id);
    }

    @Override
    public User save(User user) {
        return jpa.save(user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }
}

