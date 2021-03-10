package com.example.cadastrodevisita.listview;

import android.content.Context;
import android.widget.ListView;

import com.example.cadastrodevisita.adapter.MensagemAdapter;
import com.example.cadastrodevisita.adapter.UnidadeAdapter;
import com.example.cadastrodevisita.dao.MensagemDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.model.Mensagem;
import com.example.cadastrodevisita.model.Unidade;

import java.util.ArrayList;
import java.util.List;

public class UnidadeView {

    private final UnidadeAdapter adapter;
    private final UnidadeDAO dao;
    private final Context context;
    private List<Unidade> unidades = new ArrayList<>();

    public UnidadeView(Context context) {
        this.context = context;
        this.adapter = new UnidadeAdapter(unidades, this.context);
        this.dao = new UnidadeDAO();
    }

    public void atualizaUnidades() {
        adapter.atualiza(dao.todos());
    }


    public void configuraAdapter(ListView listView) {
        listView.setAdapter(adapter);
    }

    private void remove(Unidade unidade) {
        dao.remove(unidade);
        adapter.remove(unidade);
    }
}
