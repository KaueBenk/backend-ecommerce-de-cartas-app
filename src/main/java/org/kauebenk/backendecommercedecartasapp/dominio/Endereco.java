package org.kauebenk.backendecommercedecartasapp.dominio;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco extends EntidadeDominio {
    private String nome;
    private String tipoResidencia;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String observacoes;
    private boolean eEnderecoCobranca;
    private boolean eEnderecoEntregaPadrao;
}
