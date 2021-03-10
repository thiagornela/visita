package com.example.cadastrodevisita.cadastro.categoria.dialog;

import android.content.Context;

public class SalvaDialog_Categoria extends FormularioDialog_Categoria {

    private static final String TITULO = "Salvando categoria";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_Categoria(Context context,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
