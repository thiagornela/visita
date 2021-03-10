package com.example.cadastrodevisita.listview;

import android.content.Context;
import android.widget.ListView;

import com.example.cadastrodevisita.adapter.Servidor_EmailAdapter;
import com.example.cadastrodevisita.dao.ServidorEmailDAO;
import com.example.cadastrodevisita.model.Mensagem;
import com.example.cadastrodevisita.model.ServidorEmail;

import java.util.ArrayList;
import java.util.List;

public class ServidorEmailView {
    private final Servidor_EmailAdapter adapter;
    private final ServidorEmailDAO dao;
    private final Context context;
    private List<ServidorEmail> servidores = new ArrayList<>();

    public ServidorEmailView(Context context) {
        this.context = context;
        this.adapter = new Servidor_EmailAdapter(servidores, this.context);
        this.dao = new ServidorEmailDAO();
    }

    public void atualizaServidores() {
        adapter.atualiza(dao.todos());
    }


    public void configuraAdapter(ListView listView) {
        listView.setAdapter(adapter);
    }

    private void remove(ServidorEmail servidorEmail) {
        dao.remove(servidorEmail);
        adapter.remove(servidorEmail);
    }
}
