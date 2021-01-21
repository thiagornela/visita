package com.example.cadastrodevisita.validator;

import com.google.android.material.textfield.TextInputLayout;

public class ValidacaCampoObrigatorio implements Validador {

    private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
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
        if (!validaCampoObrigatorio()) return false;
        removeErro();
        return true;
    }

    private void removeErro() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }
}

