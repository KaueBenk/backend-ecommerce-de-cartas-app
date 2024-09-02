package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Cartao;
import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;

public class CartoesStrategy implements IStrategy<Cliente> {
    @Override
    public String processar(Cliente entidade) {
        if (entidade.getCartoes().isEmpty()) {
            return "O cliente deve ter pelo menos um cartão cadastrado.";
        }
        if (entidade.getCartoes().stream().noneMatch(Cartao::isECartaoPadrao)) {
            return "O cliente deve ter um cartão padrão.";
        }
        if (entidade.getCartoes().stream().filter(Cartao::isECartaoPadrao).count() > 1) {
            return "O cliente deve ter apenas um cartão padrão.";
        }
        return null;
    }
}
