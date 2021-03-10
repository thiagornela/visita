package com.example.cadastrodevisita.cadastro.turma.dialog;

import android.content.Context;

public class SalvaDialog_Turma extends FormularioDialog_Turma {

    private static final String TITULO = "Salvando turma";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_Turma(Context context,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
