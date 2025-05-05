package com.upoint_back.upoint.service;

import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.dto.endereco.EnderecoDTO;
import com.upoint_back.upoint.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listaEnderecos(){
        return enderecoRepository.findAll();
    }

    public  Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> buscarEnderecoId(String id){
        return enderecoRepository.findById(id);
    }

    /*public void deletarEndereco(String id){
        enderecoRepository.deleteById(id);
    }*/
}
