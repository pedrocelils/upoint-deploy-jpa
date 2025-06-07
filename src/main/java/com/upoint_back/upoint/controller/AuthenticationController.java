package com.upoint_back.upoint.controller;

import com.upoint_back.upoint.domain.empresa.Empresa;
import com.upoint_back.upoint.domain.endereco.Endereco;
import com.upoint_back.upoint.domain.user.User;
import com.upoint_back.upoint.dto.usuario.*;
import com.upoint_back.upoint.infraestructure.TokenService;
import com.upoint_back.upoint.repository.EmpresaRepository;
import com.upoint_back.upoint.repository.EnderecoRepository;
import com.upoint_back.upoint.repository.FuncionarioRepository;
import com.upoint_back.upoint.repository.UserRepository;
import com.upoint_back.upoint.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private AuthorizationService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @GetMapping("/funcionarios/count")
    public Map<String, Long> contarUsuarios() {
        long total = service.totalUsuarios();
        return Map.of("total", total);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody FuncionarioRequestDTO data) {
        // Chama o serviço para cadastrar o funcionário
        String response = service.cadastrarFuncionario(data);

        // Verifica se a operação foi bem-sucedida ou se houve algum erro
        if (response.equals("Usuário cadastrado com sucesso!")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String id) {
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @GetMapping("/busca")
    public List<FuncionarioSearchDTO> buscarFuncionarios(@RequestParam String search) {
        return service.buscarPorNomeCpfEmail(search);
    }
}
