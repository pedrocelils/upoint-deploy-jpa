package com.upoint_back.upoint.service;

import com.upoint_back.upoint.domain.empresa.Empresa;
import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.dto.empresa.EmpresaDTO;
import com.upoint_back.upoint.repository.EmpresaRepository;
import com.upoint_back.upoint.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository, EnderecoRepository enderecoRepository) {
        this.empresaRepository = empresaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    // Método para criar uma nova empresa usando apenas os IDs
    /*public Empresa createEmpresa(EmpresaDTO empresaDTO) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(empresaDTO.getEndereco().getId());
        if (enderecoOptional.isEmpty()) {
            throw new RuntimeException("Endereço não encontrado com o ID fornecido.");
        }

        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(empresaDTO.getRazaoSocial());
        empresa.setNomeFantasia(empresaDTO.getNomeFantasia());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setEmail(empresaDTO.getEmail());
        empresa.setTelefone(empresaDTO.getTelefone());
        empresa.setEndereco(enderecoOptional.get());

        return empresaRepository.save(empresa);
    }*/

    public Empresa createEmpresa(EmpresaDTO dto) {
        // Cria entidade Endereco
        Endereco endereco = new Endereco();
        endereco.setBairro(dto.getEndereco().getBairro());
        endereco.setCep(dto.getEndereco().getCep());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setLogradouro(dto.getEndereco().getLogradouro());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setUf(dto.getEndereco().getUf());

        // Salva endereço no banco
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // Cria entidade Empresa
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setNomeFantasia(dto.getNomeFantasia());
        empresa.setCnpj(dto.getCnpj());
        empresa.setEmail(dto.getEmail());
        empresa.setTelefone(dto.getTelefone());
        empresa.setEndereco(enderecoSalvo);

        // Salva empresa com o endereço vinculado
        return empresaRepository.save(empresa);
    }

    // Método para buscar uma empresa por ID
    public Empresa getEmpresaById(String id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID fornecido."));
    }

    public List<Empresa> buscarTodasEmpresas() {
        return empresaRepository.findAll();
    }
   /* public Optional<Empresa> buscarEmpresaId(String id);
    public void deletarEmpresa(String id);*/

    }

