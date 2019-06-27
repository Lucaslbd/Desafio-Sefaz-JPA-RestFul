package br.com.desafiosefaz.controller;

import br.com.desafiosefaz.dao.TelefoneDao;
import br.com.desafiosefaz.domain.Telefone;
import java.util.List;

public class TelefoneController {
   
    private final TelefoneDao dao = new TelefoneDao();
    
    public int salvar(Telefone telefone) throws Exception {
       return dao.salvar(telefone);
    }
    
    public void excluir(Telefone telefone) throws Exception{
        dao.excluir(telefone);
    }
    
    public List<Telefone>listar(int codigo)throws Exception{
        return dao.listarPorCodigo("telefone.listar","codigo", codigo);
    }
}
