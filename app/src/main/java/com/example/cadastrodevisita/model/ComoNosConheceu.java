package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class ComoNosConheceu  implements Serializable {

    private String comoNosConheceu;
    private int id = 0;

    public ComoNosConheceu() {
        this.comoNosConheceu = comoNosConheceu;
    }

    public ComoNosConheceu(String comoNosConheceu) {
        this.comoNosConheceu = comoNosConheceu;
    }

    public String getComoNosConheceu() {
        return comoNosConheceu;
    }

    public void setComoNosConheceu(String comoNosConheceu) {
        this.comoNosConheceu = comoNosConheceu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return comoNosConheceu;
    }
}
