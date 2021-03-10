package com.example.cadastrodevisita.model;

import java.io.Serializable;
import java.security.SecureRandom;

public class Turma implements Serializable {

    private String turma;
    private int id = 0;
    private String unidade;

    public Turma() {
        this.turma = turma;
    }

    public Turma(String turma) {
        this.turma = turma;
    }

    public Turma(int id, String turma, String unidade) {
        this.turma = turma;
        this.unidade = unidade;
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return turma;
    }
}
