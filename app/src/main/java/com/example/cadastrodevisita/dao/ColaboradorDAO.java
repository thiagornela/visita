package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.Categoria;
import com.example.cadastrodevisita.model.Colaborador;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO {

    private final static ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Colaborador colaborador) {
        colaborador.setId(contadorDeIds);
        colaboradores.add(colaborador);
        atualizaIds();
    }

    public void edita(Colaborador colaborador) {
        Colaborador colaboradorEncontrado = buscaPeloId(colaborador);
        if (colaboradorEncontrado != null) {
            int posicao = colaboradores.indexOf(colaboradorEncontrado);
            colaboradores.set(posicao, colaborador);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Colaborador> todos() {
        return new ArrayList<>(colaboradores);
    }

    public void remove(Colaborador colaborador) {
        Colaborador colaboradorDevolvido = buscaPeloId(colaborador);
        if(colaboradorDevolvido != null){
            colaboradores.remove(colaboradorDevolvido);
        }
    }

    public void removeComPosicao(int posicao) {
        colaboradores.remove(posicao);
    }

    @Nullable
    private Colaborador buscaPeloId(Colaborador colaborador) {
        for (Colaborador a :
                colaboradores) {
            if (a.getId() == colaborador.getId()) {
                return a;
            }
        }
        return null;
    }
}
