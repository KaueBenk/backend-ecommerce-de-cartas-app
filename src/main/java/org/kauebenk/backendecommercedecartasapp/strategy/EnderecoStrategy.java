package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Endereco;

public class EnderecoStrategy implements IStrategy<Endereco> {
    @Override
    public String processar(Endereco entidade) {
        StringBuilder errors = new StringBuilder();

        if (entidade.getTipoResidencia() == null) {
            errors.append("O tipo de residência é obrigatório.\n");
        }
        if (entidade.getTipoLogradouro() == null) {
            errors.append("O tipo de logradouro é obrigatório.\n");
        }
        if (entidade.getLogradouro() == null) {
            errors.append("O logradouro é obrigatório.\n");
        }
        if (entidade.getNumero() == null) {
            errors.append("O número é obrigatório.\n");
        }
        if (entidade.getBairro() == null) {
            errors.append("O bairro é obrigatório.\n");
        }
        if (entidade.getCep() == null) {
            errors.append("O CEP é obrigatório.\n");
        }
        if (entidade.getCidade() == null) {
            errors.append("A cidade é obrigatória.\n");
        }
        if (entidade.getEstado() == null) {
            errors.append("O estado é obrigatório.\n");
        }
        if (entidade.getPais() == null) {
            errors.append("O país é obrigatório.\n");
        }

        return !errors.isEmpty() ? errors.toString() : null;
    }
}