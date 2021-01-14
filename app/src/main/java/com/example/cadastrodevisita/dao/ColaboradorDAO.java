package com.example.cadastrodevisita.dao;

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

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Colaborador> todos() {
        return new ArrayList<>(colaboradores);
    }
}
