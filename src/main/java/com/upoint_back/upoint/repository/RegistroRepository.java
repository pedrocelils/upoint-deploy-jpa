package com.upoint_back.upoint.repository;

import com.upoint_back.upoint.domain.registro.Registro;
import com.upoint_back.upoint.domain.user.User;
import com.upoint_back.upoint.dto.historico.HistoricoDTO;
import com.upoint_back.upoint.dto.registro.RegistroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, String> {
    List<Registro> findByUsuario(User usuario);

    @Query("SELECT r FROM registros r WHERE r.usuario.id = :usuarioId AND r.data_registro BETWEEN :dataInicial AND :dataFinal ORDER BY r.data_registro")
    List<Registro> findByUsuarioIdAndDataRegistroBetween(
            String usuarioId,
            LocalDateTime dataInicial,
            LocalDateTime dataFinal
    );

    @Query("SELECT r FROM registros r WHERE r.usuario.nome = :nome AND r.data_registro >= :dataLimite ORDER BY r.data_registro DESC")
    List<Registro> findRegistrosUltimos7Dias(@Param("nome") String nome, @Param("dataLimite") LocalDateTime dataLimite);

}
