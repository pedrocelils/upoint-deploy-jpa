package com.upoint_back.upoint.domain.registro;

import com.upoint_back.upoint.domain.user.User;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "registros")
@Entity(name = "registros")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private RegistroEnum registro;
    private Instant data_registro;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;
}
