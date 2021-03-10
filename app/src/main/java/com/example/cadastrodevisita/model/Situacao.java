package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class Situacao  implements Serializable {

    private String situacao;
    private int id = 0;
    private String unidade;

    public Situacao() {
        this.situacao = situacao;
    }

    public Situacao(String situacao) {
        this.situacao = situacao;
    }

    public Situacao(int id, String situacao, String unidade) {
        this.situacao = situacao;
        this.unidade = unidade;
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return situacao;
    }
}
