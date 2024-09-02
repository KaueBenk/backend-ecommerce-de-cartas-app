package org.kauebenk.backendecommercedecartasapp.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cliente extends EntidadeDominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private boolean ativo;
    @ElementCollection
    private List<Endereco> enderecos;
    @ElementCollection
    private List<Cartao> cartoes;
}
