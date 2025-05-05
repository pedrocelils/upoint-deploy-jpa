package com.upoint_back.upoint.repository;

import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.dto.endereco.EnderecoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}
