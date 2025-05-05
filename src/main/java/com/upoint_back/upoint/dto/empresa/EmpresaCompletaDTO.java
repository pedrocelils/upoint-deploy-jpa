package com.upoint_back.upoint.dto.empresa;

import com.upoint_back.upoint.dto.endereco.EnderecoDTO;

public class EmpresaCompletaDTO {
    private EmpresaDTO empresa;
    private EnderecoDTO endereco;

    public EmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDTO empresa) {
        this.empresa = empresa;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
