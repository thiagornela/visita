package com.example.cadastrodevisita.dao;

import com.example.cadastrodevisita.model.ComoNosConheceu;

import java.util.ArrayList;
import java.util.List;

public class ComoNosConheceuDAO {

    private final static ArrayList<ComoNosConheceu> comoNosConheceu_List = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(ComoNosConheceu comoNosConheceu) {
        comoNosConheceu.setId(contadorDeIds);
        comoNosConheceu_List.add(comoNosConheceu);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<ComoNosConheceu> todos() {
        return new ArrayList<>(comoNosConheceu_List);
    }
}
