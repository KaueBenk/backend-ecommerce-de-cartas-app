package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Cartao;

import java.util.List;

public class CartaoStrategy implements IStrategy<Cartao> {
    @Override
    public String processar(Cartao entidade) {
        List<String> bandeiras = List.of("Visa", "Mastercard", "Elo", "American Express");
        if (entidade.getNumero().length() != 16) {
            return "O número do cartão deve ter 16 dígitos.";
        }
        if (entidade.getNomeImpresso().length() < 5) {
            return "O nome impresso no cartão deve ter pelo menos 5 caracteres.";
        }
        if (entidade.getBandeira() == null) {
            return "A bandeira do cartão é obrigatória.";
        }
        if (!bandeiras.contains(entidade.getBandeira())) {
            return "A bandeira do cartão é inválida.";
        }
        if (entidade.getCvv() == null) {
            return "O código de segurança é obrigatório.";
        }
        if (entidade.getCvv().length() != 3) {
            return "O código de segurança deve ter 3 dígitos.";
        }
        if (entidade.getValidade() == null) {
            return "A validade do cartão é obrigatória.";
        }
        return null;
    }
}
