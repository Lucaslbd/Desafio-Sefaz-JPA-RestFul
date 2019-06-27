package br.com.desafiosefaz.dao;

import br.com.desafiosefaz.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class DaoGenerico<T> {

    public int salvar(T entidade) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        int cd = (int) sessao.save(entidade);
        sessao.getTransaction().commit();
        sessao.close();
        return cd;
    }

    public void alterar(T entidade) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        sessao.update(entidade);
        sessao.getTransaction().commit();
        sessao.close();
    }

    public void excluir(T entidade) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        sessao.beginTransaction();
        sessao.delete(entidade);
        sessao.getTransaction().commit();
        sessao.close();
    }

    public List<T> listar(String nameQuerie) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta = sessao.getNamedQuery(nameQuerie);
        List<T> lista = consulta.list();
        sessao.close();
        return lista;
    }    

    public List<T> listarPorCodigo(String nameQuerie, String campo, int codigo) throws Exception {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Query consulta = sessao.getNamedQuery(nameQuerie);
        consulta.setInteger(campo, codigo);
        List<T> lista = consulta.list();
        sessao.close();
        return lista;
    }
}
