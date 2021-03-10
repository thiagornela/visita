package com.example.cadastrodevisita.cadastro.colaborador.dialog;

import android.content.Context;

public class SalvaDialog_Colaborador extends FormularioDialog_Colaborador {

    private static final String TITULO = "Salvando funcionário";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_Colaborador(Context context,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
