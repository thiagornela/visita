package com.example.cadastrodevisita.validator;

import com.google.android.material.textfield.TextInputLayout;

import static com.example.cadastrodevisita.ui.Constantes.CAMPO_OBRIGATORIO;

public class ValidacaCampoObrigatorio implements Validador {

    private final TextInputLayout textInputCampo;

    public ValidacaCampoObrigatorio(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
    }

    private boolean validaCampoObrigatorio() {
        String texto = textInputCampo.getEditText().getText().toString();
        if (texto.isEmpty()) {
            textInputCampo.setError(CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido() {
        if (!validaCampoObrigatorio())
            return false;
        removeErro();
        return true;
    }

    private void removeErro() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }
}

