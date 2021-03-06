package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class TipoAtendimento implements Serializable {

    private String atendimento;
    private int id = 0;
    private String unidade;

    public TipoAtendimento() {
        this.atendimento = atendimento;
    }

    public TipoAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

    public TipoAtendimento(int id, String atendimento, String unidade) {
        this.atendimento = atendimento;
        this.unidade = unidade;
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return atendimento;
    }
}
