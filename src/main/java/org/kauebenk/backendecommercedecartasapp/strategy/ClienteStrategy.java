package org.kauebenk.backendecommercedecartasapp.strategy;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;

public class ClienteStrategy implements IStrategy<Cliente> {
    @Override
    public String processar(Cliente entidade) {
        StringBuilder errors = new StringBuilder();

        if (entidade.getGenero() == null) {
            errors.append("O gênero é obrigatório.\n");
        }
        if (entidade.getNome() == null) {
            errors.append("O nome é obrigatório.\n");
        }
        if (entidade.getCpf() == null) {
            errors.append("O CPF é obrigatório.\n");
        } else {
            CPFValidator cpfValidator = new CPFValidator();
            try {
                cpfValidator.assertValid(entidade.getCpf());
            } catch (InvalidStateException e) {
                errors.append("CPF inválido.\n");
            }
        }
        if (entidade.getTelefone() == null) {
            errors.append("O telefone é obrigatório.\n");
        }
        if (entidade.getEmail() == null) {
            errors.append("O e-mail é obrigatório.\n");
        }
        if (entidade.getSenha() == null) {
            errors.append("A senha é obrigatória.\n");
        }
        if (entidade.getEnderecos() == null) {
            errors.append("O endereço é obrigatório.\n");
        }

        return !errors.isEmpty() ? errors.toString() : null;
    }
}