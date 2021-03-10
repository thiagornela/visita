package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDAO {

    private final static ArrayList<Turno> turnos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Turno turno) {
        turno.setId(contadorDeIds);
        turnos.add(turno);
        atualizaIds();
    }

    public void edita(Turno turno) {
        Turno turnoEncontrada = buscaPeloId(turno);
        if (turnoEncontrada != null) {
            int posicao = turnos.indexOf(turnoEncontrada);
            turnos.set(posicao, turno);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Turno> todos() {
        return new ArrayList<>(turnos);
    }

    public void remove(Turno turno) {
        Turno turnoDevolvida = buscaPeloId(turno);
        if (turnoDevolvida != null) {
            turnos.remove(turnoDevolvida);
        }
    }

    public void removeComPosicao(int posicao) {
        turnos.remove(posicao);
    }

    @Nullable
    private Turno buscaPeloId(Turno turno) {
        for (Turno a :
                turnos) {
            if (a.getId() == turno.getId()) {
                return a;
            }
        }
        return null;
    }
}