package br.com.desafiosefaz.bean;

import br.com.desafiosefaz.controller.TelefoneController;
import br.com.desafiosefaz.controller.UsuarioControler;
import br.com.desafiosefaz.domain.Usuario;
import br.com.desafiosefaz.util.JSFUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "MBAutenticacao")
@SessionScoped
public class MBAutenticacao {

    private Usuario usuario;
    private String email;
    private String senha;
    private String saudacao;

    public String getSaudacao() {
        return saudacao;
    }

    public void setSaudacao(String saudacao) {
        this.saudacao = saudacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /*------------------------------------------------------------------------------------*/
    private final UsuarioControler ctl = new UsuarioControler();
    private final TelefoneController ctlTel = new TelefoneController();

    public String entrar() {
        try {
            usuario = ctl.autenticar(email, senha);
            if (usuario == null) {
                JSFUtil.mensagemAviso("E-mail e/ou senha invalidos");
                return null;
            } else {
                usuario.setTelefones(ctlTel.listar(usuario.getCodigo()));
                JSFUtil.mensagemSucesso("Olá " + usuario.getNome() + " seu acesso foi autenticado com sucesso");
                return "Principal.xhtml?faces-redirect=true";
            }
        } catch (Exception ex) {
            JSFUtil.mensagemErro("Erro ao autenticar " + ex);
        }
        return null;
    }

    public String sair() {
        usuario = null;
        email = "";
        senha = "";
        return "index.xhtml?faces-redirect=true";
    }
}
