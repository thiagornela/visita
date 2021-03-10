package com.example.cadastrodevisita.listview;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastrodevisita.adapter.OnItemClickListener;
import com.example.cadastrodevisita.adapter.VisitasAdapter;
import com.example.cadastrodevisita.adapter.recycleview.VisitaItemTouchHelperCallback;
import com.example.cadastrodevisita.cadastro.Cadastro_Visita;
import com.example.cadastrodevisita.dao.VisitaDAO;
import com.example.cadastrodevisita.model.Visita;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.example.cadastrodevisita.ui.Constantes.CHAVE_VISITA;

public class ListaVisitasView {

    private final VisitasAdapter adapter;
    private final VisitaDAO dao;
    private final Context context;
    private List<Visita> visitas = new ArrayList<>();

    public ListaVisitasView(Context context) {
        this.context = context;
        this.adapter = new VisitasAdapter(visitas, this.context);
        this.dao = new VisitaDAO();
    }

    public void atualizaVisitas() {
        adapter.atualiza(dao.todos());
    }

    public void filtraPesquisa(String unidade, String secretaria, String situacao) {
        adapter.buscaFiltro(unidade, secretaria, situacao, dao.todos());
    }

    public void buscaVisitas(String unidade, String dataInicial, String dataFinal, String crianca, String responsavel, String turma, String turno) throws ParseException {
        adapter.buscadorVisitas(unidade, dataInicial, dataFinal, crianca, responsavel, turma, turno, dao.todos());
    }

    public void configuraAdapter(RecyclerView listaDeVisitas) {
        listaDeVisitas.setAdapter(adapter);
        configuraItemTouchHelper(listaDeVisitas);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(Visita visita, int posicao) {
                Visita visitaEncontrada = dao.buscaVisitaPeloId(visita);
                vaiParaEditaVisita(visitaEncontrada);
            }
        });
    }

    private void configuraItemTouchHelper(RecyclerView listaDeVisitas) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new VisitaItemTouchHelperCallback(adapter));
        itemTouchHelper.attachToRecyclerView(listaDeVisitas);
    }

    private void vaiParaEditaVisita(Visita visitaClicada) {
        Intent intent = new Intent(context, Cadastro_Visita.class);
        intent.putExtra(CHAVE_VISITA, visitaClicada);
        context.startActivity(intent);
    }

    public List<Visita> listaDoAdapterFiltrado() {
        return adapter.getVisitas();
    }

    public void atualizaActivityPesquisa() {
        adapter.atualizaActivityPesquisa(dao.todos());
    }

    public void removeTudo() {
        adapter.removeTudo();
    }


}
