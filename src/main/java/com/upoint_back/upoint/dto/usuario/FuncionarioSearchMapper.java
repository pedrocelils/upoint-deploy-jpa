package com.upoint_back.upoint.dto.usuario;

import com.upoint_back.upoint.domain.user.User;

import java.util.List;

public class FuncionarioSearchMapper {
    public static FuncionarioSearchDTO toDTO(User funcionario) {
        FuncionarioSearchDTO dto = new FuncionarioSearchDTO();
        dto.setId(String.valueOf(funcionario.getId()));
        dto.setNome(funcionario.getNome());
        dto.setCpf(funcionario.getCpf());
        dto.setEmail(funcionario.getEmail());
        return dto;
    }
}
