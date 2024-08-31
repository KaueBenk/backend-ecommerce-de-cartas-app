package org.kauebenk.backendecommercedecartasapp.dominio;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cliente extends EntidadeDominio {
    @Id
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    @ElementCollection
    private List<Endereco> enderecos;
    @ElementCollection
    private List<Cartao> cartoes;
}
