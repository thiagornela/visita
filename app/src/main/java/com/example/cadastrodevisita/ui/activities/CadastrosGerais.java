package com.example.cadastrodevisita.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.cadastro.colaborador.Cadastro_Colaborador;
import com.example.cadastrodevisita.cadastro.comoNosConheceu.Cadastro_ComoNosConheceu;
import com.example.cadastrodevisita.cadastro.secretaria.Cadastro_Secretaria;
import com.example.cadastrodevisita.cadastro.situacao.Cadastro_Situacao;
import com.example.cadastrodevisita.cadastro.tipoAtendimento.Cadastro_TipoAtendimento;
import com.example.cadastrodevisita.cadastro.turma.Cadastro_Turma;
import com.example.cadastrodevisita.cadastro.turno.Cadastro_Turno;
import com.example.cadastrodevisita.cadastro.unidade.Cadastro_Unidade;
import com.example.cadastrodevisita.cadastro.unidade.TelaCadastro_Unidade;
import com.example.cadastrodevisita.cadastro.categoria.Cadastro_Categoria;

public class CadastrosGerais extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastros Gerais";
    private Button cadastraColaborador;
    private Button cadastraComoNosConheceu;
    private Button cadastraSecretaria;
    private Button cadastraSituacao;
    private Button cadastraTipoAtendimento;
    private Button cadastraTurma;
    private Button cadastraTurno;
    private Button cadastraUnidade;
    private Button cadastraCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros_gerais);
        setTitle(TITULO_APPBAR);

        inicializaCampos();

        cadastraCategoria();
        cadastraColaborador();
        cadastraComoNosConheceu();
        cadastraSecretaria();
        cadastraSituacao();
        cadastraTipoAtendimento();
        cadastraTurma();
        cadastraTurno();
        cadastraUnidade();
    }

    private void cadastraCategoria() {
        cadastraCategoria.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_Categoria.class);
            startActivity(intent);
        });
    }

    private void cadastraUnidade() {
        cadastraUnidade.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_Unidade.class);
            startActivity(intent);
        });
    }

    private void cadastraTurno() {
        cadastraTurno.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_Turno.class);
            startActivity(intent);
        });
    }

    private void cadastraTurma() {
        cadastraTurma.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_Turma.class);
            startActivity(intent);
        });
    }

    private void cadastraTipoAtendimento() {
        cadastraTipoAtendimento.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_TipoAtendimento.class);
            startActivity(intent);
        });
    }

    private void cadastraSituacao() {
        cadastraSituacao.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_Situacao.class);
            startActivity(intent);
        });
    }

    private void cadastraSecretaria() {
        cadastraSecretaria.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_Secretaria.class);
            startActivity(intent);
        });
    }

    private void cadastraComoNosConheceu() {
        cadastraComoNosConheceu.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_ComoNosConheceu.class);
            startActivity(intent);
        });
    }

    private void cadastraColaborador() {
        cadastraColaborador.setOnClickListener(v -> {
            Intent intent = new Intent(CadastrosGerais.this, Cadastro_Colaborador.class);
            startActivity(intent);
        });
    }

    private void inicializaCampos() {
        cadastraCategoria = findViewById(R.id.config_cadastra_categoria);
        cadastraColaborador = findViewById(R.id.config_cadastra_colaborador);
        cadastraComoNosConheceu = findViewById(R.id.config_cadastra_comoNosConheceu);
        cadastraSecretaria = findViewById(R.id.config_cadastra_secretaria);
        cadastraSituacao = findViewById(R.id.config_cadastra_EmailsEquipe);
        cadastraTipoAtendimento = findViewById(R.id.config_cadastra_tipo_atendimento);
        cadastraTurma = findViewById(R.id.config_cadastra_turma);
        cadastraTurno = findViewById(R.id.config_cadastra_turno);
        cadastraUnidade = findViewById(R.id.config_cadastra_unidade);
    }
}