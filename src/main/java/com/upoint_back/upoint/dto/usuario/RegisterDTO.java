package com.upoint_back.upoint.dto.usuario;

import com.upoint_back.upoint.domain.empresa.Empresa;
import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.domain.user.UserRole;

public record RegisterDTO(
        String login,
        String password,
        UserRole role,
        String cpf,
        String nome,
        String email,
        String telefone,
        String empresaId,
        String enderecoId ) {
}
