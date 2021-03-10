package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class ServidorEmail implements Serializable {

    private String usuario;
    private String host;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String porta;
    private String senha;
    private String unidade;
    private int id = 0;

    public ServidorEmail(String usuario, String host, String porta, String senha) {
        this.usuario = usuario;
        this.host = host;
        this.porta = porta;
        this.senha = senha;
    }
    public ServidorEmail(){

    };

    public boolean temIdValido() {
        return id > 0;
    }


}
