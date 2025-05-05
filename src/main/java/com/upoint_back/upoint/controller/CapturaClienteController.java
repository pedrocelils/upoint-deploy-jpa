package com.upoint_back.upoint.controller;

import com.upoint_back.upoint.domain.captura.CapturaCliente;
import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.service.CapturaClienteService;
import com.upoint_back.upoint.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class CapturaClienteController {

    @Autowired
    CapturaClienteService clienteServices;

    @GetMapping
    public List<CapturaCliente> listarClientes(){
        return clienteServices.findAll();
    }

    @PostMapping
    public ResponseEntity<CapturaCliente> salvarCliente(@RequestBody CapturaCliente body){
        CapturaCliente cliente = clienteServices.salvarCliente(body);
        return ResponseEntity.ok(cliente);
    }
}
