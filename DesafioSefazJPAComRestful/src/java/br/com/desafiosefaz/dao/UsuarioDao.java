package br.com.desafiosefaz.dao;

import br.com.desafiosefaz.domain.Usuario;
import br.com.desafiosefaz.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDao extends DaoGenerico<Usuario> {

    public Usuario Autenticar(String email, String senha)throws Exception{
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta = sessao.getNamedQuery("Usuario.autenticar");
        consulta.setString("email", email);
        consulta.setString("senha", senha);
        Usuario usuario = (Usuario) consulta.uniqueResult();
        sessao.close();
        return usuario;
    }

}
