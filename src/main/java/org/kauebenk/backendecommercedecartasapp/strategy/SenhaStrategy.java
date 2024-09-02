package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;

public class SenhaStrategy implements IStrategy<Cliente> {
    @Override
    public String processar(Cliente entidade) {
        if (entidade.getSenha().length() < 8) {
            return "A senha deve ter pelo menos 8 caracteres.";
        }
        if (entidade.getSenha().equals(entidade.getSenha().toLowerCase())) {
            return "A senha deve ter pelo menos uma letra maiúscula.";
        }
        if (entidade.getSenha().equals(entidade.getSenha().toUpperCase())) {
            return "A senha deve ter pelo menos uma letra minúscula.";
        }
        if (entidade.getSenha().matches("[a-zA-Z0-9 ]*")) {
            return "A senha deve ter pelo menos um caractere especial.";
        }

        return null;
    }
}
