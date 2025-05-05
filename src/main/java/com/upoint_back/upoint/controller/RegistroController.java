package com.upoint_back.upoint.controller;

import com.upoint_back.upoint.domain.registro.Registro;
import com.upoint_back.upoint.dto.registro.RegistroDTO;
import com.upoint_back.upoint.dto.usuario.RegisterDTO;
import com.upoint_back.upoint.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registros")
@CrossOrigin(origins = "*")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @PostMapping
    public ResponseEntity<?> criarRegistro(@RequestBody RegistroDTO dto) {
        try {
            Registro registro = registroService.registrarPonto(dto.registro());
            return ResponseEntity.ok(registro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Tipo de registro inválido.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao registrar ponto: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Registro>> obterRegistrosUsuarioLogado() {
        try {
            List<Registro> registros = registroService.getAllRegistros();
            return ResponseEntity.ok(registros);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/count")
    public Map<String, Long> contarRegistross() {
        long total = registroService.totalRegistros();
        return Map.of("total", total);
    }
}
