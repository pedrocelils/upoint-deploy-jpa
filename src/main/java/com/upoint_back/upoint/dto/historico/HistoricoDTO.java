package com.upoint_back.upoint.dto.historico;

import com.upoint_back.upoint.domain.registro.RegistroEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class HistoricoDTO {
    private String hora;
    private String registro;


    public HistoricoDTO(RegistroEnum registro, LocalDateTime hora) {
        this.registro = registro.name();
        this.hora = hora.toString();
    }


}
