package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Endereco;

public class EnderecoStrategy implements IStrategy<Endereco> {
    @Override
    public String processar(Endereco entidade) {
        if (entidade.getTipoResidencia() == null) {
            return "O tipo de residência é obrigatório.";
        }
        if (entidade.getTipoLogradouro() == null) {
            return "O tipo de logradouro é obrigatório.";
        }
        if (entidade.getLogradouro() == null) {
            return "O logradouro é obrigatório.";
        }
        if (entidade.getNumero() == null) {
            return "O número é obrigatório.";
        }
        if (entidade.getBairro() == null) {
            return "O bairro é obrigatório.";
        }
        if (entidade.getCep() == null) {
            return "O CEP é obrigatório.";
        }
        if (entidade.getCidade() == null) {
            return "A cidade é obrigatória.";
        }
        if (entidade.getEstado() == null) {
            return "O estado é obrigatório.";
        }
        if (entidade.getPais() == null) {
            return "O país é obrigatório.";
        }
        return null;
    }
}
