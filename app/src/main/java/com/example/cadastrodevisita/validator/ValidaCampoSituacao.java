package com.example.cadastrodevisita.validator;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaCampoSituacao implements Validador {

    private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    private final TextInputLayout textInputCampo;
    private boolean temData;

    public ValidaCampoSituacao(TextInputLayout textInputCampo, boolean temData) {
        this.textInputCampo = textInputCampo;
        this.temData = temData;
    }

    private boolean validaCampoObrigatorio() {
        String texto = textInputCampo.getEditText().getText().toString();
        if (texto.isEmpty() && temData) {
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

