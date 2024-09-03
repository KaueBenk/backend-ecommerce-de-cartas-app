package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;
import org.kauebenk.backendecommercedecartasapp.dominio.Endereco;

public class EnderecosStrategy implements IStrategy<Cliente> {
    @Override
    public String processar(Cliente entidade) {
        StringBuilder errors = new StringBuilder();

        if (entidade.getEnderecos().isEmpty()) {
            errors.append("O cliente deve ter pelo menos um endereço cadastrado.\n");
        }
        if (entidade.getEnderecos().stream().noneMatch(Endereco::isEnderecoEntregaPadrao)) {
            errors.append("O cliente deve ter um endereço de entrega padrão.\n");
        }
        if (entidade.getEnderecos().stream().noneMatch(Endereco::isEnderecoCobranca)) {
            errors.append("O cliente deve ter um endereço de cobrança.\n");
        }
        if (entidade.getEnderecos().stream().filter(Endereco::isEnderecoCobranca).count() > 1) {
            errors.append("O cliente deve ter apenas um endereço de cobrança.\n");
        }
        if (entidade.getEnderecos().stream().filter(Endereco::isEnderecoEntregaPadrao).count() > 1) {
            errors.append("O cliente deve ter apenas um endereço de entrega padrão.\n");
        }

        return !errors.isEmpty() ? errors.toString() : null;
    }
}