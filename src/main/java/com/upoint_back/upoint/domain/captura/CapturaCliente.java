package com.upoint_back.upoint.domain.captura;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "cliente")
@Entity(name = "cliente")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CapturaCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String email;

}