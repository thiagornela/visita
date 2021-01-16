package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class Situacao  implements Serializable {

    private String situacao;
    private int id = 0;

    public Situacao() {
        this.situacao = situacao;
    }

    public Situacao(String situacao) {
        this.situacao = situacao;
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
