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
    public ResponseEntity<String> salvar(@RequestBody Cliente cliente) {
        try {
            String validacao = validaCliente(cliente);
            if (validacao != null) {
                return new ResponseEntity<>(validacao, HttpStatus.BAD_REQUEST);
            }
            clienteDAO.salvar(cliente);
            return new ResponseEntity<>(String.valueOf(cliente.getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping
    public ResponseEntity<String> alterar(@RequestBody Cliente cliente) {
        try {
            Cliente existingCliente = clienteDAO.consultar(cliente);
            if (existingCliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            String validacao = validaCliente(cliente);
            if (validacao != null) {
                return new ResponseEntity<>(validacao, HttpStatus.BAD_REQUEST);
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
        StringBuilder errors = new StringBuilder();

        CartaoStrategy cartaoStrategy = new CartaoStrategy();
        for (Cartao cartao : cliente.getCartoes()) {
            String cartaoStrategyResult = cartaoStrategy.processar(cartao);
            if (cartaoStrategyResult != null) {
                errors.append(cartaoStrategyResult);
            }
        }

        CartoesStrategy cartoesStrategy = new CartoesStrategy();
        String cartoesStrategyResult = cartoesStrategy.processar(cliente);
        if (cartoesStrategyResult != null) {
            errors.append(cartoesStrategyResult);
        }

        ClienteStrategy clienteStrategy = new ClienteStrategy();
        String clienteStrategyResult = clienteStrategy.processar(cliente);
        if (clienteStrategyResult != null) {
            errors.append(clienteStrategyResult);
        }

        EnderecosStrategy enderecosStrategy = new EnderecosStrategy();
        String enderecosStrategyResult = enderecosStrategy.processar(cliente);
        if (enderecosStrategyResult != null) {
            errors.append(enderecosStrategyResult);
        }

        EnderecoStrategy enderecoStrategy = new EnderecoStrategy();
        for (Endereco endereco : cliente.getEnderecos()) {
            String enderecoStrategyResult = enderecoStrategy.processar(endereco);
            if (enderecoStrategyResult != null) {
                errors.append(enderecoStrategyResult);
            }
        }

        SenhaStrategy senhaStrategy = new SenhaStrategy();
        String senhaStrategyResult = senhaStrategy.processar(cliente);
        if (senhaStrategyResult != null) {
            errors.append(senhaStrategyResult);
        }

        return errors.length() > 0 ? errors.toString() : null;
    }
}