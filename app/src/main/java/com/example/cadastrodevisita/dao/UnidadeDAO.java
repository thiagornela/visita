package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.model.Unidade;

import java.util.ArrayList;
import java.util.List;

public class UnidadeDAO {

    private final static ArrayList<Unidade> unidades = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Unidade unidade) {
        unidade.setId(contadorDeIds);
        unidades.add(unidade);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Unidade> todos() {
        return new ArrayList<>(unidades);
    }

    public static void edita(Unidade unidade) {
        Unidade unidadeEncontrada = buscaVisitaPeloId(unidade);
        if (unidadeEncontrada != null) {
            int posicaoDaVisita = unidades.indexOf(unidadeEncontrada);
            unidades.set(posicaoDaVisita, unidade);
        }
    }

    @Nullable
    private static Unidade buscaVisitaPeloId(Unidade unidade) {
        for (Unidade a :
                unidades) {
            if (a.getId() == unidade.getId()) {
                return a;
            }
        }
        return null;
    }

    public void remove(Unidade unidade) {
        Unidade unidadeDevolvida = buscaPeloId(unidade);
        if(unidadeDevolvida != null){
            unidades.remove(unidadeDevolvida);
        }
    }

    @Nullable
    private Unidade buscaPeloId(Unidade unidade) {
        for (Unidade a :
                unidades) {
            if (a.getId() == unidade.getId()) {
                return a;
            }
        }
        return null;
    }
}
