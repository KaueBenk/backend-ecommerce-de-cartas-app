package org.kauebenk.backendecommercedecartasapp.dominio;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Cartao extends EntidadeDominio {
    private String numero;
    private String nomeImpresso;
    private String bandeira;
    private String cvv;
    private String validade;
    private String observacoes;
}
