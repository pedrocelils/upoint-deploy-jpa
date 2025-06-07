package com.upoint_back.upoint.service;

import com.upoint_back.upoint.domain.registro.Registro;
import com.upoint_back.upoint.domain.registro.RegistroEnum;
import com.upoint_back.upoint.dto.manutencao.EditarRegistroDTO;
import com.upoint_back.upoint.dto.manutencao.RegistroAgrupadoDTO;
import com.upoint_back.upoint.dto.manutencao.RegistroPatchDTO;
import com.upoint_back.upoint.repository.RegistroRepository;
import com.upoint_back.upoint.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EditarRegistroService {

    private final RegistroRepository registroRepository;
    private final UserRepository userRepository;

    @Autowired
    public EditarRegistroService(RegistroRepository registroRepository, UserRepository userRepository) {
        this.registroRepository = registroRepository;
        this.userRepository = userRepository;
    }



    public List<RegistroAgrupadoDTO> editarRegistros(String nome) {
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(7);
        List<Registro> registros = registroRepository.findRegistrosUltimos7Dias(nome, dataLimite);

        Map<LocalDate, List<EditarRegistroDTO>> registrosAgrupados = registros.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getData_registro().toLocalDate(),
                        Collectors.mapping(r -> new EditarRegistroDTO(
                                r.getId().toString(),
                                r.getData_registro().toLocalTime().toString(),
                                r.getRegistro().toString()
                        ), Collectors.toList())
                ));

        return registrosAgrupados.entrySet().stream()
                .map(entry -> new RegistroAgrupadoDTO(
                        entry.getKey().toString(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
    }


    public Registro atualizarRegistro(UUID id, RegistroPatchDTO updateDTO) {
        Registro registro = registroRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Registro não encontrado."));

        if (updateDTO.getRegistro() != null) {
            registro.setRegistro(RegistroEnum.valueOf(updateDTO.getRegistro()));
        }

        if (updateDTO.getData() != null && updateDTO.getHora() != null) {
            LocalDate data = LocalDate.parse(updateDTO.getData());
            LocalTime hora = LocalTime.parse(updateDTO.getHora());
            LocalDateTime dataRegistro = LocalDateTime.of(data, hora);
            registro.setData_registro(dataRegistro);
        }

        return registroRepository.save(registro);
    }

    public void deletarRegistro(String id){
        registroRepository.deleteById(id);
    }



}
