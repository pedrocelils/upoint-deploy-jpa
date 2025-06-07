package com.upoint_back.upoint.service;

import com.upoint_back.upoint.domain.registro.Registro;
import com.upoint_back.upoint.domain.registro.RegistroEnum;
import com.upoint_back.upoint.domain.user.User;
import com.upoint_back.upoint.dto.historico.HistoricoDTO;
import com.upoint_back.upoint.repository.RegistroRepository;
import com.upoint_back.upoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private UserRepository userRepository;

    public Registro registrarPonto(String tipoRegistro) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName(); // Recupera o login do usuário logado

        Optional<User> userOpt = Optional.ofNullable(userRepository.findByLogin(login));
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        RegistroEnum tipo;
        try {
            tipo = RegistroEnum.valueOf(tipoRegistro.toUpperCase()); // Certifica que é UPPERCASE
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Tipo de registro inválido: " + tipoRegistro);
        }

        User user = userOpt.get();

        Registro registro = new Registro();
        registro.setUsuario(user);
        registro.setRegistro(RegistroEnum.valueOf(tipoRegistro));
        registro.setData_registro(LocalDateTime.now());


        return registroRepository.save(registro);
    }

    public List<HistoricoDTO> getAllRegistros() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();

        Optional<User> userOpt = Optional.ofNullable(userRepository.findByLogin(login));
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        User user = userOpt.get();
        List<Registro> registros = registroRepository.findByUsuario(user);

        return registros.stream()
                .map(r -> new HistoricoDTO(
                        r.getRegistro(),
                        r.getData_registro()

                ))
                .toList();
    }

    public long totalRegistros() {
        return registroRepository.count();
    }
}