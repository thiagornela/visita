package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

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

    public static void edita(Visita visita) {
        Visita visitaEncontrada = buscaVisitaPeloId(visita);
        if (visitaEncontrada != null) {
            int posicaoDaVisita = visitas.indexOf(visitaEncontrada);
            visitas.set(posicaoDaVisita, visita);
        }
    }

    @Nullable
    public static Visita buscaVisitaPeloId(Visita visita) {
        for (Visita a :
                visitas) {
            if (a.getId() == visita.getId()) {
                return a;
            }
        }
        return null;
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Visita> todos() {
        return new ArrayList<>(visitas);
    }
}