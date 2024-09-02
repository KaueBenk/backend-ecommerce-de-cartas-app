package org.kauebenk.backendecommercedecartasapp.controller;

import org.kauebenk.backendecommercedecartasapp.DAO.ClienteDAO;
import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController implements IController<Cliente> {

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    @PostMapping
    public ResponseEntity<Long> salvar(@RequestBody Cliente cliente) {
        try {
            clienteDAO.salvar(cliente);
            return new ResponseEntity<>(cliente.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> alterar(@RequestBody Cliente cliente) {
        try {
            Cliente existingCliente = clienteDAO.consultar(cliente);
            if (existingCliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            clienteDAO.alterar(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            Cliente cliente = new Cliente();
            cliente.setId(id);
            clienteDAO.excluir(cliente);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> consultar(@PathVariable Long id) {
        try {
            Cliente cliente = new Cliente();
            cliente.setId(id);
            Cliente result = clienteDAO.consultar(cliente);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        try {
            List<Cliente> clientes = clienteDAO.listar(new Cliente());
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}