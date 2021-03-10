package com.example.cadastrodevisita.cadastro.comoNosConheceu.dialog;

import android.content.Context;

public class SalvaDialog_ComoNosConheceu extends FormularioDialog_ComoNosConheceu {

    private static final String TITULO = "Salvando como nos conheceu";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaDialog_ComoNosConheceu(Context context,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
