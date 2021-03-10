package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class Colaborador  implements Serializable {

    private String colaborador;
    private String unidade;
    private int id = 0;

    public Colaborador() {
        this.colaborador = colaborador;
    }

    public Colaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public Colaborador(int id, String colaborador, String unidade) {
        this.colaborador = colaborador;
        this.unidade = unidade;
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return colaborador;
    }
}
