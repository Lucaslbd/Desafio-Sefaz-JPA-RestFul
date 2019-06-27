package br.com.desafiosefaz.bean;

import br.com.desafiosefaz.controller.TelefoneController;
import br.com.desafiosefaz.controller.UsuarioControler;
import br.com.desafiosefaz.domain.Telefone;
import br.com.desafiosefaz.domain.Usuario;
import br.com.desafiosefaz.util.JSFUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "MBUsuario")
@ViewScoped
public class UsuarioBean {
    
    private Usuario usuario;
    private int acao;
    @ManagedProperty(value = "#{MBAutenticacao}")
    private MBAutenticacao MBAutenticacao;
    private List<Usuario> lista;
    private List<Usuario> listaFiltrada;
    private List<Telefone> listaTel = new ArrayList<>();
    private Telefone telefone = new Telefone();
    
    public List<Usuario> getLista() {
        return lista;
    }
    
    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }
    
    public List<Usuario> getListaFiltrada() {
        return listaFiltrada;
    }
    
    public void setListaFiltrada(List<Usuario> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public List<Telefone> getListaTel() {
        return listaTel;
    }
    
    public void setListaTel(List<Telefone> listaTel) {
        this.listaTel = listaTel;
    }
    
    public Telefone getTelefone() {
        return telefone;
    }
    
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
    
    public void setListaTel(ArrayList<Telefone> listaTel) {
        this.listaTel = listaTel;
    }
    
    public void setLista(ArrayList<Usuario> lista) {
        this.lista = lista;
    }
    
    public void setListaFiltrada(ArrayList<Usuario> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }
    
    public MBAutenticacao getMBAutenticacao() {
        return MBAutenticacao;
    }
    
    public void setMBAutenticacao(MBAutenticacao MBAutenticacao) {
        this.MBAutenticacao = MBAutenticacao;
    }
    
    public int getAcao() {
        return acao;
    }
    
    public void setAcao(int acao) {
        this.acao = acao;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //Inicio da implementação------------------------------------------------------------------------------------
    private final UsuarioControler ctl = new UsuarioControler();
    private final TelefoneController ctlTel = new TelefoneController();
    
    public void salvar() {
        try {
            if (listaTel.size() > 0) {
                int codigo = ctl.salvar(usuario);
                usuario.setCodigo(codigo);
                for (Telefone t : listaTel) {
                    t.setUsuario(usuario);
                    ctlTel.salvar(t);
                }
                JSFUtil.mensagemSucesso("Usuário registrado com sucesso");
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } else {
                JSFUtil.mensagemAviso("Adicione telefones a lista");
            }
        } catch (Exception ex) {
            JSFUtil.mensagemErro("Erro ao registrar usuário" + ex);
        }
    }
    
    public void alterar() {
        try {
            if (listaTel.size() > 0) {
                ctl.alterar(usuario);
                for (Telefone t : listaTel) {
                    if (t.getCodigo() < 1) {
                        t.setUsuario(usuario);
                        ctlTel.salvar(t);
                        JSFUtil.mensagemSucesso(t.toString());
                    }
                }
                JSFUtil.mensagemSucesso("Usuário alterado com sucesso");
                FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
            } else {
                JSFUtil.mensagemAviso("Adicione telefones a lista");
            }
        } catch (Exception ex) {
            JSFUtil.mensagemErro("Erro ao alterar usuário" + ex);
        }
    }
    
    public void excluir() {
        try {
            ctl.excluir(MBAutenticacao.getUsuario());
            MBAutenticacao.setEmail("");
            MBAutenticacao.setSenha("");
            MBAutenticacao.setUsuario(null);
            JSFUtil.mensagemSucesso("Conta removida com sucesso");
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (Exception ex) {
            JSFUtil.mensagemErro("Erro ao listar conta" + ex);
        }
    }
    
    @PostConstruct
    public void listar() {
        try {
            lista = ctl.listar();
            for (int i = 0; i < lista.size(); i++) {
                lista.get(i).setTelefones(ctlTel.listar(lista.get(i).getCodigo()));
            }
        } catch (Exception ex) {
            JSFUtil.mensagemErro("Erro ao listar conta" + ex);
        }
    }
    
    public void carregarCadastro() {
        if (acao == 1) {
            usuario = new Usuario();
        } else {
            usuario = MBAutenticacao.getUsuario();
            listaTel = usuario.getTelefones();
        }
    }
    
    public void removerTel(int index, Telefone telefone) {
        listaTel.remove(index);
        if (telefone.getCodigo() > 0) {
            try {
                ctlTel.excluir(telefone);
            } catch (Exception ex) {
                JSFUtil.mensagemErro("Erro ao remover telefone" + ex);
            }
        }
    }
    
    public void addTel() {
        if (telefone.getNumero().equals("") || telefone.getNumero().length() < 8 || telefone.getDdd() == 0) {
            JSFUtil.mensagemAviso("Preencha o telefone");
        } else {
            if (telefone.getNumero().length() == 8) {
                telefone.setTipo("Residencial");
            } else {
                telefone.setTipo("Celular");
            }
            listaTel.add(telefone);
            telefone = new Telefone();
        }
    }
}
