package org.kauebenk.backendecommercedecartasapp.controller;

import org.kauebenk.backendecommercedecartasapp.DAO.ClienteDAO;
import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteDAO clienteDAO;

    @PostMapping
    public void salvar(@RequestBody Cliente cliente) {
        clienteDAO.salvar(cliente);
    }

    @PutMapping
    public void alterar(@RequestBody Cliente cliente) {
        clienteDAO.alterar(cliente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        clienteDAO.excluir(cliente);
    }

    @GetMapping("/{id}")
    public Cliente consultar(@PathVariable Long id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return clienteDAO.consultar(cliente);
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteDAO.listar(new Cliente());
    }
}