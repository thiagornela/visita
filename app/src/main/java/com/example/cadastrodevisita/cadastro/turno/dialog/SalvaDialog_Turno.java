package com.example.cadastrodevisita.cadastro.turno.dialog;

import android.content.Context;

public class SalvaDialog_Turno extends FormularioDialog_Turno {

    private static final String TITULO = "Salvando turno";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_Turno(Context context,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
