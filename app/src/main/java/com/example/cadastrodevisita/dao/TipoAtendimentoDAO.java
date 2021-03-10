package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.TipoAtendimento;

import java.util.ArrayList;
import java.util.List;

public class TipoAtendimentoDAO {

    private final static ArrayList<TipoAtendimento> tipoAtendimentos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(TipoAtendimento tipoAtendimento) {
        tipoAtendimento.setId(contadorDeIds);
        tipoAtendimentos.add(tipoAtendimento);
        atualizaIds();
    }

    public void edita(TipoAtendimento tipoAtendimento) {
        TipoAtendimento tipoAtendimentoEncontrada = buscaPeloId(tipoAtendimento);
        if (tipoAtendimentoEncontrada != null) {
            int posicao = tipoAtendimentos.indexOf(tipoAtendimentoEncontrada);
            tipoAtendimentos.set(posicao, tipoAtendimento);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<TipoAtendimento> todos() {
        return new ArrayList<>(tipoAtendimentos);
    }

    public void remove(TipoAtendimento tipoAtendimento) {
        TipoAtendimento tipoAtendimentoDevolvida = buscaPeloId(tipoAtendimento);
        if (tipoAtendimentoDevolvida != null) {
            tipoAtendimentos.remove(tipoAtendimentoDevolvida);
        }
    }

    public void removeComPosicao(int posicao) {
        tipoAtendimentos.remove(posicao);
    }

    @Nullable
    private TipoAtendimento buscaPeloId(TipoAtendimento tipoAtendimento) {
        for (TipoAtendimento a :
                tipoAtendimentos) {
            if (a.getId() == tipoAtendimento.getId()) {
                return a;
            }
        }
        return null;
    }
}