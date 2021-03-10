package com.example.cadastrodevisita.validator;

import android.util.Log;
import android.widget.EditText;

import com.example.cadastrodevisita.formatter.FormataTelefoneComDdd;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;

import static com.example.cadastrodevisita.ui.Constantes.ERRO_FORMATACAO_CPF;

public class ConfiguraCampos {

    public List<Validador> validadores = new ArrayList<>();
    public ValidacaCampoObrigatorio validador_situacao;
    public ValidacaCampoObrigatorio validador_nomeIrmao;
    public ValidacaCampoObrigatorio validador_DtNascIrmao;
    public ValidacaCampoObrigatorio validador_TurmaIrmao;
    public ValidacaCampoObrigatorio validador_TurnoIrmao;
    public ValidacaCampoObrigatorio validador_Categoria;
    public ValidacaCampoObrigatorio validador_Motivo;

    public void configuraCampoObrigatorio(TextInputLayout campo) {
        EditText campoNome = campo.getEditText();
        final ValidacaCampoObrigatorio validador = new ValidacaCampoObrigatorio(campo);
        validadores.add(validador);
        campoNome.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador.estaValido();
            }
        });
    }

    public void configuraCampoCPF(TextInputLayout cpf) {
        if(!cpf.equals("")){
            final EditText campoCPF = cpf.getEditText();
            final CPFFormatter formatador = new CPFFormatter();
            final ValidaCpf validador = new ValidaCpf(cpf);
            validadores.add(validador);
            campoCPF.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    removeFormatacao(formatador, campoCPF);
                } else {
                    validador.estaValido();
                }
            });
        }
    }

    public void configuraCampoEmail(TextInputLayout email) {
        if(!email.equals("")){
            EditText campoEmail = email.getEditText();
            final ValidaEmail validador = new ValidaEmail(email);
            validadores.add(validador);
            campoEmail.setOnFocusChangeListener((v, hasFocus) -> {
                if (!hasFocus) {
                    validador.estaValido();
                }
            });
        }
    }

    public void configuraCampoTelefone(TextInputLayout telefone) {
        if(!telefone.equals("")){
            final EditText campoTelefoneComDdd = telefone.getEditText();
            final ValidaTelefoneComDdd validador = new ValidaTelefoneComDdd(telefone);
            final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
            validadores.add(validador);
            campoTelefoneComDdd.setOnFocusChangeListener((v, hasFocus) -> {
                String telefoneComDdd = campoTelefoneComDdd.getText().toString();
                if (hasFocus) {
                    String telefoneComDddSemFormatacao = formatador.removeFormatacao(telefoneComDdd);
                    campoTelefoneComDdd.setText(telefoneComDddSemFormatacao);
                } else
                    validador.estaValido();
            });
        }
    }

    public void removeFormatacao(CPFFormatter formatador, EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemFormato);
        } catch (IllegalArgumentException e) {
            Log.e(ERRO_FORMATACAO_CPF, e.getMessage());
        }
    }

    public void configuraCampoObrigatorioSituacao(TextInputLayout situacao) {
        final EditText campoDataAgendada = situacao.getEditText();
        validador_situacao = new ValidacaCampoObrigatorio(situacao);
        validadores.add(validador_situacao);
        campoDataAgendada.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador_situacao.estaValido();
            }
        });
    }

    public void configuraCampoObrigatorioIrmao(TextInputLayout nome_irmao, TextInputLayout DtNasc_irmao, TextInputLayout turma, TextInputLayout turno) {
        final EditText campo_nomeIrmao = nome_irmao.getEditText();
        final EditText campo_DtNasc_irmao = DtNasc_irmao.getEditText();
        final EditText campo_turma = turma.getEditText();
        final EditText campo_turno = turno.getEditText();
        validaNomeIrmao(nome_irmao, campo_nomeIrmao);
        validaDtNascIrmao(DtNasc_irmao, campo_DtNasc_irmao);
        validaTurmaIrmao(turma, campo_turma);
        validaTurnoIrmao(turno, campo_turno);
    }

    private void validaNomeIrmao(TextInputLayout nome, EditText campo) {
        validador_nomeIrmao = new ValidacaCampoObrigatorio(nome);
        validadores.add(validador_nomeIrmao);
        campo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador_nomeIrmao.estaValido();
            }
        });
    }

    private void validaDtNascIrmao(TextInputLayout nome, EditText campo) {
        validador_DtNascIrmao = new ValidacaCampoObrigatorio(nome);
        validadores.add(validador_DtNascIrmao);
        campo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador_DtNascIrmao.estaValido();
            }
        });
    }

    private void validaTurmaIrmao(TextInputLayout nome, EditText campo) {
        validador_TurmaIrmao = new ValidacaCampoObrigatorio(nome);
        validadores.add(validador_TurmaIrmao);
        campo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador_TurmaIrmao.estaValido();
            }
        });
    }

    private void validaTurnoIrmao(TextInputLayout nome, EditText campo) {
        validador_TurnoIrmao = new ValidacaCampoObrigatorio(nome);
        validadores.add(validador_TurnoIrmao);
        campo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador_TurnoIrmao.estaValido();
            }
        });
    }

    public void configuraCampoObrigatorioCategoria(TextInputLayout categoria) {
        final EditText campoCategoria = categoria.getEditText();
        validador_Categoria = new ValidacaCampoObrigatorio(categoria);
        validadores.add(validador_Categoria);
        campoCategoria.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador_Categoria.estaValido();
            }
        });
    }

    public void removeValidadorSituacao(){
        validadores.remove(validador_situacao);
    }

    public void configuraCampoObrigatorioMotivo(TextInputLayout motivo) {
        final EditText campoMotivo = motivo.getEditText();
        validador_Motivo = new ValidacaCampoObrigatorio(motivo);
        validadores.add(validador_Motivo);
        campoMotivo.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                validador_Motivo.estaValido();
            }
        });
    }

    public void removeValidadorMotivo(){
        validadores.remove(validador_Motivo);
    }

    public void removeValidadorCategoria(){
        validadores.remove(validador_Categoria);
    }

    public void removeValidadorNomeIrmao(){
        validadores.remove(validador_nomeIrmao);
    }
    public void removeValidadorDtNascIrmao(){
        validadores.remove(validador_DtNascIrmao);
    }
    public void removeValidadorTurmaIrmao(){
        validadores.remove(validador_TurmaIrmao);
    }
    public void removeValidadorTurnoIrmao(){
        validadores.remove(validador_TurnoIrmao);
    }
}
