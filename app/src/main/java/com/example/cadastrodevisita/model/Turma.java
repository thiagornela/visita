package com.example.cadastrodevisita.model;

import java.io.Serializable;
import java.security.SecureRandom;

public class Turma implements Serializable {

    private String turma;
    private int id = 0;

    public Turma() {
        this.turma = turma;
    }

    public Turma(String turma) {
        this.turma = turma;
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
