package com.example.cadastrodevisita.dao;

import com.example.cadastrodevisita.model.Visita;

import java.util.ArrayList;
import java.util.List;

public class VisitaDAO {

    private final static List<Visita> visitas = new ArrayList<>();
    private static int contadorDeIds = 1;


    public void salva(Visita visita) {
        visita.setId(contadorDeIds);
        visitas.add(visita);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Visita> todos() {
        return new ArrayList<>(visitas);
    }
}