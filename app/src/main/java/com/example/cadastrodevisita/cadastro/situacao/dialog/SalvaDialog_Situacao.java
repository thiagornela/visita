package com.example.cadastrodevisita.cadastro.situacao.dialog;

import android.content.Context;

public class SalvaDialog_Situacao extends FormularioDialog_Situacao {

    private static final String TITULO = "Salvando situacao";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_Situacao(Context context,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
