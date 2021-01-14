package com.example.cadastrodevisita.model;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Visita implements Serializable {

    private int id = 0;
    private Bitmap foto_familia;
    private String nome_crianca;
    private String data_nascimento;
    private String turma;
    private String turno;
    private String nome_responsavel_1;
    private String cpf_responsavel_1;
    private String telefone_fixo_responsavel_1;
    private String telefone_celular_responsavel_1;
    private String email_responsavel_1;
    private String unidade;
    private String secrearia;
    private String tipoAtendimento;
    private String colaborador;
    private String comoNosConheceu;
    private String situacao;
    private String dataDoCadastro;


    public Visita( int id, String nome_crianca, String data_nascimento, String turma, String turno, String nome_responsavel_1, String cpf_responsavel_1, String telefone_fixo_responsavel_1, String telefone_celular_responsavel_1, String email_responsavel_1, String unidade, String secrearia, String tipoAtendimento, String colaborador, String comoNosConheceu, String situacao, String dataDoCadastro) {
        this.foto_familia = foto_familia;
        this.id = id;
        this.nome_crianca = nome_crianca;
        this.data_nascimento = data_nascimento;
        this.turma = turma;
        this.turno = turno;
        this.nome_responsavel_1 = nome_responsavel_1;
        this.cpf_responsavel_1 = cpf_responsavel_1;
        this.telefone_fixo_responsavel_1 = telefone_fixo_responsavel_1;
        this.telefone_celular_responsavel_1 = telefone_celular_responsavel_1;
        this.email_responsavel_1 = email_responsavel_1;
        this.unidade = unidade;
        this.secrearia = secrearia;
        this.tipoAtendimento = tipoAtendimento;
        this.colaborador = colaborador;
        this.comoNosConheceu = comoNosConheceu;
        this.situacao = situacao;
        this.dataDoCadastro = dataDoCadastro;
    }

    public Visita() {
    }

    public String getDataDoCadastro() {
        return dataDoCadastro;
    }

    public void setDataDoCadastro(String dataDoCadastro) {
        this.dataDoCadastro = dataDoCadastro;
    }

    public Bitmap getFoto_familia() {
        return foto_familia;
    }

    public void setFoto_familia(Bitmap foto_familia) {
        this.foto_familia = foto_familia;
    }

    public String getUnidade() {
        return unidade;
    }

    public String getCpf_responsavel_1() {
        return cpf_responsavel_1;
    }

    public String getNome_crianca() {
        return nome_crianca;
    }

    public String getTurma() {
        return turma;
    }

    public String getTurno() {
        return turno;
    }

    public String getNome_responsavel_1() {
        return nome_responsavel_1;
    }

    public String getTelefone_fixo_responsavel_1() {
        return telefone_fixo_responsavel_1;
    }

    public String getTelefone_celular_responsavel_1() {
        return telefone_celular_responsavel_1;
    }

    public String getEmail_responsavel_1() {
        return email_responsavel_1;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public void setNome_crianca(String nome_crianca) {
        this.nome_crianca = nome_crianca;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setNome_responsavel_1(String nome_responsavel_1) {
        this.nome_responsavel_1 = nome_responsavel_1;
    }

    public void setCpf_responsavel_1(String cpf_responsavel_1) {
        this.cpf_responsavel_1 = cpf_responsavel_1;
    }

    public void setTelefone_fixo_responsavel_1(String telefone_fixo_responsavel_1) {
        this.telefone_fixo_responsavel_1 = telefone_fixo_responsavel_1;
    }

    public void setTelefone_celular_responsavel_1(String telefone_celular_responsavel_1) {
        this.telefone_celular_responsavel_1 = telefone_celular_responsavel_1;
    }

    public void setEmail_responsavel_1(String email_responsavel_1) {
        this.email_responsavel_1 = email_responsavel_1;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }

    public String getSecrearia() {
        return secrearia;
    }

    public void setSecrearia(String secrearia) {
        this.secrearia = secrearia;
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getComoNosConheceu() {
        return comoNosConheceu;
    }

    public void setComoNosConheceu(String comoNosConheceu) {
        this.comoNosConheceu = comoNosConheceu;
    }

}
