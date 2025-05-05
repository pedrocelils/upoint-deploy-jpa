package com.upoint_back.upoint.repository;

import com.upoint_back.upoint.domain.empresa.Empresa;
import com.upoint_back.upoint.dto.empresa.EmpresaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String> {
}
