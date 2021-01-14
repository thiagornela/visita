package com.example.cadastrodevisita;

import android.content.Context;
import android.widget.ListView;

import com.example.cadastrodevisita.adapter.ListaVisitasAdapter;
import com.example.cadastrodevisita.dao.VisitaDAO;
import com.example.cadastrodevisita.model.Visita;

import java.util.ArrayList;
import java.util.List;

public class ListaVisitasView {

    private final ListaVisitasAdapter adapter;
    private final VisitaDAO dao;
    private final Context context;
    private List<Visita> visitas = new ArrayList<>();

    public ListaVisitasView(Context context) {
        this.context = context;
        //this.adapter = new ListaVisitasAdapter(this.context);
        this.adapter = new ListaVisitasAdapter(visitas, this.context);
        this.dao = new VisitaDAO();
    }

    public void atualizaVisitas() {
        adapter.atualiza(dao.todos());
    }

//    public void configuraAdapter(ListView listaDeVisitas) {
//        listaDeVisitas.setAdapter(adapter);
//    }

    public List<Visita> listaDoAdapterFiltrado(){
        return adapter.getVisitas();
    }

}
