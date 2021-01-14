package com.example.cadastrodevisita.dao;

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

}
