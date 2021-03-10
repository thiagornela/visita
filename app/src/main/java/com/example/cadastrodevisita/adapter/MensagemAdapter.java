package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.model.Mensagem;

import java.util.List;

public class MensagemAdapter extends BaseAdapter {

    private final List<Mensagem> mensagens;
    private final Context context;

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public Context getContext() {
        return context;
    }

    public MensagemAdapter(List<Mensagem> mensagens, Context context) {
        this.mensagens = mensagens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Object getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mensagens.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Mensagem mensagemDevolvida = mensagens.get(position);
        vincula(viewCriada, mensagemDevolvida);
        return viewCriada;
    }

    private void vincula(View view, Mensagem mensagem) {
        TextView assunto = view.findViewById(R.id.item_nome_ListViewGenerica);
        assunto.setText(mensagem.getAssunto());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(mensagem.getUnidade());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<Mensagem> mensagens){
        this.mensagens.clear();
        this.mensagens.addAll(mensagens);
        notifyDataSetChanged();
    }

    public void remove(Mensagem mensagem) {
        mensagens.remove(mensagem);
        notifyDataSetChanged();
    }
}
