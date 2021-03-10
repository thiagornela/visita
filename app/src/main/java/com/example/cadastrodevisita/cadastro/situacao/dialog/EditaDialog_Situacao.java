package com.example.cadastrodevisita.cadastro.situacao.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.Situacao;

public class EditaDialog_Situacao extends FormularioDialog_Situacao {

    private static final String TITULO = "Editando situacao";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_Situacao(Context context,
                                 Situacao situacao,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, situacao);
    }
}
