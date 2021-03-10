package com.example.cadastrodevisita.cadastro.secretaria.dialog;

import android.content.Context;

public class SalvaDialog_Secretaria extends FormularioDialog_Secretaria {

    private static final String TITULO = "Salvando secretária";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_Secretaria(Context context,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
