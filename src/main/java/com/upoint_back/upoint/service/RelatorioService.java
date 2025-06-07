package com.upoint_back.upoint.service;

import com.upoint_back.upoint.domain.registro.Registro;
import com.upoint_back.upoint.domain.registro.RegistroEnum;
import com.upoint_back.upoint.domain.user.User;
import com.upoint_back.upoint.dto.relatorios.RelatorioHorasDTO;
import com.upoint_back.upoint.repository.RegistroRepository;
import com.upoint_back.upoint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistroRepository registroRepository;

    public byte[] gerarRelatorioRegistros() {
        try {
            // Caminho para o arquivo .jasper
            InputStream jasperStream = getClass().getResourceAsStream("/jasper/todosRegistros.jasper");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, new HashMap<>(), dataSource.getConnection());

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de registros", e);
        }
    }

    public byte[] gerarRelatorioRegistrosPorUsuario(String nomeUsuario, Date dataInicial, Date dataFinal) {
        try {
            // Caminho para o arquivo .jasper
            InputStream jasperStream = getClass().getResourceAsStream("/jasper/relatorioPorUsuario/BuscandoPorUsuariojrxml.jasper");

            // Parâmetros
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("nomeUsuario", nomeUsuario);
            parametros.put("dataInicial", dataInicial);
            parametros.put("dataFinal", dataFinal);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, dataSource.getConnection());

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório de registros", e);
        }
    }
}
