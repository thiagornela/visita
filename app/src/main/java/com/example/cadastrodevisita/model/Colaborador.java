package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class Colaborador  implements Serializable {

    private String colaborador;
    private int id = 0;

    public Colaborador() {
        this.colaborador = colaborador;
    }

    public Colaborador(String colaborador) {
        this.colaborador = colaborador;
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
