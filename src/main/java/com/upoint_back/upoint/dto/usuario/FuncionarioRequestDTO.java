package com.upoint_back.upoint.dto.usuario;


public record FuncionarioRequestDTO(
        String login,
        String senha,
        String role,
        String cpf,
        String nome,
        String email,
        String telefone,
        String empresaId,

        // Endereço
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}