package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Cartao;

import java.util.List;

public class CartaoStrategy implements IStrategy<Cartao> {
    @Override
    public String processar(Cartao entidade) {
        StringBuilder errors = new StringBuilder();
        List<String> bandeiras = List.of("Visa", "Mastercard", "Elo", "American Express");

        if (entidade.getNumero().length() != 16) {
            errors.append("O número do cartão deve ter 16 dígitos.\n");
        }
        if (entidade.getNomeImpresso().length() < 5) {
            errors.append("O nome impresso no cartão deve ter pelo menos 5 caracteres.\n");
        }
        if (entidade.getBandeira() == null) {
            errors.append("A bandeira do cartão é obrigatória.\n");
        } else if (!bandeiras.contains(entidade.getBandeira())) {
            errors.append("A bandeira do cartão é inválida.\n");
        }
        if (entidade.getCvv() == null) {
            errors.append("O código de segurança é obrigatório.\n");
        } else if (entidade.getCvv().length() != 3) {
            errors.append("O código de segurança deve ter 3 dígitos.\n");
        }
        if (entidade.getValidade() == null) {
            errors.append("A validade do cartão é obrigatória.\n");
        }

        return !errors.isEmpty() ? errors.toString() : null;
    }
}