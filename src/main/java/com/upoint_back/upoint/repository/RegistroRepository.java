package com.upoint_back.upoint.repository;

import com.upoint_back.upoint.domain.registro.Registro;
import com.upoint_back.upoint.domain.user.User;
import com.upoint_back.upoint.dto.registro.RegistroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, String> {
    List<Registro> findByUsuario(User usuario);
}
