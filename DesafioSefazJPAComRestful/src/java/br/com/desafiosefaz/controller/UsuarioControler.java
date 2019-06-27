package br.com.desafiosefaz.controller;

import br.com.desafiosefaz.dao.UsuarioDao;
import br.com.desafiosefaz.domain.Usuario;
import java.util.List;

public class UsuarioControler {

    private final UsuarioDao dao = new UsuarioDao();

    public int salvar(Usuario usuario) throws Exception {
        return dao.salvar(usuario);
    }
    
     public void alterar(Usuario usuario) throws Exception{
        dao.alterar(usuario);
    }
     
     public void excluir(Usuario usuario)throws Exception{
         dao.excluir(usuario);
     }
    
    public Usuario autenticar(String email, String senha)throws Exception{
       return dao.Autenticar(email, senha);
    }  
    
    public List<Usuario>listar()throws Exception{
        return dao.listar("Usuario.listar");
    }
}
