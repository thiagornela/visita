package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

public class MensagemDAO {

    private final static ArrayList<Mensagem> mensagens = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Mensagem mensagem) {
        mensagem.setId(contadorDeIds);
        mensagens.add(mensagem);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Mensagem> todos() {
        return new ArrayList<>(mensagens);
    }

    public static void edita(Mensagem mensagem) {
        Mensagem mensagemEncontrada = buscaVisitaPeloId(mensagem);
        if (mensagemEncontrada != null) {
            int posicaoDaVisita = mensagens.indexOf(mensagemEncontrada);
            mensagens.set(posicaoDaVisita, mensagem);
        }
    }

    @Nullable
    private static Mensagem buscaVisitaPeloId(Mensagem mensagem) {
        for (Mensagem a :
                mensagens) {
            if (a.getId() == mensagem.getId()) {
                return a;
            }
        }
        return null;
    }

    public void remove(Mensagem mensagem) {
        Mensagem mensagemDevolvida = buscaPeloId(mensagem);
        if(mensagemDevolvida != null){
            mensagens.remove(mensagemDevolvida);
        }
    }

    @Nullable
    private Mensagem buscaPeloId(Mensagem mensagem) {
        for (Mensagem a :
                mensagens) {
            if (a.getId() == mensagem.getId()) {
                return a;
            }
        }
        return null;
    }
}
