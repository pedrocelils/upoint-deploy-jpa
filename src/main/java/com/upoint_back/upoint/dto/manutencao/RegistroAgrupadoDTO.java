package com.upoint_back.upoint.dto.manutencao;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistroAgrupadoDTO {
    private String data;
    private List<EditarRegistroDTO> registros;

    public RegistroAgrupadoDTO(String data, List<EditarRegistroDTO> registros) {
        this.data = data;
        this.registros = registros;
    }

    // getters e setters
}