package com.upoint_back.upoint.service;

import com.upoint_back.upoint.domain.empresa.Empresa;
import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.domain.user.User;
import com.upoint_back.upoint.domain.user.UserRole;
import com.upoint_back.upoint.dto.usuario.FuncionarioRequestDTO;
import com.upoint_back.upoint.repository.EmpresaRepository;
import com.upoint_back.upoint.repository.EnderecoRepository;
import com.upoint_back.upoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
    public User findById(String id) {
        return userRepository.findById(id).orElse(null); // Retorna null caso o usuário não seja encontrado
    }

    @Transactional
    public String cadastrarFuncionario(FuncionarioRequestDTO dto) {
        // Verificar se o usuário já existe
        if (userRepository.findByLogin(dto.login()) != null) {
            return "Usuário já existe com o login: " + dto.login();
        }

        // Criação de senha criptografada
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());

        // Busca a empresa pelo ID
        Empresa empresa = empresaRepository.findById(dto.empresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        // Criação do endereço com os dados do DTO
        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setUf(dto.estado());
        endereco.setCep(dto.cep());

        // Salva o endereço
        enderecoRepository.save(endereco);

        // Criação do usuário (funcionário)
        User newUser = new User();
        newUser.setLogin(dto.login());
        newUser.setPassword(encryptedPassword);
        newUser.setRole(UserRole.valueOf(dto.role()));  // Definindo a role do usuário
        newUser.setCpf(dto.cpf());
        newUser.setNome(dto.nome());
        newUser.setEmail(dto.email());
        newUser.setTelefone(dto.telefone());
        newUser.setEmpresa(empresa);
        newUser.setEndereco(endereco);

        // Salva o usuário
        userRepository.save(newUser);

        return "Usuário cadastrado com sucesso!";
    }

    public long totalUsuarios() {
        return userRepository.count();
    }


}
