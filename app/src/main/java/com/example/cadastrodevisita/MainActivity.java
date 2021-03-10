package com.example.cadastrodevisita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastrodevisita.adapter.recycleview.VisitaItemTouchHelperCallback;
import com.example.cadastrodevisita.cadastro.Cadastro_Visita;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.listview.ListaVisitasView;
import com.example.cadastrodevisita.model.Secretaria;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.ui.activities.Configuracoes;
import com.example.cadastrodevisita.ui.activities.Indicadores;
import com.example.cadastrodevisita.ui.activities.PesquisaVisitas;
import com.example.cadastrodevisita.ui.activities.Relatorios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_AMBIENTACAO_PARA;
import static com.example.cadastrodevisita.ui.Constantes.SITUACAO_CONTATO_ESCOLA_PARA;

public class MainActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de visitas";
    private final ListaVisitasView listaVisitasView = new ListaVisitasView(this);
    private UnidadeDAO unidadeDAO = new UnidadeDAO();
    private SecretariaDAO SecretariaDAO = new SecretariaDAO();

    Animation rotateOpen, rotateClose, fromBottom, toBottom;

    private boolean clicado = false;
    private FloatingActionButton fab_adiciona_visita;
    private FloatingActionButton fab_geral;
    private FloatingActionButton fab_relatorios;
    private FloatingActionButton fab_configuracoes;
    private FloatingActionButton fab_PesquisaVisitas;
    private FloatingActionButton fab_indicadores;
    private AutoCompleteTextView spinner_filtro_situacao;
    private AutoCompleteTextView spinner_filtro_secretaria;
    private AutoCompleteTextView spinner_filtro_unidade;
    private Button buttonPesquisar;
    private Button buttonLimparPesquisa;
    private RecyclerView listaDeVisitas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_visita);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
        botoesPesquisar();
        configuraLista();
        configuraFabs();
    }

    private void configuraFabs() {
        fab_geral.setOnClickListener(v -> fabGeralClicado());
        fab_configuracoes.setOnClickListener(v -> fab_configuracoes.setOnClickListener(v1 -> vaiparaConfiguracoes()));
        fab_relatorios.setOnClickListener(v -> fab_relatorios.setOnClickListener(v1 -> vaiParaRelatorios()));
        fab_indicadores.setOnClickListener(v -> fab_indicadores.setOnClickListener(v1 -> vaiparaIndicadores()));
        fab_PesquisaVisitas.setOnClickListener(v -> fab_PesquisaVisitas.setOnClickListener(v1 -> vaiparaPesquisaVisitas()));
        fab_adiciona_visita.setOnClickListener(v -> fab_adiciona_visita.setOnClickListener(v1 -> vaiParaCadastroVisita()));
    }

    private void botoesPesquisar() {
        buttonLimparPesquisa.setOnClickListener(v -> {
            listaVisitasView.atualizaVisitas();
            spinner_filtro_unidade.setText(null);
            spinner_filtro_secretaria.setText(null);
            spinner_filtro_situacao.setText(null);
        });

        buttonPesquisar.setOnClickListener(v -> {
            String unidadeConteudo = spinner_filtro_unidade.getEditableText().toString();
            String SecretariaConteudo = spinner_filtro_secretaria.getEditableText().toString();
            String SituacaoConteudo = spinner_filtro_situacao.getEditableText().toString();
            listaVisitasView.filtraPesquisa(unidadeConteudo, SecretariaConteudo, SituacaoConteudo);
        });
    }

    private void inicializaCampos() {
        spinner_filtro_unidade = findViewById(R.id.spinnerAdapterUnidade);
        ArrayAdapter<Unidade> arrayAdapterUnidade = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, unidadeDAO.todos());
        spinner_filtro_unidade.setAdapter(arrayAdapterUnidade);

        spinner_filtro_secretaria = findViewById(R.id.spinnerAdapterSecretaria);
        ArrayAdapter<Secretaria> arrayAdapterSecretaria = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, SecretariaDAO.todos());
        spinner_filtro_secretaria.setAdapter(arrayAdapterSecretaria);

        spinner_filtro_situacao = findViewById(R.id.spinnerAdapterSituacao);
        ArrayAdapter<String> arrayAdapterSituacao = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item);
        arrayAdapterSituacao.add(SITUACAO_CONTATO_ESCOLA_PARA);
        arrayAdapterSituacao.add(SITUACAO_AMBIENTACAO_PARA);
        spinner_filtro_situacao.setAdapter(arrayAdapterSituacao);

        fab_configuracoes = findViewById(R.id.fab_configuracoes);
        fab_relatorios = findViewById(R.id.fab_relatorios);
        fab_indicadores = findViewById(R.id.fab_indicadores);
        fab_PesquisaVisitas = findViewById(R.id.fab_visitas);
        fab_adiciona_visita = findViewById(R.id.fab_adiciona_visita);
        fab_geral = findViewById(R.id.fab_geral);

        buttonPesquisar = findViewById(R.id.button_pesquisar_adapter);
        buttonLimparPesquisa = findViewById(R.id.button_limpar_adapter);

        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotacionar_abri_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotacionar_fechar_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
    }

    private void fabGeralClicado() {
        setVisibility(clicado);
        setAnimation(clicado);
        setClickable(clicado);
        clicado = !clicado;
    }

    private void setVisibility(boolean clicado) {
        if (!clicado) {
            fab_adiciona_visita.setVisibility(View.VISIBLE);
            fab_relatorios.setVisibility(View.VISIBLE);
            fab_PesquisaVisitas.setVisibility(View.VISIBLE);
            fab_indicadores.setVisibility(View.VISIBLE);
            fab_configuracoes.setVisibility(View.VISIBLE);
            fab_geral.setVisibility(View.VISIBLE);
        } else {
            fab_adiciona_visita.setVisibility(View.GONE);
            fab_relatorios.setVisibility(View.GONE);
            fab_PesquisaVisitas.setVisibility(View.GONE);
            fab_indicadores.setVisibility(View.GONE);
            fab_configuracoes.setVisibility(View.GONE);
            fab_geral.setVisibility(View.GONE);
        }
    }

    private void setAnimation(boolean clicado) {
        if (!clicado) {
            fab_adiciona_visita.startAnimation(fromBottom);
            fab_relatorios.startAnimation(fromBottom);
            fab_PesquisaVisitas.startAnimation(fromBottom);
            fab_indicadores.startAnimation(fromBottom);
            fab_configuracoes.startAnimation(fromBottom);
            fab_geral.startAnimation(rotateOpen);
        } else {
            fab_adiciona_visita.startAnimation(toBottom);
            fab_relatorios.startAnimation(toBottom);
            fab_PesquisaVisitas.startAnimation(toBottom);
            fab_indicadores.startAnimation(toBottom);
            fab_configuracoes.startAnimation(toBottom);
            fab_geral.startAnimation(rotateClose);
        }
    }

    private void setClickable(boolean clicado) {
        if (!clicado) {
            fab_adiciona_visita.setClickable(true);
            fab_relatorios.setClickable(true);
            fab_PesquisaVisitas.setClickable(true);
            fab_indicadores.setClickable(true);
            fab_configuracoes.setClickable(true);
        } else {
            fab_adiciona_visita.setClickable(false);
            fab_relatorios.setClickable(false);
            fab_PesquisaVisitas.setClickable(false);
            fab_indicadores.setClickable(false);
            fab_configuracoes.setClickable(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaVisitasView.atualizaVisitas();
    }

    private void configuraLista() {
        listaDeVisitas = findViewById(R.id.lista_visitas_recycleview);
        listaVisitasView.configuraAdapter(listaDeVisitas);

    }
    private void vaiparaConfiguracoes() {
        Intent intent = new Intent(MainActivity.this, Configuracoes.class);
        startActivity(intent);
    }

    private void vaiParaRelatorios() {
        Intent intent = new Intent(MainActivity.this, Relatorios.class);
        startActivity(intent);
    }

    private void vaiparaIndicadores() {
        Intent intent = new Intent(MainActivity.this, Indicadores.class);
        startActivity(intent);
    }

    private void vaiparaPesquisaVisitas() {
        Intent intent = new Intent(MainActivity.this, PesquisaVisitas.class);
        startActivity(intent);
    }

    private void vaiParaCadastroVisita() {
        Intent intent = new Intent(MainActivity.this, Cadastro_Visita.class);
        startActivity(intent);
    }
}
