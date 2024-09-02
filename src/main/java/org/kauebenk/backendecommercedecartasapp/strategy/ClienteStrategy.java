package org.kauebenk.backendecommercedecartasapp.strategy;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;

public class ClienteStrategy implements IStrategy<Cliente> {
    @Override
    public String processar(Cliente entidade) {
        if (entidade.getGenero() == null) {
            return "O gênero é obrigatório.";
        }
        if (entidade.getNome() == null) {
            return "O nome é obrigatório.";
        }
        if (entidade.getCpf() == null) {
            return "O CPF é obrigatório.";
        }
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(entidade.getCpf());
        } catch (InvalidStateException e) {
            return "CPF inválido.";
        }
        if (entidade.getTelefone() == null) {
            return "O telefone é obrigatório.";
        }
        if (entidade.getEmail() == null) {
            return "O e-mail é obrigatório.";
        }
        if (entidade.getSenha() == null) {
            return "A senha é obrigatória.";
        }
        if (entidade.getEnderecos() == null) {
            return "O endereço é obrigatório.";
        }
        return null;
    }
}
