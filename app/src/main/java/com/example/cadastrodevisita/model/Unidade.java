package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class Unidade  implements Serializable {

    private String unidade;
    private int id = 0;

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

    public boolean temIdValido() {
        return id > 0;
    }
}
