package com.example.cadastrodevisita.cadastro.tipoAtendimento.dialog;

import android.content.Context;

public class SalvaDialog_TipoAtendimento extends FormularioDialog_TipoAtendimento {

    private static final String TITULO = "Salvando atendimento";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_TipoAtendimento(Context context,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
