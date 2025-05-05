package com.upoint_back.upoint.dto.empresa;

import com.upoint_back.upoint.dto.endereco.EnderecoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpresaDTO {
    private String id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String telefone;
    private EnderecoDTO endereco;

}