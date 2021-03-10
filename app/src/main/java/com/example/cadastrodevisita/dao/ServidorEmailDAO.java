package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.ServidorEmail;

import java.util.ArrayList;
import java.util.List;

public class ServidorEmailDAO {

    private final static ArrayList<ServidorEmail> servidores = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(ServidorEmail servidorEmail) {
        servidorEmail.setId(contadorDeIds);
        servidores.add(0, servidorEmail);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<ServidorEmail> todos() {
        return new ArrayList<>(servidores);
    }

    public static void edita(ServidorEmail servidorEmail) {
        ServidorEmail servidorEncontrado = buscaPeloId(servidorEmail);
        if (servidorEncontrado != null) {
            int posicao = servidores.indexOf(servidorEncontrado);
            servidores.set(posicao, servidorEmail);
        }
    }

    @Nullable
    private static ServidorEmail buscaPeloId(ServidorEmail servidorEmail) {
        for (ServidorEmail a :
                servidores) {
            if (a.getId() == servidorEmail.getId()) {
                return a;
            }
        }
        return null;
    }

    public void remove(ServidorEmail servidorEmail) {
        ServidorEmail servidorEmailDevolvida = buscaPeloId(servidorEmail);
        if(servidorEmailDevolvida != null){
            servidores.remove(servidorEmailDevolvida);
        }
    }
}
