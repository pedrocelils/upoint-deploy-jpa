package com.upoint_back.upoint.service;

import com.upoint_back.upoint.domain.captura.CapturaCliente;
import com.upoint_back.upoint.repository.CapturaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapturaClienteService {

    @Autowired
    private CapturaClienteRepository CapturaClienteRepository;
    @Autowired
    private CapturaClienteRepository capturaClienteRepository;


    public CapturaCliente salvarCliente(CapturaCliente cliente){
        return capturaClienteRepository.save(cliente);
    }

    public List<CapturaCliente> findAll() {
        return capturaClienteRepository.findAll();
    }
}
