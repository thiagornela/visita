package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class Turno implements Serializable {

    private String turno;
    private int id = 0;
    private String unidade;

    public Turno() {
        this.turno = turno;
    }

    public Turno(String turno) {
        this.turno = turno;
    }

    public Turno(int id, String turno, String unidade) {
        this.turno = turno;
        this.unidade = unidade;
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return turno;
    }
}
