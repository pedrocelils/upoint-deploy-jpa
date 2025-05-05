package com.upoint_back.upoint.repository;

import com.upoint_back.upoint.domain.captura.CapturaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapturaClienteRepository extends JpaRepository<CapturaCliente, String> {
}
