package com.example.cadastrodevisita.cadastro.secretaria.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.Secretaria;

public class EditaDialog_Secretaria extends FormularioDialog_Secretaria {

    private static final String TITULO = "Editando secretária";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_Secretaria(Context context,
                                 Secretaria secretaria,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, secretaria);
    }
}
