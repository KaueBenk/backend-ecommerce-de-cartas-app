package org.kauebenk.backendecommercedecartasapp.dominio;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Telefone extends EntidadeDominio {
    private String tipo;
    private String ddd;
    private String numero;
}
