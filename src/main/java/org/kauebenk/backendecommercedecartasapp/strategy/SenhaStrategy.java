package org.kauebenk.backendecommercedecartasapp.strategy;

import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;

public class SenhaStrategy implements IStrategy<Cliente> {
    @Override
    public String processar(Cliente entidade) {
        StringBuilder errors = new StringBuilder();

        if (entidade.getSenha().length() < 8) {
            errors.append("A senha deve ter pelo menos 8 caracteres.\n");
        }
        if (entidade.getSenha().equals(entidade.getSenha().toLowerCase())) {
            errors.append("A senha deve ter pelo menos uma letra maiúscula.\n");
        }
        if (entidade.getSenha().equals(entidade.getSenha().toUpperCase())) {
            errors.append("A senha deve ter pelo menos uma letra minúscula.\n");
        }
        if (entidade.getSenha().matches("[a-zA-Z0-9 ]*")) {
            errors.append("A senha deve ter pelo menos um caractere especial.\n");
        }

        return !errors.isEmpty() ? errors.toString() : null;
    }
}