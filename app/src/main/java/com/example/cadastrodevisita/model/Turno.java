package com.example.cadastrodevisita.model;

public class Turno {

    private String turno;
    private int id = 0;

    public Turno() {
        this.turno = turno;
    }

    public Turno(String turno) {
        this.turno = turno;
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
