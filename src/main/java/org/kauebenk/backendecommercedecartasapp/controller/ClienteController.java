package org.kauebenk.backendecommercedecartasapp.controller;

import org.kauebenk.backendecommercedecartasapp.DAO.ClienteDAO;
import org.kauebenk.backendecommercedecartasapp.dominio.Cartao;
import org.kauebenk.backendecommercedecartasapp.dominio.Cliente;
import org.kauebenk.backendecommercedecartasapp.dominio.Endereco;
import org.kauebenk.backendecommercedecartasapp.strategy.*;
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
            String validacao = validaCliente(cliente);
            if (validacao != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
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
            String validacao = validaCliente(cliente);
            if (validacao != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    private String validaCliente(Cliente cliente) {
        CartaoStrategy cartaoStrategy = new CartaoStrategy();
        String cartaoStrategyResult = null;
        for (Cartao cartao : cliente.getCartoes()) {
            cartaoStrategyResult = cartaoStrategy.processar(cartao);

        }
        if (cartaoStrategyResult != null) {
            return cartaoStrategyResult;
        }

        // implemente todas as Strategies
        CartoesStrategy cartoesStrategy = new CartoesStrategy();
        String cartoesStrategyResult = cartoesStrategy.processar(cliente);
        if (cartoesStrategyResult != null) {
            return cartoesStrategyResult;
        }

        ClienteStrategy clienteStrategy = new ClienteStrategy();
        if (clienteStrategy.processar(cliente) != null) {
            return clienteStrategy.processar(cliente);
        }

        EnderecosStrategy enderecosStrategy = new EnderecosStrategy();
        String enderecosStrategyResult = enderecosStrategy.processar(cliente);
        if (enderecosStrategyResult != null) {
            return enderecosStrategyResult;
        }

        EnderecoStrategy enderecoStrategy = new EnderecoStrategy();
        String enderecoStrategyResult = null;
        for (Endereco endereco : cliente.getEnderecos()) {
            enderecoStrategyResult = enderecoStrategy.processar(endereco);
        }
        if (enderecoStrategyResult != null) {
            return enderecoStrategyResult;
        }

        SenhaStrategy senhaStrategy = new SenhaStrategy();
        if (senhaStrategy.processar(cliente) != null) {
            return senhaStrategy.processar(cliente);
        }

        return null;

    }
}