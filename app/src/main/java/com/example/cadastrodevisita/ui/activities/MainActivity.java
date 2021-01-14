package com.example.cadastrodevisita.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.ListaVisitasView;
import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.ListaVisitasAdapter;
import com.example.cadastrodevisita.cadastro.Cadastro_Colaborador;
import com.example.cadastrodevisita.cadastro.Cadastro_Como_Nos_Conheceu;
import com.example.cadastrodevisita.cadastro.Cadastro_Secretaria;
import com.example.cadastrodevisita.cadastro.Cadastro_Situacao;
import com.example.cadastrodevisita.cadastro.Cadastro_Tipo_Atendimento;
import com.example.cadastrodevisita.cadastro.Cadastro_Turma;
import com.example.cadastrodevisita.cadastro.Cadastro_Turno;
import com.example.cadastrodevisita.cadastro.Cadastro_Unidade;
import com.example.cadastrodevisita.cadastro.Cadastro_Visita;
import com.example.cadastrodevisita.dao.VisitaDAO;
import com.example.cadastrodevisita.model.Visita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.cadastrodevisita.ui.activities.Constantes.CHAVE_VISITA;

public class MainActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de visitas";
    private final ListaVisitasView listaVisitasView = new ListaVisitasView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_visita);
        setTitle(TITULO_APPBAR);
        configuraLista();
        configuraBotoesMenu();
    }

    private void configuraBotoesMenu() {

        Button b1 = findViewById(R.id.button_adicionar_Colaborador);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro_Colaborador.class);
                startActivity(intent);
            }
        });

        Button b2 = findViewById(R.id.button_adicionar_ComoNosConheceu);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro_Como_Nos_Conheceu.class);
                startActivity(intent);
            }
        });

        Button b3 = findViewById(R.id.button_adicionar_Secretaria);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro_Secretaria.class);
                startActivity(intent);
            }
        });

        Button b4 = findViewById(R.id.button_adicionar_Situacao);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro_Situacao.class);
                startActivity(intent);
            }
        });

        Button b5 = findViewById(R.id.button_adicionar_Tipo_Atendimento);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro_Tipo_Atendimento.class);
                startActivity(intent);
            }
        });

        Button b6 = findViewById(R.id.button_adicionar_Turma);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro_Turma.class);
                startActivity(intent);
            }
        });

        Button b7 = findViewById(R.id.button_adicionar_Turno);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro_Turno.class);
                startActivity(intent);
            }
        });

        Button botaoCadastraVisita = findViewById(R.id.button_adicionar_visita);
        botaoCadastraVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaCadastroVisita();
            }
        });

        Button botaoCadastraUnidade = findViewById(R.id.button_adicionar_unidade);
        botaoCadastraUnidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaCadastroUnidade();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaVisitasView.atualizaVisitas();
    }

//    private void configuraLista() {
//        ListView listaDeVisitas = findViewById(R.id.lista_visitas_listview);
//        listaVisitasView.configuraAdapter(listaDeVisitas);
//        listaDeVisitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Visita visitaClicada = (Visita) parent.getItemAtPosition(position);
//                vaiParaEditaVisita(visitaClicada);
//            }
//        });
//        registerForContextMenu(listaDeVisitas);
//    }



    private void configuraLista() {
        ListView listaDeVisitas = findViewById(R.id.lista_visitas_listview);
        final List<Visita> visitasFiltradas = listaVisitasView.listaDoAdapterFiltrado();
        listaDeVisitas.setAdapter(new ListaVisitasAdapter(visitasFiltradas, this));
        listaDeVisitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Visita visitaClicada = (Visita) parent.getItemAtPosition(position);
                vaiParaEditaVisita(visitaClicada);
            }
        });
        registerForContextMenu(listaDeVisitas);
    }


    private void vaiParaEditaVisita(Visita visitaClicada) {
        Intent intent = new Intent(MainActivity.this, Cadastro_Visita.class);
        intent.putExtra(CHAVE_VISITA, visitaClicada);
        startActivity(intent);
    }

    private void vaiParaCadastroVisita() {
        Intent intent = new Intent(MainActivity.this, Cadastro_Visita.class);
        startActivity(intent);
    }

    private void vaiParaCadastroUnidade() {
        Intent intent = new Intent(MainActivity.this, Cadastro_Unidade.class);
        startActivity(intent);
    }

}
