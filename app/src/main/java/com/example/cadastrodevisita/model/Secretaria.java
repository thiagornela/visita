package com.example.cadastrodevisita.model;

public class Secretaria {

    private String secretaria;
    private int id = 0;

    public Secretaria() {
        this.secretaria = secretaria;
    }

    public Secretaria(String secretaria) {
        this.secretaria = secretaria;
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
