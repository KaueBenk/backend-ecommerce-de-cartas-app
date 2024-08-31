package org.kauebenk.backendecommercedecartasapp.controller;

import java.util.List;

public interface IController<EntidadeDominio> {
    public void salvar(EntidadeDominio entidade);
    public void alterar(EntidadeDominio entidade);
    public void excluir(EntidadeDominio entidade);
    public EntidadeDominio consultar(EntidadeDominio entidade);
    public List<EntidadeDominio> listar(EntidadeDominio entidade);
}
