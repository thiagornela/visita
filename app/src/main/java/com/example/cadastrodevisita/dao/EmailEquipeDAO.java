package com.example.cadastrodevisita.dao;

import androidx.annotation.Nullable;

import com.example.cadastrodevisita.model.EmailEquipe;

import java.util.ArrayList;
import java.util.List;

public class EmailEquipeDAO {

    private final static ArrayList<EmailEquipe> emails = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(EmailEquipe email) {
        email.setId(contadorDeIds);
        emails.add(email);
        atualizaIds();
    }

    public void edita(EmailEquipe emailEquipe) {
        EmailEquipe emailEquipeEncontrado = buscaEmailEquipePeloId(emailEquipe);
        if (emailEquipeEncontrado != null) {
            int posicaoDoAluno = emails.indexOf(emailEquipeEncontrado);
            emails.set(posicaoDoAluno, emailEquipe);
        }
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<EmailEquipe> todos() {
        return new ArrayList<>(emails);
    }

    public void remove(EmailEquipe email) {
        EmailEquipe emailDevolvido = buscaEmailEquipePeloId(email);
        if(emailDevolvido != null){
            emails.remove(emailDevolvido);
        }
    }

    public void removeComPosicao(int posicao) {
        emails.remove(posicao);
    }

    @Nullable
    private EmailEquipe buscaEmailEquipePeloId(EmailEquipe emailEquipe) {
        for (EmailEquipe a :
                emails) {
            if (a.getId() == emailEquipe.getId()) {
                return a;
            }
        }
        return null;
    }
}
