package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Cartao;
import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;

public class CartoesStrategy implements IStrategy<Cliente> {
    @Override
    public String processar(Cliente entidade) {
        StringBuilder errors = new StringBuilder();

        if (entidade.getCartoes().isEmpty()) {
            errors.append("O cliente deve ter pelo menos um cartão cadastrado.\n");
        }
        if (entidade.getCartoes().stream().noneMatch(Cartao::isCartaoPadrao)) {
            errors.append("O cliente deve ter um cartão padrão.\n");
        }
        if (entidade.getCartoes().stream().filter(Cartao::isCartaoPadrao).count() > 1) {
            errors.append("O cliente deve ter apenas um cartão padrão.\n");
        }

        return !errors.isEmpty() ? errors.toString() : null;
    }
}