package com.upoint_back.upoint.dto.relatorios;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class RelatorioHorasDTO {

    private String nome;
    private String data;
    private String entrada;
    private String intervalo;
    private String retorno;
    private String saida;
    private String horaExtra;
    private String horaExtraFormatada;

    // Construtor que recebe os dados já tratados
    public RelatorioHorasDTO(
            String nome,
            LocalDateTime data,
            LocalDateTime entrada,
            LocalDateTime intervalo,
            LocalDateTime retorno,
            LocalDateTime saida,
            Duration horaExtra) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.nome = nome;
        this.data = (data != null) ? data.format(dateFormatter) : null;
        this.entrada = (entrada != null) ? entrada.format(timeFormatter) : null;
        this.intervalo = (intervalo != null) ? intervalo.format(timeFormatter) : null;
        this.retorno = (retorno != null) ? retorno.format(timeFormatter) : null;
        this.saida = (saida != null) ? saida.format(timeFormatter) : null;

        this.horaExtra = horaExtra != null ? horaExtra.toString() : "PT0S";
        this.horaExtraFormatada = formatarHoraExtra(horaExtra);
    }

    private String formatarHoraExtra(Duration duracao) {
        if (duracao == null || duracao.isZero() || duracao.isNegative()) {
            return "00:00";
        }
        long horas = duracao.toHours();
        long minutos = duracao.minusHours(horas).toMinutes();
        return String.format("%02d:%02d", horas, minutos);
    }
}
