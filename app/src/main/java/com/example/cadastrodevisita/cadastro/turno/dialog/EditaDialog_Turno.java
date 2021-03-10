package com.example.cadastrodevisita.cadastro.turno.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.Turno;

public class EditaDialog_Turno extends FormularioDialog_Turno {

    private static final String TITULO = "Editando turno";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_Turno(Context context,
                                 Turno turno,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, turno);
    }
}
