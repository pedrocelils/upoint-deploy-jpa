package com.upoint_back.upoint.controller;

import com.upoint_back.upoint.domain.empresa.Empresa;
import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.dto.empresa.EmpresaCompletaDTO;
import com.upoint_back.upoint.dto.empresa.EmpresaDTO;
import com.upoint_back.upoint.repository.EnderecoRepository;
import com.upoint_back.upoint.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EmpresaController(EmpresaService empresaService, EnderecoRepository enderecoRepository) {
        this.empresaService = empresaService;
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.buscarTodasEmpresas());
    }

    @PostMapping
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = empresaService.createEmpresa(empresaDTO);
        return ResponseEntity.ok(empresa);
    }


   /* @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarEmpresaPorId(@PathVariable String id) {
        return empresaService.buscarEmpresaId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable String id) {
        empresaService.deletarEmpresa(id);
        return ResponseEntity.noContent().build();
    }*/
}
