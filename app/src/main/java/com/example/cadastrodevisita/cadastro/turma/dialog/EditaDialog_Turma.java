package com.example.cadastrodevisita.cadastro.turma.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.Turma;

public class EditaDialog_Turma extends FormularioDialog_Turma {

    private static final String TITULO = "Editando turma";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_Turma(Context context,
                                 Turma turma,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, turma);
    }
}
