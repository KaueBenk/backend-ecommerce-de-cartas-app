package org.kauebenk.backendecommercedecartasapp.DAO;

import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;
import org.kauebenk.backendecommercedecartasapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteDAO implements IDAO<Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void salvar(Cliente entidade) {
        clienteRepository.save(entidade);
    }

    @Override
    public void alterar(Cliente entidade) {
        clienteRepository.save(entidade);
    }

    @Override
    public void excluir(Cliente entidade) {
        clienteRepository.deleteById(entidade.getId());
    }

    @Override
    public Cliente consultar(Cliente entidade) {
        return clienteRepository.findById(entidade.getId()).orElse(null);
    }

    @Override
    public List<Cliente> listar(Cliente entidade) {
        return clienteRepository.findAll();
    }
}