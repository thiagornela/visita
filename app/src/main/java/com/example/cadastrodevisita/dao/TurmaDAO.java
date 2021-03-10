package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.Turma;
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

    public void edita(Turma turma) {
        Turma turmaEncontrada = buscaPeloId(turma);
        if (turmaEncontrada != null) {
            int posicao = turmas.indexOf(turmaEncontrada);
            turmas.set(posicao, turma);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Turma> todos() {
        return new ArrayList<>(turmas);
    }

    public void remove(Turma turma) {
        Turma turmaDevolvida = buscaPeloId(turma);
        if (turmaDevolvida != null) {
            turmas.remove(turmaDevolvida);
        }
    }

    public void removeComPosicao(int posicao) {
        turmas.remove(posicao);
    }

    @Nullable
    private Turma buscaPeloId(Turma turma) {
        for (Turma a :
                turmas) {
            if (a.getId() == turma.getId()) {
                return a;
            }
        }
        return null;
    }
}