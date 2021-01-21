package com.example.cadastrodevisita.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.ListaVisitasView;
import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.ListaVisitasAdapter;
import com.example.cadastrodevisita.cadastro.Cadastro_Visita;
import com.example.cadastrodevisita.model.Visita;

import java.util.List;

import static com.example.cadastrodevisita.ui.activities.Constantes.CHAVE_VISITA;

public class MainActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de visitas";
    private final ListaVisitasView listaVisitasView = new ListaVisitasView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_visita);
        setTitle(TITULO_APPBAR);
        configuraLista();
        configuraBotoesMenu();

    }

    private void configuraBotoesMenu() {

        Button botaoCadastraVisita = findViewById(R.id.button_adicionar_visita);
        botaoCadastraVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaiParaCadastroVisita();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaVisitasView.atualizaVisitas();
    }

    private void configuraLista() {
        ListView listaDeVisitas = findViewById(R.id.lista_visitas_listview);
        final List<Visita> visitasFiltradas = listaVisitasView.listaDoAdapterFiltrado();
        //listaDeVisitas.setAdapter(new ListaVisitasAdapter(visitasFiltradas, this));
        listaVisitasView.configuraAdapter(listaDeVisitas);
        listaDeVisitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Visita visitaClicada = (Visita) parent.getItemAtPosition(position);
                vaiParaEditaVisita(visitaClicada);
            }
        });
        //registerForContextMenu(listaDeVisitas);
    }


    private void vaiParaEditaVisita(Visita visitaClicada) {
        Intent intent = new Intent(MainActivity.this, Cadastro_Visita.class);
        intent.putExtra(CHAVE_VISITA, visitaClicada);
        //intent.putExtra(CHAVE_VISITA, visitaClicada);
        startActivity(intent);
    }

    private void vaiParaCadastroVisita() {
        Intent intent = new Intent(MainActivity.this, Cadastro_Visita.class);
        startActivity(intent);
    }

}
