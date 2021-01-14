package com.example.cadastrodevisita.dao;

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

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<TipoAtendimento> todos() {
        return new ArrayList<>(tipoAtendimentos);
    }
}
