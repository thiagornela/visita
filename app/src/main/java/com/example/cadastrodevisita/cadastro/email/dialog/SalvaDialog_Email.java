package com.example.cadastrodevisita.cadastro.email.dialog;

import android.content.Context;

public class SalvaDialog_Email extends FormularioDialog_Email {

    private static final String TITULO = "Salvando E-mail";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_Email(Context context,
                             ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
