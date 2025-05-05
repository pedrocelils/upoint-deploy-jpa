package com.upoint_back.upoint.repository;

import com.upoint_back.upoint.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);

    boolean existsByCpf(String cpf);

    Optional<User> findByCpf(String cpf);

    Optional<User> findByEmail(String email);
}
