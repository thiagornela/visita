package com.example.cadastrodevisita.dao;

import com.example.cadastrodevisita.model.Secretaria;

import java.util.ArrayList;
import java.util.List;

public class SecretariaDAO {

    private final static ArrayList<Secretaria> secretarias = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Secretaria secretaria) {
        secretaria.setId(contadorDeIds);
        secretarias.add(secretaria);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Secretaria> todos() {
        return new ArrayList<>(secretarias);
    }
}
