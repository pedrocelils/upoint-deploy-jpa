package com.upoint_back.upoint.repository;

import com.upoint_back.upoint.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<User, String> {

    Optional<User> findByCpf(String cpf);

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByLogin(String login);
}