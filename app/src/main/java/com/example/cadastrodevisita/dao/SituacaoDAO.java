package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.Situacao;
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

    public void edita(Situacao situacao) {
        Situacao situacaoEncontrada = buscaPeloId(situacao);
        if (situacaoEncontrada != null) {
            int posicao = situacoes.indexOf(situacaoEncontrada);
            situacoes.set(posicao, situacao);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Situacao> todos() {
        return new ArrayList<>(situacoes);
    }

    public void remove(Situacao situacao) {
        Situacao situacaoDevolvida = buscaPeloId(situacao);
        if (situacaoDevolvida != null) {
            situacoes.remove(situacaoDevolvida);
        }
    }

    public void removeComPosicao(int posicao) {
        situacoes.remove(posicao);
    }

    @Nullable
    private Situacao buscaPeloId(Situacao situacao) {
        for (Situacao a :
                situacoes) {
            if (a.getId() == situacao.getId()) {
                return a;
            }
        }
        return null;
    }
}