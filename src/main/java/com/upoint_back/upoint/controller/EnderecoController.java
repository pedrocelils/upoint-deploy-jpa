package com.upoint_back.upoint.controller;

import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.dto.endereco.EnderecoDTO;
import com.upoint_back.upoint.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "*")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoServices;

    @GetMapping
    public List<Endereco> listarEnderecos(){
        return enderecoServices.listaEnderecos();
    }

    @PostMapping
    public ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco body){
        Endereco endereco = enderecoServices.salvarEndereco(body);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable String id){
        return enderecoServices.buscarEnderecoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   /* @DeleteMapping
    public ResponseEntity<Void> deleteEndereco(String id) {
        enderecoServices.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }*/
}
