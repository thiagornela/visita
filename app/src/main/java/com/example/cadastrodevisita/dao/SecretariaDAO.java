package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.Secretaria;
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

    public void edita(Secretaria secretaria) {
        Secretaria secretariaEncontrada = buscaPeloId(secretaria);
        if (secretariaEncontrada != null) {
            int posicao = secretarias.indexOf(secretariaEncontrada);
            secretarias.set(posicao, secretaria);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Secretaria> todos() {
        return new ArrayList<>(secretarias);
    }

    public void remove(Secretaria secretaria) {
        Secretaria secretariaDevolvida = buscaPeloId(secretaria);
        if(secretariaDevolvida != null){
            secretarias.remove(secretariaDevolvida);
        }
    }

    public void removeComPosicao(int posicao) {
        secretarias.remove(posicao);
    }

    @Nullable
    private Secretaria buscaPeloId(Secretaria secretaria) {
        for (Secretaria a :
                secretarias) {
            if (a.getId() == secretaria.getId()) {
                return a;
            }
        }
        return null;
    }
}