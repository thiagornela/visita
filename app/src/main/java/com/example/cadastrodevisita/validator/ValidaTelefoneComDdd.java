package com.example.cadastrodevisita.validator;

import com.example.cadastrodevisita.formatter.FormataTelefoneComDdd;
import com.google.android.material.textfield.TextInputLayout;

public class ValidaTelefoneComDdd implements Validador {

    public static final String DEVE_TER_10_OU_11_DIGITOS = "Telefone deve ter 10 ou 11 dígitos";
    private final TextInputLayout textInputTelefoneComDdd;
    private final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();

    public ValidaTelefoneComDdd(TextInputLayout textInputTelefoneComDdd) {
        this.textInputTelefoneComDdd = textInputTelefoneComDdd;
    }

    private boolean validaEntreDezOuOndeDigitos(String telefoneComDdd) {
        int digitos = telefoneComDdd.length();
        if (digitos == 10 || digitos == 11) {
            return true;
        }
        if (digitos == 0) {
            return true;
        } else {
            textInputTelefoneComDdd.setError(DEVE_TER_10_OU_11_DIGITOS);
            return false;
        }
    }


    @Override
    public boolean estaValido() {
        String telefoneComDdd = textInputTelefoneComDdd.getEditText().getText().toString();
        String telefoneComDddSemFormatacao = formatador.removeFormatacao(telefoneComDdd);
        if (!validaEntreDezOuOndeDigitos(telefoneComDddSemFormatacao)) return false;
        adicionaFormatacao(telefoneComDddSemFormatacao);
        removeErro();
        return true;
    }

    private void adicionaFormatacao(String telefoneComDdd) {
        String telefoneComDddFormatado = formatador.formata(telefoneComDdd);
        textInputTelefoneComDdd.getEditText().setText(telefoneComDddFormatado);
    }

    private void removeErro() {
        textInputTelefoneComDdd.setError(null);
        textInputTelefoneComDdd.setErrorEnabled(false);
    }
}