package com.example.cadastrodevisita.cadastro.categoria.dialog;

import android.content.Context;

import com.example.cadastrodevisita.model.Categoria;
import com.example.cadastrodevisita.model.EmailEquipe;

public class EditaDialog_Categoria extends FormularioDialog_Categoria {

    private static final String TITULO = "Editando categoria";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaDialog_Categoria(Context context,
                                 Categoria categoria,
                                 ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, categoria);
    }
}
