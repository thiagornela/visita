package com.example.cadastrodevisita.model;

import java.io.Serializable;

public class ComoNosConheceu  implements Serializable {

    private String comoNosConheceu;
    private int id = 0;
    private String unidade;

    public ComoNosConheceu() {
        this.comoNosConheceu = comoNosConheceu;
    }

    public ComoNosConheceu(String comoNosConheceu) {
        this.comoNosConheceu = comoNosConheceu;
    }

    public ComoNosConheceu(int id, String comoNosConheceu, String unidade) {
        this.comoNosConheceu = comoNosConheceu;
        this.unidade = unidade;
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
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
