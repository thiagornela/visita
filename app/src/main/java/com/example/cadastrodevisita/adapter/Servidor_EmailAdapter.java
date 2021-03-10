package com.example.cadastrodevisita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.model.ServidorEmail;

import java.util.List;

public class Servidor_EmailAdapter extends BaseAdapter {

    private final List<ServidorEmail> servidores;
    private final Context context;

    public List<ServidorEmail> getMensagens() {
        return servidores;
    }

    public Context getContext() {
        return context;
    }

    public Servidor_EmailAdapter(List<ServidorEmail> servidores, Context context) {
        this.servidores = servidores;
        this.context = context;
    }

    @Override
    public int getCount() {
        return servidores.size();
    }

    @Override
    public Object getItem(int position) {
        return servidores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return servidores.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        ServidorEmail mensagemDevolvida = servidores.get(position);
        vincula(viewCriada, mensagemDevolvida);
        return viewCriada;
    }

    private void vincula(View view, ServidorEmail servidorEmail) {
        TextView usuario = view.findViewById(R.id.item_nome_ListViewGenerica);
        usuario.setText(servidorEmail.getUsuario());
        TextView unidade = view.findViewById(R.id.item_unidade_ListViewGenerica);
        unidade.setText(servidorEmail.getUnidade());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_cadastro, viewGroup, false);
    }

    public void atualiza(List<ServidorEmail> servidores) {
        this.servidores.clear();
        this.servidores.addAll(servidores);
        notifyDataSetChanged();
    }

    public void remove(ServidorEmail mensagem) {
        servidores.remove(mensagem);
        notifyDataSetChanged();
    }
}