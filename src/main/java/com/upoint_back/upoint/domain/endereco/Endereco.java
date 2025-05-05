package com.upoint_back.upoint.domain.endereco;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "endereco")
@Entity(name = "endereco")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
}