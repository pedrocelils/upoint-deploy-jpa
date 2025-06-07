package com.upoint_back.upoint.controller;

import com.upoint_back.upoint.domain.registro.Registro;
import com.upoint_back.upoint.dto.manutencao.EditarRegistroDTO;
import com.upoint_back.upoint.dto.manutencao.RegistroPatchDTO;
import com.upoint_back.upoint.repository.RegistroRepository;
import com.upoint_back.upoint.service.EditarRegistroService;
import com.upoint_back.upoint.service.RegistroService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/editar-registros")
public class EditarRegistroController {

    private final EditarRegistroService registroService;
    private final RegistroRepository registroRepository;

    public EditarRegistroController(EditarRegistroService registroService, RegistroRepository registroRepository) {
        this.registroService = registroService;
        this.registroRepository = registroRepository;
    }

        @GetMapping("/usuario/edit")
        public ResponseEntity<?> listarRegistrosAgrupadosPorNome(@RequestParam String nome) {
            LocalDateTime dataLimite = LocalDateTime.now().minusDays(7);

            // Busca os registros do usuário pelo nome e dataLimite (últimos 7 dias)
            List<Registro> registros = registroRepository.findRegistrosUltimos7Dias(nome, dataLimite);

            // Agrupa os registros por data (LocalDate), mantendo ordem
            Map<LocalDate, List<Map<String, String>>> agrupado = registros.stream()
                    .collect(Collectors.groupingBy(
                            r -> r.getData_registro().toLocalDate(),
                            LinkedHashMap::new,
                            Collectors.mapping(r -> Map.of(
                                    "id", r.getId().toString(),                // incluído o id
                                    "registro", r.getRegistro().toString(),
                                    "hora", r.getData_registro().toLocalTime().toString()
                            ), Collectors.toList())
                    ));

            // Monta a lista com data + registros para retorno JSON
            List<Map<String, Object>> resultado = agrupado.entrySet().stream()
                    .map(e -> Map.of(
                            "data", e.getKey().toString(),
                            "registros", e.getValue()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(resultado);
        }



    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarRegistro(
            @PathVariable UUID id,
            @RequestBody RegistroPatchDTO updateDTO) {

        try {
            Registro registroAtualizado = registroService.atualizarRegistro(id, updateDTO);
            return ResponseEntity.ok(registroAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable String id) {
        try {
            registroService.deletarRegistro(id);
            return ResponseEntity.noContent().build(); // HTTP 204 - No Content
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build(); // HTTP 404 - Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // HTTP 500 - Internal Server Error
        }
    }

}

