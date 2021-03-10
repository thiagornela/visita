package com.example.cadastrodevisita.cadastro.comoNosConheceu.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.ComoNosConheceu;

public class EditaDialog_ComoNosConheceu extends FormularioDialog_ComoNosConheceu {

    private static final String TITULO = "Editando como nos conheceu";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_ComoNosConheceu(Context context,
                                 ComoNosConheceu comoNosConheceu,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, comoNosConheceu);
    }
}
