package com.example.cadastrodevisita.dao;

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

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Turno> todos() {
        return new ArrayList<>(turnos);
    }

}
