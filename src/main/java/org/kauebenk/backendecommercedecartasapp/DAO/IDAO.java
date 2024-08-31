package org.kauebenk.backendecommercedecartasapp.DAO;

import java.util.List;

public interface IDAO<EntidadeDominio> {
    public void salvar(EntidadeDominio entidade);
    public void alterar(EntidadeDominio entidade);
    public void excluir(EntidadeDominio entidade);
    public EntidadeDominio consultar(EntidadeDominio entidade);
    public List<EntidadeDominio> listar(EntidadeDominio entidade);
}
