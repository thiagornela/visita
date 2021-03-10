package com.example.cadastrodevisita.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Visita implements Parcelable {

    private int id = 0;
    private Bitmap foto_familia;
    private String nome_crianca;
    private String dataNascimento_crianca;
    private String turma_crianca;
    private String turno_crianca;
    private boolean temIrmao;
    private String nome_irmao;
    private String dataNascimento_irmao;
    private String turma_irmao;
    private String turno_irmao;
    private String nome_responsavel_1;
    private String cpf_responsavel_1;
    private String telefone_fixo_responsavel_1;
    private String telefone_celular_responsavel_1;
    private String email_responsavel_1;
    private String nome_responsavel_2;
    private String cpf_responsavel_2;
    private String telefone_fixo_responsavel_2;
    private String telefone_celular_responsavel_2;
    private String email_responsavel_2;
    private String unidade;
    private String secretaria;
    private String tipoAtendimento;
    private String colaborador;
    private String comoNosConheceu;
    private String situacao;
    private String categoria;
    private String dataAgendada;
    private String observacao;
    private String dataCadastro;
    //private Date dataCadastro;
    private String motivo_outraEscola;
    private String nome_outraEscola;

    public Visita() {
    }

    protected Visita(Parcel in) {
        id = in.readInt();
        foto_familia = in.readParcelable(Bitmap.class.getClassLoader());
        nome_crianca = in.readString();
        dataNascimento_crianca = in.readString();
        turma_crianca = in.readString();
        turno_crianca = in.readString();
        temIrmao = in.readByte() != 0;
        nome_irmao = in.readString();
        dataNascimento_irmao = in.readString();
        turma_irmao = in.readString();
        turno_irmao = in.readString();
        nome_responsavel_1 = in.readString();
        cpf_responsavel_1 = in.readString();
        telefone_fixo_responsavel_1 = in.readString();
        telefone_celular_responsavel_1 = in.readString();
        email_responsavel_1 = in.readString();
        nome_responsavel_2 = in.readString();
        cpf_responsavel_2 = in.readString();
        telefone_fixo_responsavel_2 = in.readString();
        telefone_celular_responsavel_2 = in.readString();
        email_responsavel_2 = in.readString();
        unidade = in.readString();
        secretaria = in.readString();
        tipoAtendimento = in.readString();
        colaborador = in.readString();
        comoNosConheceu = in.readString();
        situacao = in.readString();
        categoria = in.readString();
        dataAgendada = in.readString();
        observacao = in.readString();
        dataCadastro = in.readString();
        //dataCadastro = (Date) in.readSerializable();
        motivo_outraEscola = in.readString();
        nome_outraEscola = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(foto_familia, flags);
        dest.writeString(nome_crianca);
        dest.writeString(dataNascimento_crianca);
        dest.writeString(turma_crianca);
        dest.writeString(turno_crianca);
        dest.writeByte((byte) (temIrmao ? 1 : 0));
        dest.writeString(nome_irmao);
        dest.writeString(dataNascimento_irmao);
        dest.writeString(turma_irmao);
        dest.writeString(turno_irmao);
        dest.writeString(nome_responsavel_1);
        dest.writeString(cpf_responsavel_1);
        dest.writeString(telefone_fixo_responsavel_1);
        dest.writeString(telefone_celular_responsavel_1);
        dest.writeString(email_responsavel_1);
        dest.writeString(nome_responsavel_2);
        dest.writeString(cpf_responsavel_2);
        dest.writeString(telefone_fixo_responsavel_2);
        dest.writeString(telefone_celular_responsavel_2);
        dest.writeString(email_responsavel_2);
        dest.writeString(unidade);
        dest.writeString(secretaria);
        dest.writeString(tipoAtendimento);
        dest.writeString(colaborador);
        dest.writeString(comoNosConheceu);
        dest.writeString(situacao);
        dest.writeString(categoria);
        dest.writeString(dataAgendada);
        dest.writeString(observacao);
        //dest.writeString(dataCadastro);
        //dest.writeLong(dataCadastro); posso tentar depois
        dest.writeString(dataCadastro);
        dest.writeString(motivo_outraEscola);
        dest.writeString(nome_outraEscola);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Visita> CREATOR = new Creator<Visita>() {
        @Override
        public Visita createFromParcel(Parcel in) {
            return new Visita(in);
        }

        @Override
        public Visita[] newArray(int size) {
            return new Visita[size];
        }
    };

    //public Date getDataCadastro() {        return dataCadastro;    }
    public String getDataCadastro() {
        return dataCadastro;
    }

//    public void setDataCadastro(Date dataCadastro) {
//        this.dataCadastro = dataCadastro;
//    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public String getTurma_crianca() {
        return turma_crianca;
    }

    public String getTurno_crianca() {
        return turno_crianca;
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

    public String getDataNascimento_crianca() {
        return dataNascimento_crianca;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setDataNascimento_crianca(String dataNascimento_crianca) {
        this.dataNascimento_crianca = dataNascimento_crianca;
    }

    public void setNome_crianca(String nome_crianca) {
        this.nome_crianca = nome_crianca;
    }

    public void setTurma_crianca(String turma_crianca) {
        this.turma_crianca = turma_crianca;
    }

    public void setTurno_crianca(String turno_crianca) {
        this.turno_crianca = turno_crianca;
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

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
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

    public String getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(String dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public boolean getTemIrmao() {
        return temIrmao;
    }

    public void setTemIrmao(boolean temIrmao) {
        this.temIrmao = temIrmao;
    }

    public String getNome_irmao() {
        return nome_irmao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setNome_irmao(String nome_irmao) {
        this.nome_irmao = nome_irmao;
    }

    public String getDataNascimento_irmao() {
        return dataNascimento_irmao;
    }

    public void setDataNascimento_irmao(String dataNascimento_irmao) {
        this.dataNascimento_irmao = dataNascimento_irmao;
    }

    public String getTurma_irmao() {
        return turma_irmao;
    }

    public void setTurma_irmao(String turma_irmao) {
        this.turma_irmao = turma_irmao;
    }

    public String getTurno_irmao() {
        return turno_irmao;
    }

    public void setTurno_irmao(String turno_irmao) {
        this.turno_irmao = turno_irmao;
    }

    public String getNome_responsavel_2() {
        return nome_responsavel_2;
    }

    public void setNome_responsavel_2(String nome_responsavel_2) {
        this.nome_responsavel_2 = nome_responsavel_2;
    }

    public String getCpf_responsavel_2() {
        return cpf_responsavel_2;
    }

    public void setCpf_responsavel_2(String cpf_responsavel_2) {
        this.cpf_responsavel_2 = cpf_responsavel_2;
    }

    public String getTelefone_fixo_responsavel_2() {
        return telefone_fixo_responsavel_2;
    }

    public void setTelefone_fixo_responsavel_2(String telefone_fixo_responsavel_2) {
        this.telefone_fixo_responsavel_2 = telefone_fixo_responsavel_2;
    }

    public String getTelefone_celular_responsavel_2() {
        return telefone_celular_responsavel_2;
    }

    public void setTelefone_celular_responsavel_2(String telefone_celular_responsavel_2) {
        this.telefone_celular_responsavel_2 = telefone_celular_responsavel_2;
    }

    public String getEmail_responsavel_2() {
        return email_responsavel_2;
    }

    public void setEmail_responsavel_2(String email_responsavel_2) {
        this.email_responsavel_2 = email_responsavel_2;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getMotivo_outraEscola() {
        return motivo_outraEscola;
    }

    public void setMotivo_outraEscola(String motivo_outraEscola) {
        this.motivo_outraEscola = motivo_outraEscola;
    }

    public String getNome_outraEscola() {
        return nome_outraEscola;
    }

    public void setNome_outraEscola(String nome_outraEscola) {
        this.nome_outraEscola = nome_outraEscola;
    }
}
