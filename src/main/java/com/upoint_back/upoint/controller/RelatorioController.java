package com.upoint_back.upoint.controller;

import com.upoint_back.upoint.dto.relatorios.RelatorioHorasDTO;
import com.upoint_back.upoint.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/registros")
    public ResponseEntity<byte[]> gerarRelatorioRegistros() {
        byte[] relatorio = relatorioService.gerarRelatorioRegistros();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .builder("inline")
                .filename("relatorio_registros.pdf")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(relatorio);
    }

    @GetMapping("/registroPorUsuario")
    public ResponseEntity<byte[]> gerarRelatorioRegistrosPorUsuario(
            @RequestParam String nomeUsuario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {

        // Converter LocalDate para java.sql.Date
        Date dataInicialSql = Date.valueOf(dataInicial);
        Date dataFinalSql = Date.valueOf(dataFinal);

        byte[] relatorio = relatorioService.gerarRelatorioRegistrosPorUsuario(nomeUsuario, dataInicialSql, dataFinalSql);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .builder("inline")
                .filename("relatorio_registros.pdf")
                .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(relatorio);
    }

}