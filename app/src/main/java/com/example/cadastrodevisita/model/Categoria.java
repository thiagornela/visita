package com.example.cadastrodevisita.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Categoria implements Serializable {

    private String categoria;
    private int id = 0;
    private String unidade;

    public Categoria() {
        this.categoria = categoria;
    }

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria(int id, String categoria, String unidade) {
        this.categoria = categoria;
        this.unidade = unidade;
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
