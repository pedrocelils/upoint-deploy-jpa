package com.upoint_back.upoint.dto.manutencao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class RegistroPatchDTO {
    private UUID id;
    private String registro; // opcional
    private String data;     // exemplo: "2025-06-03"
    private String hora;     // exemplo: "07:00:00"
}
