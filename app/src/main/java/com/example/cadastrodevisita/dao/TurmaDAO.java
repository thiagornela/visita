package com.example.cadastrodevisita.dao;

import com.example.cadastrodevisita.model.Turma;

import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    private final static ArrayList<Turma> turmas = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Turma turma) {
        turma.setId(contadorDeIds);
        turmas.add(turma);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Turma> todos() {
        return new ArrayList<>(turmas);
    }
}
