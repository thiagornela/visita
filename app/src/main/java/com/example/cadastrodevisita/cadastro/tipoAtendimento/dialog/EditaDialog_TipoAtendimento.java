package com.example.cadastrodevisita.cadastro.tipoAtendimento.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.TipoAtendimento;

public class EditaDialog_TipoAtendimento extends FormularioDialog_TipoAtendimento {

    private static final String TITULO = "Editando atendimento";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_TipoAtendimento(Context context,
                                 TipoAtendimento tipoAtendimento,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, tipoAtendimento);
    }
}
