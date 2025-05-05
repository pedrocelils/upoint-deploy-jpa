package com.upoint_back.upoint.domain.empresa;

import com.upoint_back.upoint.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "empresa")
@Entity(name = "empresa")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String telefone;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

}
