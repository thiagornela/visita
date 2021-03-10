package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.ComoNosConheceu;
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
    
    public void edita(ComoNosConheceu comoNosConheceu) {
        ComoNosConheceu comoNosConheceuEncontrada = buscaPeloId(comoNosConheceu);
        if (comoNosConheceuEncontrada != null) {
            int posicao = comoNosConheceu_List.indexOf(comoNosConheceuEncontrada);
            comoNosConheceu_List.set(posicao, comoNosConheceu);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<ComoNosConheceu> todos() {
        return new ArrayList<>(comoNosConheceu_List);
    }

    public void remove(ComoNosConheceu comoNosConheceu) {
        ComoNosConheceu comoNosConheceuDevolvida = buscaPeloId(comoNosConheceu);
        if(comoNosConheceuDevolvida != null){
            comoNosConheceu_List.remove(comoNosConheceuDevolvida);
        }
    }

    public void removeComPosicao(int posicao) {
        comoNosConheceu_List.remove(posicao);
    }

    @Nullable
    private ComoNosConheceu buscaPeloId(ComoNosConheceu comoNosConheceu) {
        for (ComoNosConheceu a :
                comoNosConheceu_List) {
            if (a.getId() == comoNosConheceu.getId()) {
                return a;
            }
        }
        return null;
    }
}
