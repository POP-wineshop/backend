package com.popwine.backend.module.auth.infrastructure;

import com.popwine.backend.module.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JpaUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String value);
}



