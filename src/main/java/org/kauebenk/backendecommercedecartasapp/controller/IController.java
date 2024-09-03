package org.kauebenk.backendecommercedecartasapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IController<EntidadeDominio> {
    @PostMapping
    ResponseEntity<String> salvar(EntidadeDominio entidade);
    @PutMapping
    ResponseEntity<String> alterar(EntidadeDominio entidade);
    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable Long id);
    @GetMapping("{id}")
    ResponseEntity<EntidadeDominio> consultar(@PathVariable Long id);
    @GetMapping
    ResponseEntity<List<EntidadeDominio>> listar();
}