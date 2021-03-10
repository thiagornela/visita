package com.example.cadastrodevisita.listview;

import android.content.Context;
import android.widget.ListView;

import com.example.cadastrodevisita.adapter.MensagemAdapter;
import com.example.cadastrodevisita.dao.MensagemDAO;
import com.example.cadastrodevisita.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

public class MensagemView {

    private final MensagemAdapter adapter;
    private final MensagemDAO dao;
    private final Context context;
    private List<Mensagem> mensagens = new ArrayList<>();

    public MensagemView(Context context) {
        this.context = context;
        this.adapter = new MensagemAdapter(mensagens, this.context);
        this.dao = new MensagemDAO();
    }

    public void atualizaMensagem() {
        adapter.atualiza(dao.todos());
    }


    public void configuraAdapter(ListView listView) {
        listView.setAdapter(adapter);
    }

    private void remove(Mensagem mensagem) {
        dao.remove(mensagem);
        adapter.remove(mensagem);
    }
}
