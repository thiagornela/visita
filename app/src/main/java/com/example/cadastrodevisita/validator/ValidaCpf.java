package com.example.cadastrodevisita.validator;

import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

import static com.example.cadastrodevisita.ui.Constantes.CPF_INVALIDO;
import static com.example.cadastrodevisita.ui.Constantes.DEVE_TER_11_DIGITOS;
import static com.example.cadastrodevisita.ui.Constantes.ERRO_FORMATACAO_CPF;

public class ValidaCpf implements Validador {
    private TextInputLayout textInputCPF;
    CPFFormatter formatador = new CPFFormatter();

    public ValidaCpf(TextInputLayout textInputCpf) {
        this.textInputCPF = textInputCpf;
    }


    private boolean validaCalculoCpf(String cpf) {
        if (cpf.length() != 0) {
            try {
                CPFValidator cpfValidator = new CPFValidator();
                cpfValidator.assertValid(cpf);
            } catch (InvalidStateException e) {
                textInputCPF.setError(CPF_INVALIDO);
                return false;
            }
        }
        return true;
    }

    private String getCpf() {
        return textInputCPF.getEditText().getText().toString();
    }

    private boolean validaCampoComOnzeDigitos(String cpf) {
        if (cpf.length() == 0)
            return true;
        else if (cpf.length() != 11) {
            textInputCPF.setError(DEVE_TER_11_DIGITOS);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean estaValido() {
        String cpf = getCpf();
        String cpfSemFormato = cpf;
        try {
            cpfSemFormato = formatador.unformat(cpf);
        } catch (IllegalArgumentException e) {
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
        if (!validaCampoComOnzeDigitos(cpfSemFormato)) return false;
        if (!validaCalculoCpf(cpfSemFormato)) return false;
        if (cpf.length() != 0)
            adicionaFormatacao(cpfSemFormato);
        removeErro();
        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        textInputCPF.getEditText().setText(cpfFormatado);
    }

    private void removeErro() {
        textInputCPF.setError(null);
        textInputCPF.setErrorEnabled(false);
    }
}
