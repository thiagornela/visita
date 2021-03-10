package com.example.cadastrodevisita.cadastro.email.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.EmailEquipe;

public class EditaDialog_Email extends FormularioDialog_Email {

    private static final String TITULO = "Editando E-mail";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_Email(Context context,
                             EmailEquipe emailEquipe,
                             ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, emailEquipe);
    }
}
