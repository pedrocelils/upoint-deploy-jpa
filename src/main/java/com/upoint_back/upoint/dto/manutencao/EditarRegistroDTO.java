package com.upoint_back.upoint.dto.manutencao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
public class EditarRegistroDTO {
    private String id;
    private String hora;
    private String registro;

    public EditarRegistroDTO(String id, String hora, String registro) {
        this.id = id;
        this.hora = hora;
        this.registro = registro;
    }

}
