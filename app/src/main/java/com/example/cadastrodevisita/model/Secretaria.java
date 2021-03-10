package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class Secretaria  implements Serializable {

    private String secretaria;
    private int id = 0;
    private String unidade;

    public Secretaria() {
        this.secretaria = secretaria;
    }

    public Secretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public Secretaria(int id, String secretaria, String unidade) {
        this.secretaria = secretaria;
        this.unidade = unidade;
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return secretaria;
    }
}
