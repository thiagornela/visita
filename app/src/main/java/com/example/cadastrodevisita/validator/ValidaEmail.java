package com.example.cadastrodevisita.validator;

import com.google.android.material.textfield.TextInputLayout;

public class ValidaEmail implements Validador {

    private final TextInputLayout textInputEmail;

    public ValidaEmail(TextInputLayout textInputEmail) {
        this.textInputEmail = textInputEmail;
    }

    private boolean validaPadrao(String email) {
        if (email.matches(".+@.+\\..+") || email.equals("")) {
            return true;
        }
        textInputEmail.setError("E-mail inválido");
        return false;
    }

    @Override
    public boolean estaValido() {
        String email = textInputEmail.getEditText().getText().toString();
        if (!validaPadrao(email)) return false;
        removeErro();
        return true;
    }

    private void removeErro() {
        textInputEmail.setError(null);
        textInputEmail.setErrorEnabled(false);
    }

}
