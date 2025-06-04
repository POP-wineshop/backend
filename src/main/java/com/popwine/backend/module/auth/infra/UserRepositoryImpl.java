package com.popwine.backend.module.auth.infra;

import com.popwine.backend.module.auth.domain.entity.User;
import com.popwine.backend.module.auth.domain.repository.UserRepository;
import com.popwine.backend.module.auth.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpa;

    @Override
    public Optional<User> findByUsername(Username username) {
        return jpa.findByUsername(username.getValue());
    }

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
        jpa.delete(user);
    }

    @Override
    public List<User> findAll() {
        return jpa.findAll();
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpa.findByUsername(username).isPresent();
    }
}

