package com.example.cadastrodevisita.cadastro.colaborador.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.Categoria;
import com.example.cadastrodevisita.model.Colaborador;

public class EditaDialog_Colaborador extends FormularioDialog_Colaborador {

    private static final String TITULO = "Editando funcionário";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_Colaborador(Context context,
                                   Colaborador colaborador,
                                   ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, colaborador);
    }
}
