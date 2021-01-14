package com.example.cadastrodevisita.dao;

import com.example.cadastrodevisita.model.Situacao;

import java.util.ArrayList;
import java.util.List;

public class SituacaoDAO {

    private final static ArrayList<Situacao> situacoes = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Situacao situacao) {
        situacao.setId(contadorDeIds);
        situacoes.add(situacao);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Situacao> todos() {
        return new ArrayList<>(situacoes);
    }
}
