package com.example.cadastrodevisita.model;

public class Unidade {

    private String unidade;
    private int id = 0;

    public Unidade() {
        this.unidade = unidade;
    }

    public Unidade(String unidade) {
        this.unidade = unidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return unidade;
    }
}
