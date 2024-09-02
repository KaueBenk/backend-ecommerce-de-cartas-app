package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;
import org.kauebenk.backendecommercedecartasapp.dominio.Endereco;

public class EnderecosStrategy implements IStrategy<Cliente> {
    @Override
    public String processar(Cliente entidade) {
        if (entidade.getEnderecos().isEmpty()) {
            return "O cliente deve ter pelo menos um endereço cadastrado.";
        }
        if (entidade.getEnderecos().stream().noneMatch(Endereco::isEEnderecoEntregaPadrao)) {
            return "O cliente deve ter um endereço de entrega padrão.";
        }
        if (entidade.getEnderecos().stream().noneMatch(Endereco::isEEnderecoCobranca)) {
            return "O cliente deve ter um endereço de cobrança.";
        }
        if (entidade.getEnderecos().stream().filter(Endereco::isEEnderecoCobranca).count() > 1) {
            return "O cliente deve ter apenas um endereço de cobrança.";
        }
        if (entidade.getEnderecos().stream().filter(Endereco::isEEnderecoEntregaPadrao).count() > 1) {
            return "O cliente deve ter apenas um endereço de entrega padrão.";
        }

        return null;
    }
}
